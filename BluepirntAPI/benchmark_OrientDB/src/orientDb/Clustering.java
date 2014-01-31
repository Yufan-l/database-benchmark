package orientDb;

import java.util.Set;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.*;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;

import edu.uci.ics.jung.algorithms.cluster.WeakComponentClusterer;

public class Clustering {
		public static void WeakComponent(String dir){
		OrientGraph graph = new OrientGraph("local:"+dir);
		
		long start=System.currentTimeMillis();
		WeakComponentClusterer<Vertex,Edge> WC =new WeakComponentClusterer<Vertex,Edge>();
		Set<Set<Vertex>> rs=WC.transform(new GraphJung<OrientGraph>(graph));
		System.out.println(rs.size());
/*		int total=0;
		for(Set<Vertex>s1:rs){
			total+=s1.size();
		}
		System.out.println(total);*/
		System.out.println(System.currentTimeMillis()-start);
		graph.shutdown();
		
	}
		
		public static void main(String args[]){
			
			WeakComponent("/Users/yliu/Documents/ca-AstroPh_Orient");
		}
}
