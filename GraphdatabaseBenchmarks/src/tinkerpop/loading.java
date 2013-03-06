package tinkerpop;
//import com.tinkerpop.blueprints.pgm.*;
//import com.tinkerpop.blueprints.pgm.impls.neo4j.*;
//import com.tinkerpop.blueprints.Edge;
//import com.tinkerpop.blueprints.TransactionalGraph.Conclusion;
//import com.tinkerpop.blueprints.Vertex;
//import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import java.util.*;
import java.io.*;

public class loading {
	
	
	public static void main(String args[])throws Exception
	{
		

		int kk=548551;
		
		System.out.print(kk);
		String k1="";
		String k2="";
		String k3="";
		String k4="";
		
		Scanner SC=new Scanner(new FileReader("C:/amazon-metaGraphmlLarge.txt"));
		
		while(SC.hasNext())
		{
			k1=SC.nextLine();
			if (!SC.hasNext())
			{
				break;
			}
			k2=SC.nextLine();
			if (!SC.hasNext())
			{
				break;
			}
			k3=SC.nextLine();
			if (!SC.hasNext())
			{
				break;
			}
			k4=SC.nextLine();
		}
		
		System.out.println(k1);
		System.out.println(k2);
		System.out.println(k3);
		System.out.println(k4);
		SC.close();
		
//		for(int i=0;i<200;i++)
//		{
//			Random rd=new Random();
//			int SourceSeed=0;
//			int TargetSeed=0;
//			SourceSeed=rd.nextInt(10);
//			TargetSeed=rd.nextInt(10);
//			
//			while(SourceSeed==TargetSeed)
//			{
//				TargetSeed=rd.nextInt(10);
//			}
//	
//			System.out.println(SourceSeed+" "+TargetSeed);
//		}
		
		
		
		
		
//		Scanner SC=new Scanner(new FileReader("C:/amazon-meta.txt"));
//
//		while(SC.hasNext())
//		{  
//			String newline=SC.nextLine();
//			String contents[]=newline.split("\\W+");
//			if (contents[0].equals("Id"))
//			{
//				System.out.print("ID:"+contents[1]+" ");
//				String ASIN=SC.nextLine().replace("ASIN: ", "");
//				System.out.print("ASIN:"+ASIN+" ");
//				String title=SC.nextLine().replace("title: ", "");
//				System.out.print("title:"+title+" ");
//				String group=SC.nextLine().replace("group: ", "");
//				System.out.print("group:"+group+" ");
//				String salesrank=SC.nextLine().replace("salesrank: ", "");
//				System.out.print("salesrank:"+salesrank+" ");
//				System.out.println("");
//			}
//			
//			
//		}
	
		
		
//		Graph graph = new Neo4jGraph("/home/yufan/temp/neograph");
//		Vertex a =  graph.addVertex(null);
//		Vertex b =  graph.addVertex(null);
//		a.setProperty("name","marko");
//		b.setProperty("name","peter");
//		System.out.println( "Created vertex: " + a.getId() );
//		System.out.println( "Created vertex: " + b.getId() );
//		Edge e = graph.addEdge(null, a, b, "knows");
//		e.setProperty("since", 2006);
//		graph.shutdown();
		
	}
	
	

}
