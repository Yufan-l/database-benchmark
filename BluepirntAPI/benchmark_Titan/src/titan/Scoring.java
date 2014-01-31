package titan;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.graphdb.blueprints.TitanBlueprintsGraph;
//import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;
import com.tinkerpop.blueprints.oupls.jung.JungHelper;

import edu.uci.ics.jung.algorithms.scoring.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.*;


public class Scoring {
	
	public static void pageRank(Configuration conf,double ref){
		TitanGraph graph = TitanFactory.open(conf); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<TitanGraph>(graph), ref);
		long start=System.currentTimeMillis();
		//pageRank.setMaxIterations(10);
		//pageRank.evaluate();
		for(int i=0;i<10;i++){
			pageRank.step();
		}
		double i=0;
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		/*for (Vertex vertex : graph.getVertices()) {
			if(i>=10)
				break;
		  System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  i++;
		}*/
		System.out.println("Iterations number: "  + pageRank.getIterations());
		
		System.out.println("total nodes: "+ i);
		
	}
	
	public static void pageRank(String path,double ref){
		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<TitanGraph>(graph), ref);
		long start=System.currentTimeMillis();
		//pageRank.setMaxIterations(10);
		//pageRank.evaluate();
		for(int i=0;i<10;i++){
			pageRank.step();
		}
		double i=0;
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		/*for (Vertex vertex : graph.getVertices()) {
			if(i>=10)
				break;
		  System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  i++;
		}*/
		System.out.println("Iterations number: "  + pageRank.getIterations());
		
		System.out.println("total nodes: "+ i);
		
	}
	
	/*public static void BetweennessCentrality(String path,double ref){
		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		BetweennessCentrality<Vertex,Edge> BetweennessCentrality = new BetweennessCentrality<Vertex, Edge>(new GraphJung<TitanGraph>(graph), ref);
		long start=System.currentTimeMillis();
		pageRank.evaluate();
		double i=0;
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		for (Vertex vertex : graph.getVertices()) {
			//if(i>=100)
				//break;
		  //System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  i++;
		}
		
		
		System.out.println("total nodes: "+ i);
		
	}*/
	public static void ClosenessCentrality(String path){
		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		long start=System.currentTimeMillis();
		ClosenessCentrality<Vertex,Edge> CC = new ClosenessCentrality<Vertex, Edge>(new GraphJung<TitanGraph>(graph));
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
	
		double i=0;
		
		for (Vertex vertex : graph.getVertices()) {
			if(i>=5)
				break;
		  System.out.println("The PageRank score of " + vertex + " is: " + CC.getVertexScore(vertex));
		  i++;
		}
		
		
		System.out.println("total nodes: "+ i);
		
	}
	public static void DegreeScorer(String path,double ref){
		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<TitanGraph>(graph), ref);
		long start=System.currentTimeMillis();
		pageRank.evaluate();
		double i=0;
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		for (Vertex vertex : graph.getVertices()) {
			if(i>=10)
				break;
		  System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  i++;
		}
		
		
		System.out.println("total nodes: "+ i);
		
	}
	
	public static void HITS(String path,double ref){
		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<TitanGraph>(graph), ref);
		long start=System.currentTimeMillis();
		pageRank.evaluate();
		double i=0;
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		/*for (Vertex vertex : graph.getVertices()) {
			//if(i>=100)
				//break;
		  //System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  i++;
		}*/
		
		
		System.out.println("total nodes: "+ i);
		
	}
	
public static void main(String args[]){
	//for(int i=0;i<4;i++)
	Configuration conf = new BaseConfiguration();
	
	//conf.setProperty("storage.port","2182");
	
	//conf.setProperty("storage.backend","hbase");
	
	
	//conf.setProperty("storage.backend","cassandra");
	//conf.setProperty("storage.hostname","127.0.0.1");
	//conf.setProperty("storage.short-cf-names",false);
	
	conf.setProperty("storage.tablename","ca-AstroPh1");
	
	//pageRank(conf,0.15d);
	
	pageRank("/Users/yliu/Documents/ca-AstroPh_titan",0.15d);
	//ClosenessCentrality("/Users/yliu/Documents/ca-AstroPh_titan");
	
}
}
