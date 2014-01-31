package titan;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.attribute.Geoshape;
import com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration;
import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.ElementHelper;
import com.tinkerpop.blueprints.util.KeyIndexableGraphHelper;
import com.tinkerpop.blueprints.Predicate;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration.INDEX_BACKEND_KEY;
import static com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration.STORAGE_DIRECTORY_KEY;


/**
* Example Graph factory that creates a {@link TitanGraph} based on roman mythology.
* Used in the documentation examples and tutorials.
*
* @author Marko A. Rodriguez (http://markorodriguez.com)
*/
public class BasicToolTitan {

    public static final String INDEX_NAME = "search";


    public static TitanGraph create(final String directory) {
    	
        BaseConfiguration config = new BaseConfiguration();
        Configuration storage = config.subset(GraphDatabaseConfiguration.STORAGE_NAMESPACE);
        // configuring local backend
        storage.setProperty(GraphDatabaseConfiguration.STORAGE_BACKEND_KEY, "local");
        storage.setProperty(GraphDatabaseConfiguration.STORAGE_DIRECTORY_KEY, directory);
        
        // configuring elastic search index
        
        Configuration index = storage.subset(GraphDatabaseConfiguration.INDEX_NAMESPACE).subset(INDEX_NAME);
        index.setProperty(INDEX_BACKEND_KEY, "elasticsearch");
        index.setProperty("local-mode", true);
        index.setProperty("client-only", false);
        index.setProperty(STORAGE_DIRECTORY_KEY, directory + File.separator + "es");
         
        
        TitanGraph graph = TitanFactory.open(directory);
        graph.makeKey("Name").dataType(String.class).indexed(Vertex.class).unique().make();
        
        graph.commit();
        return graph;
    }
    
    public static Vertex insertNode(final TitanGraph graph,String property,Object value){
    	Iterator<Vertex> itr=graph.query().has(property,Cmp.EQUAL,value).vertices().iterator();
    	
    	if(!itr.hasNext()){
    		 Vertex newVertex = graph.addVertex(null);
    	     ElementHelper.setProperties(newVertex, property,value);
    	     return newVertex;
    	}
    	return itr.next();
    	
    }
    public static void insertEdge(final TitanGraph graph,Vertex A, Vertex B,String edgeName){
    	A.addEdge(edgeName, B); 	
    	
    }
    
    public static void insertFromText(String path, final TitanGraph graph,String splitter,String folder, int reportInterval)throws Exception{
    	
    	
    	Scanner SC=new Scanner(new FileReader(path));
		int count=0;
		long start=System.currentTimeMillis();
		while(SC.hasNext()){
		//for(int i=0;i<100;i++){
			if(count % reportInterval==0)
			{
				/*
				System.out.println("imported edges: "+count);
				System.out.println("spent time "+(System.currentTimeMillis()-start)/1000);
				System.out.println("total size "+folderSize(new File(folder)));
				*/
				System.out.println(count+"\t"+(System.currentTimeMillis()-start)/1000+"\t"+(folderSize(new File(folder))));
			}
			String newLine=SC.nextLine();

			if(newLine.contains("#"))
				continue;
			String content[]=newLine.split(splitter);
			Vertex a=insertNode(graph,"Name",content[0]);
			Vertex b=insertNode(graph,"Name",content[1]);
			insertEdge(graph,a, b,"KNOWS");
			
			count++;
			graph.commit();
		}
		//graph.commit();
		System.out.println("totally edges: "+count);
		
		
		SC.close();
    	
    	
    }
    
    public static void dropNode(TitanGraph graph){
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
    	
    	TitanGraph graph=BasicToolTitan.create("/Users/yliu/Documents/amazon0302_titan");
    	//TitanGraph graph = TitanFactory.open("/Users/yliu/Documents/ca-AstroPh_titan");
    	insertFromText("/Users/yliu/Downloads/graph/dataset/amazon0302.txt",graph,"\t","/Users/yliu/Documents/amazon0302_titan",100000);
    	graph.shutdown();
    	//dropNode(graph);
    	
    	//System.out.println(BasicToolTitan.folderSize(new File("/Users/yliu/Documents/ca-AstroPh_titan")));
    }
    
}