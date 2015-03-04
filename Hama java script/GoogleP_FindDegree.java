import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hama.HamaConfiguration;
import org.apache.hama.bsp.HashPartitioner;
import org.apache.hama.bsp.TextInputFormat;
import org.apache.hama.bsp.TextOutputFormat;
import org.apache.hama.graph.GraphJob;
import org.apache.hama.graph.Vertex;
import org.apache.hama.graph.Edge;
import org.apache.hama.graph.VertexInputReader;

public class GoogleP_FindDegree {

	public static class GooglePVertex extends Vertex<Text, NullWritable, Text> {

		int inDegree;
		int reciDegree;

		@Override
		public void setup(Configuration conf) {
			inDegree = 0;
			reciDegree = 0;
		}

		// Send to neighbors and append the fans
		@Override
		public void compute(Iterator<Text> messages) throws IOException {
			if (getSuperstepCount() == 0L) {
				Text msg = getVertexID();
				sendMessageToNeighbors(msg);
			} else {
				while (messages.hasNext()) {
					inDegree++;
					Text msg = messages.next();
					// if the msg is from my neighbors, increase the reciDegree
					for (Edge<Text, NullWritable> e : getEdges()) {
						if (msg.equals(e.getDestinationVertexID())) {
							reciDegree++;
						}
					}
				}
				String valueStr = getValue().toString();
				valueStr = valueStr + "\t" + String.valueOf(inDegree)
						+ "\t" + String.valueOf(reciDegree);
				setValue(new Text(valueStr));
				voteToHalt();
			}
		}
	}

	// the first 2 are key and value, the left 3 are V, E, M
	public static class GooglePOriginalTxtFileReader extends									
			VertexInputReader<LongWritable, Text, Text, NullWritable, Text> {
		@Override
		public boolean parseVertex(LongWritable key, Text value,
				Vertex<Text, NullWritable, Text> vertex) throws Exception {
			String[] vertexInfo = value.toString().split(":");
			vertex.setVertexID(new Text(vertexInfo[0].trim()));

			if (vertexInfo.length == 1) {
				vertex.setValue(new Text("0"));
				return true;
			}

			String[] neighborStrAr = vertexInfo[1].split(",");
			vertex.setValue(new Text(String.valueOf(neighborStrAr.length)));
			for (String neighbor : neighborStrAr) {
				vertex.addEdge(new Edge<Text, NullWritable>(new Text(neighbor),
						null));
			}

			return true;
		}
	}

	public static GraphJob createJob(String[] args, HamaConfiguration conf)
			throws IOException {
		GraphJob selectJob = new GraphJob(conf, GoogleP_FindDegree.class);
		selectJob.setJobName("GoogleP_Select");
		selectJob.set("hama.graph.self.ref", "false");

		selectJob.setVertexClass(GooglePVertex.class);
		selectJob.setInputPath(new Path(args[0]));
		selectJob.setOutputPath(new Path(args[1]));

		// selectJob.setMaxIteration(1);

		if (args.length == 3) {
			selectJob.setNumBspTask(Integer.parseInt(args[2]));
		}

		selectJob.setVertexIDClass(Text.class); // V
		selectJob.setEdgeValueClass(NullWritable.class); // E
		selectJob.setVertexValueClass(Text.class); // M

		selectJob.setInputFormat(TextInputFormat.class);
		selectJob.setVertexInputReaderClass(GooglePOriginalTxtFileReader.class); // Vertex
																					// reader
		selectJob.setPartitioner(HashPartitioner.class);
		selectJob.setOutputFormat(TextOutputFormat.class);
		selectJob.setOutputKeyClass(Text.class);
		selectJob.setOutputValueClass(DoubleWritable.class);
		return selectJob;
	}

	private static void printUsage() {
		System.out.println("Usage: <input> <output> [tasks]");
		System.exit(-1);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException, ClassNotFoundException {
		if (args.length < 2)
			printUsage();

		HamaConfiguration conf = new HamaConfiguration(new Configuration());
		GraphJob pageJob = createJob(args, conf);

		long startTime = System.currentTimeMillis();
		if (pageJob.waitForCompletion(true)) {
			System.out.println("Job Finished in "
					+ (System.currentTimeMillis() - startTime) / 1000.0
					+ " seconds");
		}
	}
}