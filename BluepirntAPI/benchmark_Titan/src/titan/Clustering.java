package titan;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import com.tinkerpop.blueprints.oupls.jung.GraphJung;

import edu.uci.ics.jung.algorithms.cluster.VoltageClusterer;
import edu.uci.ics.jung.algorithms.cluster.WeakComponentClusterer;

public class Clustering {
	
public static void WeakComponent(String dir){
		
		TitanGraph graph = TitanFactory.open(dir);
		long start=System.currentTimeMillis();
		WeakComponentClusterer<Vertex,Edge> WC =new WeakComponentClusterer<Vertex,Edge>();
		Set<Set<Vertex>> rs=WC.transform(new GraphJung<TitanGraph>(graph));
		System.out.println(rs.size());
/*		int total=0;
		for(Set<Vertex>s1:rs){
			total+=s1.size();
		}
		System.out.println(total);*/
		System.out.println(System.currentTimeMillis()-start);
		graph.shutdown();
		
	}

public static void WeakComponent(Configuration conf){
	
	TitanGraph graph = TitanFactory.open(conf);
	long start=System.currentTimeMillis();
	WeakComponentClusterer<Vertex,Edge> WC =new WeakComponentClusterer<Vertex,Edge>();
	Set<Set<Vertex>> rs=WC.transform(new GraphJung<TitanGraph>(graph));
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
	
	TitanGraph graph = TitanFactory.open(dir);
	long start=System.currentTimeMillis();
	VoltageClusterer <Vertex,Edge> VC= new VoltageClusterer<Vertex,Edge>(new GraphJung<TitanGraph>(graph), 3);
	
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
	
	Configuration conf = new BaseConfiguration();
	
	//conf.setProperty("storage.port","2182");
	
	//conf.setProperty("storage.backend","hbase");
	
	//conf.setProperty("storage.tablename","ca-AstroPh");
	
	conf.setProperty("storage.backend","cassandra");
	conf.setProperty("storage.hostname","127.0.0.1");
	 
	//WeakComponent(conf);
	
	WeakComponent("/Users/yliu/Documents/ca-AstroPh_Titan");
	
	//Voltage("/Users/yliu/Documents/ca-AstroPh_Titan");
}

}
