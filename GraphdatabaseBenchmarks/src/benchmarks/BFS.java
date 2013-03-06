package benchmarks;
import java.util.*;
//import java.io.*;
//import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;





public class BFS {
	
	public static int BfsPath(Graph graph, int source, int target, int MaxLength)throws Exception
	{
		Vertex sourceV=graph.getVertex(source);	
		LinkedList<Vertex> CurrentLevel=new LinkedList<Vertex>();
		LinkedList<Vertex> NextLevel=new LinkedList<Vertex>();
		HashSet<Vertex> CircleDetect=new HashSet<Vertex>();
		CurrentLevel.add(sourceV);
		
		//ArrayList<Vertex> path=new ArrayList<Vertex>();
		//path.add(sourceV);
		int length=0;
		while(!CurrentLevel.isEmpty())
		{
			if(length>MaxLength)
			{
				return -1;
			}
			Vertex e=CurrentLevel.poll();
			if(e.equals(graph.getVertex(target)))
			{
				return length;
			}
			
			for(Vertex e1 : e.getVertices(Direction.OUT)) 
			{
				if (CircleDetect.contains(e1))
				{
					continue;
				}
				CircleDetect.add(e1);
				NextLevel.add(e1);
			}
			
			if(CurrentLevel.isEmpty())
			{ 
				if(NextLevel.isEmpty())
				{
					break;
				}
				length++;
				CurrentLevel.addAll(NextLevel);
				NextLevel.clear();
			}
		}
		
		return -2;
		
		
	}
	
	
	
	
	
	public static void main(String args[])throws Exception
	{
		Graph graph = new TinkerGraph("C:/tmp/Tinker2");
//		Vertex a = graph.addVertex(null);
//		System.out.println(a.getId());
//		Vertex b = graph.addVertex(null);
//		System.out.println(b.getId());
//		Vertex c = graph.addVertex(null);
//		System.out.println(c.getId());
//		Vertex d = graph.addVertex(null);
//		System.out.println(d.getId());
//		Vertex e = graph.addVertex(null);
//		System.out.println(e.getId());
//		Vertex f = graph.addVertex(null);
//		System.out.println(f.getId());
//		
//		Edge e1 = graph.addEdge(null, a, b, "knows");
//		Edge e2 = graph.addEdge(null, b, c, "knows");
//		Edge e3 = graph.addEdge(null, c, d, "knows");
//		Edge e4 = graph.addEdge(null, d, b, "knows");
//		Edge e5 = graph.addEdge(null, d, e, "knows");
//		Edge e6 = graph.addEdge(null, e, f, "knows");
//		
		Vertex k1 = graph.getVertex(0);
		Vertex k2 = graph.getVertex(5);
		int rs=BFS1.search(graph,k1,k2,5);
		System.out.println(rs);
		
		int rs1= BfsPath(graph,0,5,5);
		System.out.println(rs1);
		
		boolean rs2= DFS.search(graph, Direction.OUT, k1, k2, 5);
		System.out.println(rs2);
//		Vertex e=graph.getVertex(5);
//		
//		for(Vertex e1 : e.getVertices(Direction.OUT)) 
//		{
//			System.out.println(e1.getId());
//		}
		graph.shutdown();
		
		
			
		
	}
	
	
	

}
