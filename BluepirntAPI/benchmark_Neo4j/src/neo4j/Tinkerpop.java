package neo4j;


import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.util.ElementHelper;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;



public class Tinkerpop {

    public static final String INDEX_NAME = "search";


    public static Neo4jGraph create(final String directory,String indexName) {
        
    	Neo4jGraph graph = new Neo4jGraph(directory);
    	if(graph.getIndex(indexName, Vertex.class)==null)
    	{
    		Index<Vertex> index = graph.createIndex(indexName, Vertex.class);
    	}
        return graph;
    }
    
    public static Vertex insertNode(final Neo4jGraph graph,String property,Object value,Index<Vertex> idx){
    	Iterator<Vertex> itr=idx.get(property, value).iterator();
    	if(!itr.hasNext()){
    		 Vertex newVertex = graph.addVertex(null);
    	     ElementHelper.setProperties(newVertex, property,value);
    	     idx.put(property, value, newVertex);
    	     return newVertex;
    	}
    	return itr.next();
    	
    }
    public static void insertEdge(final Neo4jGraph graph,Vertex A, Vertex B,String edgeName){
    	A.addEdge(edgeName, B); 	
    	
    }
    
    public static void insertFromText(String path, final Neo4jGraph graph,String splitter,String folder, int reportInterval,String indexName)throws Exception{
    	
    	Index<Vertex> index = graph.getIndex(indexName, Vertex.class);
    	if(index==null)
    	{
    		System.out.println("Can't input without index, exit!");
    		return;
    	}
    	Scanner SC=new Scanner(new FileReader(path));
		int count=0;
		long start=System.currentTimeMillis();
		while(SC.hasNext()){
		//for(int i=0;i<100;i++){
			if(count % reportInterval==0)
			{
				
				//System.out.println("imported edges: "+count);
				//System.out.println("spent time "+(System.currentTimeMillis()-start)/1000);
				//System.out.println("total size "+folderSize(new File(folder)));
				System.out.println(count+"\t"+(System.currentTimeMillis()-start)/1000+"\t"+(folderSize(new File(folder))));
			}
			String newLine=SC.nextLine();

			if(newLine.contains("#"))
				continue;
			String content[]=newLine.split(splitter);
			Vertex a=insertNode(graph,"Name",content[0],index);
			Vertex b=insertNode(graph,"Name",content[1],index);
			insertEdge(graph,a, b,"KNOWS");
			
			count++;
			graph.commit();
		}
		//graph.commit();
		System.out.println("totally edges: "+count);
		
		
		SC.close();
    	
    	
    }
    
    public static void dropNode(Neo4jGraph graph){
    	int i=0;
    	Set<Vertex> unique=new HashSet<Vertex>();
    	for(Iterator<Vertex>itr=graph.getVertices().iterator();itr.hasNext();)
    	{
    		itr.next();
    		//unique.add(itr.next());
    		i++;
    	}
    	for(Vertex v1:unique)
    	{
    		//graph.removeVertex(v1);
    		//System.out.println(i);
    	}
    	graph.commit();
    	//System.out.println(graph.query().has("Name","1").vertices().iterator().next());
    	System.out.println(i);
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
    
    public static void main(String args[])throws Exception{
    	
    	//Neo4jGraph graph=Tinkerpop.create("/Users/yliu/Documents/testdata");
    	//Tinkerpop.insertNode(graph, "name", "1");
    	//Tinkerpop.insertNode(graph, "name", "2");
    	//Tinkerpop.insertNode(graph, "name", "1");
    	
    	Neo4jGraph graph = Tinkerpop.create("/Users/yliu/Documents/amazon0302_Neo","name");
    	insertFromText("/Users/yliu/Downloads/graph/dataset/amazon0302.txt",graph,"\t","/Users/yliu/Documents/amazon0302_Neo",100000,"name");

    	//dropNode(graph);
    	
    	//System.out.println(BasicToolTitan.folderSize(new File("/Users/yliu/Documents/ca-AstroPh_titan")));
    	graph.shutdown();
    }
    
}