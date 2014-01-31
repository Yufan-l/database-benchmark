package neo4j;

import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

/*
 * totally edges: 3387388
403395

 */
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.index.UniqueFactory;
import org.neo4j.tooling.GlobalGraphOperations;

import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;



public class BasicTools {
	public static GraphDatabaseService graphDb;
	public static UniqueFactory<Node> factory;
	public static Index<Node> nodeIndex;
	private static enum RelTypes implements RelationshipType
	{
	    KNOWS
	}
	
	public BasicTools(String path)
	{
		//Neo4jGraph graph = new Neo4jGraph("/Users/yliu/Documents/testdata");
		
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(path);
		nodeIndex=graphDb.index().forNodes("nodeId");
		
		//factory
		//registerShutdownHook( graphDb );
	}
	
	public void importFromText(String path,String splitter,String folder,int reportInterval)throws Exception{
		Scanner SC=new Scanner(new FileReader(path));
		int count=0;
		long start = System.currentTimeMillis();
		while(SC.hasNext()){
		//for(int i=0;i<100;i++){
			if(count % reportInterval==0)
			{
				System.out.println("imported edges: "+count);
				System.out.println("spent time "+(System.currentTimeMillis()-start)/1000);
				System.out.println("total size "+folderSize(new File(folder)));
			}
			String newLine=SC.nextLine();

			if(newLine.contains("#"))
				continue;
			String content[]=newLine.split(splitter);
			Node a=insertNode(content[0]);
			Node b=insertNode(content[1]);
			insertEdge(a, b);
			
			count++;
			
		}
		System.out.println("totally edges: "+count);
		
		
		SC.close();
	}
	 public static long folderSize(File directory) {
	        long length = 0;
	        for (File file : directory.listFiles()) {
	            if (file.isFile())
	                length += file.length();
	            else
	                length += folderSize(file);
	        }
	        return length;
	    }
	 
	public Node insertNode (String id){
		Transaction tx = graphDb.beginTx();
		Node newNode;
		try
		{
			if(!nodeIndex.get("ID", id).hasNext()){
			newNode=graphDb.createNode();
			newNode.setProperty("ID", id);
			nodeIndex.add(newNode, "ID", id);
			}
			else
				return nodeIndex.get("ID", id).next();
			
			tx.success();
		}
		finally
		{
		    tx.finish();
		}
		return newNode;
		
	}
	
	public Relationship insertEdge (Node a,Node b){
		Transaction tx = graphDb.beginTx();
		Relationship newEdge;
		try
		{
			newEdge=a.createRelationshipTo(b,RelTypes.KNOWS);
			tx.success();
		}
		finally
		{
		    tx.finish();
		}
		return newEdge;
	}
	
	public long countNodes()throws Exception
	{
		long count=0;
		Iterator<Node> itr=GlobalGraphOperations.at(graphDb).getAllNodes().iterator();
		while(itr.hasNext()){
			//System.out.println(itr.next().getProperty("ID"));
			itr.next();
			count++;
		}
		System.out.println(count);
		return count;
	}
	
	public long countEdges()
	{
		
		long count=0;
		Iterator<Relationship> itr=GlobalGraphOperations.at(graphDb).getAllRelationships().iterator();
		while(itr.hasNext()){
			itr.next();
			count++;
		}
		return count;
	}
	
	public void registerShutdownHook( )
	{
		graphDb.shutdown();
	}
	
	public static void main(String args[])throws Exception{
		BasicTools BT=new BasicTools("/Users/yliu/Documents/testdata1");
		//Node a=BT.insertNode("1");
		//Node b=BT.insertNode("2");
		//BT.insertNode("3");
		//BT.insertEdge(a,b);
		BT.countNodes();
		BT.registerShutdownHook();
		//BT.importFromText("/Users/yliu/Downloads/graph/dataset/amazon0601.txt","\t");
		//BasicTools BT=new BasicTools("/Users/yliu/Documents/ca-AstroPh_Neo");
		//BT.importFromText("/Users/yliu/Downloads/graph/dataset/ca-AstroPh.txt","\t","/Users/yliu/Documents/ca-AstroPh_Neo",20000);
		//System.out.println(BT.countNodes());
	    //System.out.println(BT.countEdges());
	}

}
