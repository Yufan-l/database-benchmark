package neo4j;


import org.neo4j.graphdb.*;
import org.neo4j.*;

import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;
import com.tinkerpop.blueprints.oupls.jung.JungHelper;

import edu.uci.ics.jung.algorithms.scoring.*;
import edu.uci.ics.jung.*;


public class Pagerank {
	
	public static void doBetweenness(String path){
		Neo4jGraph graph = new Neo4jGraph(path); // construct a particular Blueprints graph implementation
		
		
		long start=System.currentTimeMillis();
		BetweennessCentrality<Vertex,Edge> BC=new BetweennessCentrality<Vertex,Edge>(new GraphJung<Neo4jGraph>(graph));
		System.out.println("total time: "+ (System.currentTimeMillis()-start));

		//System.out.println("total nodes: "+ i);
		graph.shutdown();
		
	}
	
	
	public static void doPageRank(String path, double rate){
		Neo4jGraph graph = new Neo4jGraph(path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<Neo4jGraph>(graph), rate);
		long start=System.currentTimeMillis();
		//pageRank.step();
		//pageRank.step();
		//pageRank.step();
		for(int i=0;i<10;i++){
			pageRank.step();
		}
		//pageRank.setMaxIterations(10);
		//pageRank.evaluate();
		double i=0;
		/*for (Vertex vertex : graph.getVertices()) {
			if(i>=10)
				break;
		  System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  i++;
		}*/
		
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		System.out.println("Iterations number: "  + pageRank.getIterations());
		//System.out.println("total nodes: "+ i);
		graph.shutdown();
		
	}
	
public static void main(String args[]){
	
		doPageRank("/Users/yliu/Documents/ca-AstroPh_Neo",0.15d);
	//doBetweenness("/Users/yliu/Documents/ca-AstroPh_Neo");
	
}
}
