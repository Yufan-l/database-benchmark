package dex;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Edge;
//import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.dex.*;

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


    public static DexGraph create(final String directory,String indexName) {
        
    	DexGraph graph = new DexGraph(directory);
    	
    	if(!graph.getIndexedKeys(Vertex.class).contains(indexName))
    	{
    		
    		graph.label.set("people");
    		
    		graph.createKeyIndex(indexName,Vertex.class);
    		System.out.println("create index");
    		
    		graph.commit();
    	}
    	
        return graph;
    }
    
    public static Vertex insertNode(final DexGraph graph,String property,Object value){
    	//CloseableIterable<Vertex> it=graph.getVertices(property, value);
    	//Iterator<Vertex> itr=it.iterator();
    	Iterator<Vertex> itr=graph.query().has(property, value).vertices().iterator();
    	if(!itr.hasNext()){
    		 Vertex newVertex = graph.addVertex(null);
    		 newVertex.setProperty(property, value);
    	     //ElementHelper.setProperties(newVertex, property,value);
    	     return newVertex;
    	     
    	}
    	
    	return itr.next();
    	
    }
    public static void insertEdge(final DexGraph graph,Vertex A, Vertex B,String edgeName){
    	A.addEdge(edgeName, B); 	
    	
    }
    
    public static void insertFromText(String path, DexGraph graph,String splitter,String folder, int reportInterval,String indexName)throws Exception{
    	
    	Scanner SC=new Scanner(new FileReader(path));
		int count=0;
		long start=System.currentTimeMillis();
		while(SC.hasNext()){
		//for(int i=0;i<10000;i++){
			if(count % reportInterval==0)
			{			
				
				//System.out.println("imported edges: "+count);
				//System.out.println("spent time "+(System.currentTimeMillis()-start)/1000);
				//System.out.println("total size "+folderSize(new File(folder)));
				System.out.println(count+"\t"+(System.currentTimeMillis()-start)/1000+"\t"+(folderSize(new File(folder))));
				//System.out.println(graph.getRawGraph().countNodes());
			}
			String newLine=SC.nextLine();

			if(newLine.contains("#"))
				continue;
			String content[]=newLine.split(splitter);
			Vertex a=insertNode(graph,"name",content[0]);
			Vertex b=insertNode(graph,"name",content[1]);
			insertEdge(graph,a, b,"KNOWS");
			
			count++;
			graph.commit();
			//graph.shutdown();
			
			
		}
		//graph.commit();
		System.out.println("totally edges: "+count);
		
		
		SC.close();
    	
    	
    }
    
    public static void dropNode(DexGraph graph){
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
   /* 
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
    */
    
    public static long folderSize(File directory) {
        
        
        return directory.length();
    }
    
    public static void main(String args[])throws Exception{
    	
    	//DexGraph graph=Tinkerpop.create("/Users/yliu/Documents/testdata");
    	//Tinkerpop.insertNode(graph, "name", "1");
    	//Tinkerpop.insertNode(graph, "name", "2");
    	//Tinkerpop.insertNode(graph, "name", "1");
    	
    	DexGraph graph = Tinkerpop.create("/Users/yliu/Documents/ca-AstroPh_Dex","name");
    	//Vertex v2=graph.addVertex(null);
    	//v2.setProperty("age", "123");
    	
    	
    	//System.out.println(graph.getIndexedKeys(Vertex.class).iterator().next());
    	
    	//v1.addEdge("knows", v2);
    	insertFromText("/Users/yliu/Downloads/graph/dataset/ca-AstroPh.txt",graph,"\t","/Users/yliu/Documents/ca-AstroPh_Dex",100,"name");
    
    	
    	//dropNode(graph);
    	
    	//System.out.println(BasicToolTitan.folderSize(new File("/Users/yliu/Documents/ca-AstroPh_titan")));
    	graph.shutdown();
    	
    	
    	
    }
    
}