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

public class GoogleP_FindFansAndMarkTrivial {

	final static String TrivialToken = "\t+\t#TrivialGraph";

	public static class GooglePVertex extends Vertex<Text, NullWritable, Text> {

		int numInterest, numFans, numReciprocity;
	    
	    public String removeLastSymbol(String msg) {
	    	return msg.substring(0, msg.length() - 1);
	    }

		@Override
		public void setup(Configuration conf) {
			numInterest = getEdges().size();
			numFans = 0;
			numReciprocity = 0;
		}

		// Send to neighbors and append the fans
		@Override
		public void compute(Iterator<Text> messages) throws IOException {
			if (getSuperstepCount() == 0L) {
				Text msg = getVertexID();
				sendMessageToNeighbors(msg);
			} 
			// Append all the fans
			else {
				String fan = new String("\t+");
				String reciprocity = new String("\t#");
				while (messages.hasNext()) {
					Text msg = messages.next();
					fan += msg.toString() + ",";
					numFans++;
					// if the msg is from my neighbors, send it back
					for (Edge<Text, NullWritable> e : getEdges()) {
						if (msg.equals(e.getDestinationVertexID())) {
							reciprocity += msg.toString() + ",";
							numReciprocity++;
						}
					}
				}
				
				// remove the last comma
				fan = removeLastSymbol(fan);

				String oldValueStr = getValue().toString();
				String newValueStr = new String();
				if (numInterest == 0) {
					newValueStr = oldValueStr.replaceFirst(TrivialToken, fan);
				} else {
					if (numReciprocity > 0) {
						// remove the last comma
						reciprocity = removeLastSymbol(reciprocity);	
						newValueStr = oldValueStr + fan + reciprocity;
					}
					else {
						newValueStr = oldValueStr + fan;
					}
				}
				setValue(new Text(newValueStr));
				voteToHalt();
			}
		}
	}

	public static class GooglePOriginalTxtFileReader extends // the first 2 are
																// key and
																// value, the
																// left 3 are V,
																// E, M
			VertexInputReader<LongWritable, Text, Text, NullWritable, Text> {
		@Override
		public boolean parseVertex(LongWritable key, Text value,
				Vertex<Text, NullWritable, Text> vertex) throws Exception {
			String[] vertexInfo = value.toString().split(":");
			vertex.setVertexID(new Text(vertexInfo[0].trim()));

			if (vertexInfo.length == 1) {
				vertex.setValue(new Text(":" + TrivialToken));
				return true;
			}

			vertex.setValue(new Text(":" + vertexInfo[1]));
			String[] neighborStrAr = vertexInfo[1].split(",");
			for (String neighbor : neighborStrAr) {
				vertex.addEdge(new Edge<Text, NullWritable>(new Text(neighbor),
						null));
			}

			return true;
		}
	}

	public static GraphJob createJob(String[] args, HamaConfiguration conf)
			throws IOException {
		GraphJob selectJob = new GraphJob(conf, GoogleP_FindFansAndMarkTrivial.class);
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