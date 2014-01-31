package titan;

import java.util.Iterator;
import java.util.Random;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import orientDb.ShortestPath_Orient;
import neo4j.ShortestPath_Neo4j;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.graphdb.blueprints.TitanBlueprintsGraph;
//import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import com.tinkerpop.blueprints.oupls.jung.GraphJung;
import com.tinkerpop.blueprints.oupls.jung.JungHelper;

import edu.uci.ics.jung.algorithms.shortestpath.*;
import edu.uci.ics.jung.algorithms.scoring.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.*;


public class ShortestPath {

	public static void Unweighted(String path,String S,String T,String property){

		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		UnweightedShortestPath<Vertex,Edge> USP = new UnweightedShortestPath<Vertex, Edge>(new GraphJung<TitanGraph>(graph));



		Vertex source=graph.query().has(property,Cmp.EQUAL,S).vertices().iterator().next();
		Vertex target=graph.query().has(property,Cmp.EQUAL,T).vertices().iterator().next();

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

		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		DijkstraShortestPath<Vertex,Edge> USP = new DijkstraShortestPath<Vertex, Edge>(new GraphJung<TitanGraph>(graph));


		Vertex source=graph.query().has(property,Cmp.EQUAL,S).vertices().iterator().next();
		Vertex target=graph.query().has(property,Cmp.EQUAL,T).vertices().iterator().next();


		System.out.println("source is "+source);
		System.out.println("target is "+target);
		long start=System.currentTimeMillis();
		int dis=USP.getDistance(source, target).intValue();
		//pageRank.evaluate();

		System.out.println("total time: "+ (System.currentTimeMillis()-start));

		System.out.println("The distance is " + dis);

		graph.shutdown();
	}

	public static void Unweighted(Configuration conf,String S,String T,String property){

		TitanGraph graph = TitanFactory.open(conf); // construct a particular Blueprints graph implementation
		UnweightedShortestPath<Vertex,Edge> USP = new UnweightedShortestPath<Vertex, Edge>(new GraphJung<TitanGraph>(graph));



		Vertex source=graph.query().has(property,Cmp.EQUAL,S).vertices().iterator().next();
		Vertex target=graph.query().has(property,Cmp.EQUAL,T).vertices().iterator().next();

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
		
		TitanGraph graph = TitanFactory.open(path); // construct a particular Blueprints graph implementation
		
		long start=System.currentTimeMillis();
		Vertex source=graph.query().has(property,S).vertices().iterator().next();
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		

		graph.shutdown();  	
	}
	
	public static void query(Configuration conf,String S,String property){
		
		TitanGraph graph = TitanFactory.open(conf); // construct a particular Blueprints graph implementation
		
		long start=System.currentTimeMillis();
		Vertex source=graph.query().has(property,S).vertices().iterator().next();
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		

		graph.shutdown();  	
	}
	public static void Dijkstra(Configuration conf,String S,String T,String property){

		TitanGraph graph = TitanFactory.open(conf); // construct a particular Blueprints graph implementation
		DijkstraShortestPath<Vertex,Edge> USP = new DijkstraShortestPath<Vertex, Edge>(new GraphJung<TitanGraph>(graph));


		Vertex source=graph.query().has(property,Cmp.EQUAL,S).vertices().iterator().next();
		Vertex target=graph.query().has(property,Cmp.EQUAL,T).vertices().iterator().next();


		System.out.println("source is "+source);
		System.out.println("target is "+target);
		long start=System.currentTimeMillis();
		int dis=USP.getDistance(source, target).intValue();
		//pageRank.evaluate();

		System.out.println("total time: "+ (System.currentTimeMillis()-start));

		System.out.println("The distance is " + dis);

		graph.shutdown();
	}

	public static boolean CheckNodes(String path,String S,String T,String property){
		TitanGraph graph = TitanFactory.open(path);
		Iterator<Vertex> itr=graph.query().has(property,Cmp.EQUAL,S).vertices().iterator();
		if(!itr.hasNext()){
			graph.shutdown();
			return false;
		}
		Iterator<Vertex> itr1=graph.query().has(property,Cmp.EQUAL,T).vertices().iterator();
		if(!itr1.hasNext()){
			graph.shutdown();
			return false;
		}
		graph.shutdown();
		return true;
		
	}
	
	public static void printSeperator(String name){
		System.out.println("====================================");
		System.out.println(name);
		System.out.println("====================================");
		
	}

	public static void main(String args[]){
		//pageRank("/Users/yliu/Documents/ca-AstroPh_titan",0.15d);
		//Unweighted("/Users/yliu/Documents/ca-AstroPh_titan","23","342","name");
		String path="/Users/yliu/Documents/ca-AstroPh_titan";
		String path2="/Users/yliu/Documents/ca-AstroPh_Neo";
		String path3="local:/Users/yliu/Documents/ca-AstroPh_Orient";

		Configuration conf = new BaseConfiguration();

		//conf.setProperty("storage.port","2182");

		//conf.setProperty("storage.backend","hbase");
		
		conf.setProperty("storage.backend","cassandra");
		conf.setProperty("storage.hostname","127.0.0.1");

		conf.setProperty("storage.tablename","ca-AstroPh1");

		Random rd=new Random();
		int a=-1;
		int b=-1;
		boolean cont=true;

		for(int i=0;i<=100;i++){

			a=rd.nextInt(100000);
			b=rd.nextInt(100000);
			if(CheckNodes(path,Integer.toString(a),Integer.toString(b),"Name"))
				break;
			if(i==10000)
				cont=false;
		}
		if(cont){
			//query(path,Integer.toString(a),"Name");
			//query(conf, Integer.toString(a),"Name");
			//ShortestPath_Neo4j.query(path2,Integer.toString(a),"Name");
			//ShortestPath_Orient.query(path3, Integer.toString(a), "Name");
			/*Unweighted(path, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Titan");
			Dijkstra(path, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Titan");
			Unweighted(conf, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Titan-HBase");
			Dijkstra(conf, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Titan-HBase");
			ShortestPath_Neo4j.Unweighted(path2, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Neo4j");
			ShortestPath_Neo4j.Dijkstra(path2, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Neo4j");
			ShortestPath_Orient.Unweighted(path3, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Orient");
			ShortestPath_Orient.Dijkstra(path3, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Orient");
			*/
			
			printSeperator("Titan");
			Dijkstra(conf, Integer.toString(a), Integer.toString(b), "Name");
			Unweighted(conf, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Titan");
			
			printSeperator("Titan");
			Dijkstra(conf, Integer.toString(a), Integer.toString(b), "Name");
			Unweighted(conf, Integer.toString(a), Integer.toString(b), "Name");
			printSeperator("Titan");
			
		}

	}
}
