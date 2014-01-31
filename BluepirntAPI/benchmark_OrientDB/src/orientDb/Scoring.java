package orientDb;


import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.*;
import com.tinkerpop.blueprints.util.ElementHelper;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;
import com.tinkerpop.blueprints.oupls.jung.JungHelper;

import edu.uci.ics.jung.algorithms.scoring.*;
//import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.*;


public class Scoring {
	
	public static void pageRank(String path,double ref){
		
		Graph graph = new OrientGraph("local:"+path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<Graph>(graph), ref);
		long start=System.currentTimeMillis();
		pageRank.step();
		//pageRank.step();
		//pageRank.step();
		double i=0;
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		for (Vertex vertex : graph.getVertices()) {
			if(i>=10)
				break;
		  System.out.println("The PageRank score of " + vertex + " is: " + pageRank.getVertexScore(vertex));
		  
		  i++;
		}
		System.out.println("Iterations number: "  + pageRank.getIterations());
		
		System.out.println("MAX Iterations number: "  + pageRank.getMaxIterations());
		System.out.println("total nodes: "+ i);
		
	}
	
	/*public static void BetweennessCentrality(String path,double ref){
		OrientGraph graph = OrientGraph(path); // construct a particular Blueprints graph implementation
		BetweennessCentrality<Vertex,Edge> BetweennessCentrality = new BetweennessCentrality<Vertex, Edge>(new GraphJung<OrientGraph>(graph), ref);
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
	public static void ClosenessCentrality(String path,double ref){
		OrientGraph graph = new OrientGraph("local:"+path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<OrientGraph>(graph), ref);
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
		
	}
	public static void DegreeScorer(String path,double ref){
		OrientGraph graph = new OrientGraph("local:"+path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<OrientGraph>(graph), ref);
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
		
	}
	
	public static void HITS(String path,double ref){
		OrientGraph graph = new OrientGraph("local:"+path); // construct a particular Blueprints graph implementation
		PageRank<Vertex,Edge> pageRank = new PageRank<Vertex, Edge>(new GraphJung<OrientGraph>(graph), ref);
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
		
	}
	
public static void main(String args[]){
	for(int i=0;i<5;i++)
	pageRank("/Users/yliu/Documents/ca-AstroPh_Orient",0.15d);
	
	
}
}
