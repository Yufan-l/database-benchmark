package neo4j;
import java.util.Iterator;
import java.util.Random;

import org.neo4j.graphdb.*;
import org.neo4j.*;


import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;
import com.tinkerpop.blueprints.oupls.jung.JungHelper;

import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.algorithms.shortestpath.*;
import edu.uci.ics.jung.*;


public class ShortestPath_Neo4j {
public static void Unweighted(String path,String S,String T,String property){
		
		Neo4jGraph graph = new Neo4jGraph(path); // construct a particular Blueprints graph implementation
		UnweightedShortestPath<Vertex,Edge> USP = new UnweightedShortestPath<Vertex, Edge>(new GraphJung<Neo4jGraph>(graph));
		
		
		
		Vertex source=graph.query().has(property,S).vertices().iterator().next();
		Vertex target=graph.query().has(property,T).vertices().iterator().next();
		
		System.out.println("source is "+source);
		System.out.println("target is "+target);
		long start=System.currentTimeMillis();
		int dis=USP.getDistance(source, target).intValue();
		//pageRank.evaluate();
		
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		
		System.out.println("The distance is " + dis);
		  

		
		graph.shutdown();
	}
public static void Dijkstra(String path,String S,String T,String property){
		
		Neo4jGraph graph  = new Neo4jGraph(path); // construct a particular Blueprints graph implementation
		DijkstraShortestPath<Vertex,Edge> USP = new DijkstraShortestPath<Vertex, Edge>(new GraphJung<Neo4jGraph>(graph));
		
		
		Vertex source=graph.query().has(property,S).vertices().iterator().next();
		Vertex target=graph.query().has(property,T).vertices().iterator().next();
		
		
		System.out.println("source is "+source);
		System.out.println("target is "+target);
		long start=System.currentTimeMillis();
		int dis=USP.getDistance(source, target).intValue();
		//pageRank.evaluate();
		
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		
		System.out.println("The distance is " + dis);
		graph.shutdown();  	
	}

public static void query(String path,String S,String property){
	
	Neo4jGraph graph  = new Neo4jGraph(path); // construct a particular Blueprints graph implementation
	
	long start=System.currentTimeMillis();
	Vertex source=graph.query().has(property,S).vertices().iterator().next();
	System.out.println("total time: "+ (System.currentTimeMillis()-start));
	

	graph.shutdown();  	
}


public static boolean CheckNodes(String path,String S,String T,String property){
	Neo4jGraph graph =  new Neo4jGraph(path);
	Iterator<Vertex> itr=graph.query().has(property,S).vertices().iterator();
	if(!itr.hasNext()){
		graph.shutdown();
		return false;
	}
	Iterator<Vertex> itr1=graph.query().has(property,T).vertices().iterator();
	if(!itr1.hasNext()){
		graph.shutdown();
		return false;
	}
	graph.shutdown();
	return true;
}


	
public static void main(String args[]){
	//pageRank("/Users/yliu/Documents/ca-AstroPh_titan",0.15d);
	//Unweighted("/Users/yliu/Documents/ca-AstroPh_titan","23","342","name");
	String path="/Users/yliu/Documents/ca-AstroPh_Neo";
	Random rd=new Random();
	int a=-1;
	int b=-1;
	boolean cont=true;
	for(int i=0;i<=10000;i++){
	
	a=rd.nextInt(100000);
	b=rd.nextInt(100000);
	if(CheckNodes(path,Integer.toString(a),Integer.toString(b),"Name"))
		break;
	if(i==10000)
		cont=false;
	}
	if(cont){
		Unweighted(path, Integer.toString(a), Integer.toString(b), "Name");
		Dijkstra(path, Integer.toString(a), Integer.toString(b), "Name");
	}
		
}


}
