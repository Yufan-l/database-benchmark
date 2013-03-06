package tinkerpop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import com.thoughtworks.xstream.XStream;

public class RandomEdge {
	
	public static void main(String args[])throws Exception
	{
		File file = new File("C:/amazon-metaGraphmlLarge.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		final BufferedWriter bw = new BufferedWriter(fw);
		

		
		Edge E1;
		
		XStream xstream = new XStream();
		
		xstream.alias("edge", Edge.class);
		xstream.useAttributeFor(Edge.class, "ID");
		xstream.useAttributeFor(Edge.class, "source");
		xstream.useAttributeFor(Edge.class, "target");
		xstream.useAttributeFor(Edge.class, "label");

		xstream.aliasField("id", Edge.class, "ID");	
		xstream.aliasField("source", Edge.class, "source");	
		xstream.aliasField("target", Edge.class, "target");
		xstream.aliasField("label", Edge.class, "label");

		

	
		int StartId=39359399;

		
		
		for(int i=0;i<100000;i++)
		{
			Random rd=new Random();
			int SourceSeed=1+rd.nextInt(10);
			int TargetSeed=1+rd.nextInt(10);
			int SourceLocal=rd.nextInt(403394);
			int TargetLocal=rd.nextInt(403394);
			while(SourceSeed==TargetSeed)
			{
				TargetSeed=rd.nextInt(10);
			}
			
			E1=new Edge(Integer.toString(StartId));
		
			E1.SetSource(Integer.toString(SourceSeed*SourceLocal));

			E1.SetTarget(Integer.toString(TargetSeed*TargetLocal));
			
			String xml = xstream.toXML(E1).replace("/>", "></edge>");
			//System.out.println(xml);
			
			bw.write(xml);
			bw.newLine();
			
			StartId++;
				
		}
		
		bw.newLine();
		String EndTag="	</graph>\n</graphml>";
		bw.write(EndTag);


		bw.close();
		
	}

}
