import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

public class GoogleP_FindClique {

	public static class GooglePVertex extends Vertex<Text, NullWritable, Text> {

		List<String> reciprocities = new ArrayList<String>();
		String msgReci = "";
	    List<List<String>> cliques = new ArrayList<List<String>>();
	    
	    public String removeLastSymbol(String msg) {
	    	return msg.substring(0, msg.length() - 1);
	    }
	    
	    // refine the reciAr with my reciprocities
	    public List<String> refineReci(String[] reciAr) {
	    	List<String> refinedAr = new ArrayList<String>();
	    	for (String reci : reciAr) {
	    		if (reciprocities.contains(reci)) {
	    			refinedAr.add(reci);
	    		}
	    	}
	    	return refinedAr;
	    }
	    
	    public String msgFromSortedReci(List<String> reciAr) {
	    	String msg = "";
	    	for (String reci : reciAr) {
	    		msg += reci + ",";
	    	}
	    	return removeLastSymbol(msg);
	    }

		@Override
		public void setup(Configuration conf) {
		}

		// Send to neighbors and append the fans
		@Override
		public void compute(Iterator<Text> messages) throws IOException {
			if (getSuperstepCount() == 0L) {
				sendMessageToNeighbors(getVertexID());
			}
			// Find all the fans
			else if (getSuperstepCount() == 1L) {
				while (messages.hasNext()) {
					Text msg = messages.next();
					// if the msg is from my neighbors, record it
					for (Edge<Text, NullWritable> e : getEdges()) {
						if (msg.equals(e.getDestinationVertexID())) {
							reciprocities.add(msg.toString());
							msgReci += msg.toString() + ",";
						}
					}
				}
				// send msg to all the reciprocities, if I have two at least
				if (reciprocities.size() >= 2) {
					msgReci = removeLastSymbol(msgReci);
					// add myself at the beginning of ths msg
					msgReci = getVertexID().toString() + "," + msgReci;
					//sendMessageToNeighbors(new Text(msgReci));					
					for (String reci : reciprocities) {
						sendMessage(new Text(reci), new Text(msgReci));
					}					
				}				
			}
			// Refine the msg for my reciprocities
			else if (getSuperstepCount() == 2L) {
				//String id = getVertexID().toString();
				while (messages.hasNext()) {
					Text msg = messages.next();
					String[] reciList = msg.toString().split(",");
					// refine the reciList
					List<String> refinedAr = refineReci(reciList);
					// there maybe a clique in my view
					if (refinedAr.size() > 1) {
						refinedAr.add(getVertexID().toString()); // add myself
						// sort the list
						Collections.sort(refinedAr);
						String refined = msgFromSortedReci(refinedAr);				
						// send the refined msg back
						sendMessage(new Text(reciList[0]), new Text(refined));
					}
				}
			}
			// At last, make the statistic of the Clique
			else {
				List<String> cliAr = new ArrayList<String>();
				Set<String> uniqueAr = new HashSet<String>();
				while (messages.hasNext()) {
					String msg = messages.next().toString();
					cliAr.add(msg);
					uniqueAr.add(msg);
				}
				
				// statistic
				int cliqueNum = 0;
				String cliqVertexNumStr = ""; 
				String cliqueStr = new String();
		        for (String msg : uniqueAr) {
		        	int freq = Collections.frequency(cliAr, msg);
		        	String[] cliqueAr = msg.split(",");
		        	if (freq == cliqueAr.length - 1) {
		        		cliqueNum++;
		        		cliqVertexNumStr += String.valueOf(cliqueAr.length) + ",";
		        		// L means I am the leader of the clique
		        		if (cliqueAr[0].equals(getVertexID().toString())) {
			        		cliqueStr += "(" + "L " + msg + ")";
		        		}
		        		else {
			        		cliqueStr += "(" + msg + ")";		        			
		        		}
		        	}
		        }
		        
		        if (cliqueNum > 0) {
			        cliqVertexNumStr = removeLastSymbol(cliqVertexNumStr);				
					setValue(new Text("{" + String.valueOf(cliqueNum) + "}"
							+ "[" + cliqVertexNumStr + "]" 
							+ cliqueStr ));		        	
		        }
		        
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
			vertex.setValue(new Text());

			if (vertexInfo.length == 1) {
				return true;
			}

			String[] neighborStrAr = vertexInfo[1].split(",");
			for (String neighbor : neighborStrAr) {
				vertex.addEdge(new Edge<Text, NullWritable>(new Text(neighbor), null));
			}

			return true;
		}
	}

	public static GraphJob createJob(String[] args, HamaConfiguration conf)
			throws IOException {
		GraphJob selectJob = new GraphJob(conf, GoogleP_FindClique.class);
		selectJob.setJobName("GoogleP_Select");
		selectJob.set("hama.graph.self.ref", "false");

		selectJob.setVertexClass(GooglePVertex.class);
		selectJob.setInputPath(new Path(args[0]));
		selectJob.setOutputPath(new Path(args[1]));

		selectJob.setMaxIteration(-1);

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