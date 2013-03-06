package benchmarks;
import java.util.*;
//import java.io.*;
//import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.pgm.impls.neo4j.*;
import com.tinkerpop.blueprints.pgm.*;
import com.tinkerpop.blueprints.Direction;




public class DFSNeo4j {
	
	public static int Neo4jBfsPath(Graph graph, int source, int target, int MaxLength)throws Exception
	{
		Vertex sourceV=graph.getVertex(source);	
		LinkedList<Vertex> CurrentBranch=new LinkedList<Vertex>();
		LinkedList<Vertex> NextBranch=new LinkedList<Vertex>();
		HashSet<Vertex> Visited=new HashSet<Vertex>();
		CurrentBranch.add(sourceV);
		
		//ArrayList<Vertex> path=new ArrayList<Vertex>();
		//path.add(sourceV);
		int length=0;
		while(!CurrentBranch.isEmpty())
		{
			if(length>MaxLength)
			{
				return -1;
			}
			Vertex e=CurrentBranch.pop();
			
			if(e.equals(graph.getVertex(target)))
			{
				return length;
			}
			
//			while(e.)
//			{
//				
//			}
			
			
			
			if(CurrentBranch.isEmpty())
			{
				if(NextBranch.isEmpty())
				{
					break;
				}
				length++;
				CurrentBranch.addAll(NextBranch);
//				for(int i=0;i<CurrentLevel.size();i++)
//				{
//					System.out.print(CurrentLevel.get(i).getId()+" ");				
//				}
//				System.out.println();
				NextBranch.clear();
			}
		}
		
		return -2;
		
		
	}
	
	
	public static void main(String args[])throws Exception
	{
		Graph graph = new Neo4jGraph("C:/tmp/neo4j");
		Vertex a = graph.addVertex(null);
		Vertex b = graph.addVertex(null);
		Vertex c = graph.addVertex(null);
		Vertex d = graph.addVertex(null);
		Vertex e = graph.addVertex(null);
		Vertex f = graph.addVertex(null);
		
		Edge e1 = graph.addEdge(null, a, b, "knows");
		Edge e2 = graph.addEdge(null, b, e, "knows");
		Edge e3 = graph.addEdge(null, e, f, "knows");
		Edge e4 = graph.addEdge(null, a, c, "knows");
		Edge e5 = graph.addEdge(null, c, f, "knows");	
		Edge e6 = graph.addEdge(null, a, d, "knows");
		
		int rs=Neo4jBfsPath(graph,1,6,5);
		System.out.println(rs);
		graph.shutdown();
		
		
			
		
	}
	
	
	

}
