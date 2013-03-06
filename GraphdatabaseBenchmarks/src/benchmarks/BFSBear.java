package benchmarks;
//package com.cbdb.benchmark.algorithm;
//
//import java.util.*;
//import com.tinkerpop.blueprints.*;
////add dependencies here
//
//
	public class BFSBear {
//	
//	public static int BfsPath(BearGraph graph, int source, int target, int MaxLength)throws Exception
//	{
//		BearVertex sourceV=graph.getVertex(source);	
//		LinkedList<BearVertex> CurrentLevel=new LinkedList<BearVertex>();
//		LinkedList<BearVertex> NextLevel=new LinkedList<BearVertex>();
//		HashSet<BearVertex> CircleDetect=new HashSet<BearVertex>();
//		
//		//push the source node to the queue
//		CurrentLevel.add(sourceV);
//		
//		//ArrayList<Vertex> path=new ArrayList<Vertex>();
//		//path.add(sourceV);
//		
//		int length=0;
//		
//		while(!CurrentLevel.isEmpty())
//		{
//			if(length>MaxLength)
//			{
//				return -1;
//			}
//			
//			//dequeue the first head node
//			BearVertex e=CurrentLevel.poll();
//			
//			if(e.equals(graph.getVertex(target)))
//			{
//				return length;
//			}
//			
//			//push every neighbor of current node to the next queue
//			for(BearVertex e1 : e.getVertices(Direction.OUT)) 
//			{
//				//circle detection
//				if (CircleDetect.contains(e1))
//				{
//					continue;
//				}
//				
//				CircleDetect.add(e1);
//				
//				NextLevel.add(e1);
//			}
//			
//			if(CurrentLevel.isEmpty())
//			{
//				//no more nodes to scan
//				if(NextLevel.isEmpty())
//				{
//					break;
//				}
//				length++;
//				CurrentLevel.addAll(NextLevel);
//				NextLevel.clear();
//			}
//		}
//		
//		return -2;
//		
//		
//	}
//	
//	
//	
//	
//	
//	public static void main(String args[])throws Exception
//	{
//		BearGraph graph = new BearGraph("tmp/Bear");
//		BearVertex a = graph.addVertex(null);
//		System.out.println(a.getId());
//		BearVertex b = graph.addVertex(null);
//		System.out.println(b.getId());
//		BearVertex c = graph.addVertex(null);
//		System.out.println(c.getId());
//		BearVertex d = graph.addVertex(null);
//		System.out.println(d.getId());
//		BearVertex e = graph.addVertex(null);
//		System.out.println(e.getId());
//		BearVertex f = graph.addVertex(null);
//		System.out.println(f.getId());
//		
//		BearEdge e1 = graph.addEdge(null, a, b, "knows");
//		BearEdge e2 = graph.addEdge(null, b, e, "knows");
//		BearEdge e3 = graph.addEdge(null, e, f, "knows");
//		BearEdge e4 = graph.addEdge(null, a, c, "knows");
//		BearEdge e5 = graph.addEdge(null, c, f, "knows");	
//		BearEdge e6 = graph.addEdge(null, a, d, "knows");
//		
//		int rs=BfsPath(graph,0,5,5);
//		System.out.println(rs);
//		
//
//		graph.shutdown();
//		
//		
//			
//		
//	}
	
	
	

}
