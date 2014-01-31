package neo4j;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.neo4j.graphdb.*;
import org.neo4j.*;

import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;
import com.tinkerpop.blueprints.oupls.jung.JungHelper;

import edu.uci.ics.jung.algorithms.cluster.*;
import edu.uci.ics.jung.*;
import edu.uci.ics.jung.algorithms.util.*;


public class Clustering {
	public static void WeakComponent(String dir){
		
		Neo4jGraph graph = new Neo4jGraph(dir);
		long start=System.currentTimeMillis();
		WeakComponentClusterer<Vertex,Edge> WC =new WeakComponentClusterer<Vertex,Edge>();
		Set<Set<Vertex>> rs=WC.transform(new GraphJung<Neo4jGraph>(graph));
		System.out.println(rs.size());
/*		int total=0;
		for(Set<Vertex>s1:rs){
			total+=s1.size();
		}
		System.out.println(total);*/
		System.out.println(System.currentTimeMillis()-start);
		graph.shutdown();
		
	}
	
	public static void Voltage(String dir){
		
		Neo4jGraph graph = new Neo4jGraph("/Users/yliu/Documents/ca-AstroPh_Neo");
		long start=System.currentTimeMillis();
		VoltageClusterer <Vertex,Edge> VC= new VoltageClusterer<Vertex,Edge>(new GraphJung<Neo4jGraph>(graph), 3);
		
		Collection<Set<Vertex>> rs=VC.cluster(3);
	
		System.out.println("total time: "+ (System.currentTimeMillis()-start));
		//System.out.println(rs.size());
		int total=0;
		for(Set<Vertex>s1:rs){
			System.out.println(s1.size());
		}
		
		graph.shutdown();
		
	}
	public static void main(String args[]){

		//WeakComponent("/Users/yliu/Documents/ca-AstroPh_Neo");
		
		Voltage("/Users/yliu/Documents/ca-AstroPh_Neo");
		
	}
	

}
