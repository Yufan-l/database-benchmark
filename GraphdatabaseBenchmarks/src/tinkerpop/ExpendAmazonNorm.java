package tinkerpop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class ExpendAmazonNorm {

	public static void EAN(int StartId) throws Exception
	{
		Scanner SC=new Scanner(new FileReader("C:/amazon-meta.txt"));
		File file = new File("C:/amazon-metaGraphml.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		final BufferedWriter bw = new BufferedWriter(fw);
		
		String tmp="";
		
		Node N1;
		
		XStream xstream = new XStream();
		
		xstream.alias("node", Node.class);
		xstream.useAttributeFor(Node.class, "ID");
		
		xstream.aliasField("id", Node.class, "ID");	
		xstream.aliasField("data key=\"ASIN\"", Node.class, "ASIN");	
		xstream.aliasField("data key=\"title\"", Node.class, "title");
		xstream.aliasField("data key=\"group\"", Node.class, "group");
		xstream.aliasField("data key=\"salesrank\"", Node.class, "salesrank");
		xstream.aliasField("data key=\"similar\"", Node.class, "similar");
		
		while(SC.hasNext())
		{
			
			if (tmp.contains("Id:  "))
				break;
			tmp=SC.nextLine();			
		}
		
		String content[]=tmp.split("\\W+");
		int newId=Integer.parseInt(content[1])+StartId;
		N1=new Node(Integer.toString(newId));
		
		tmp=SC.nextLine();
		
		
		
		while(SC.hasNext())
		{
			while(!tmp.contains("Id:  ")&&SC.hasNext())
			{
				if(tmp.contains("title: "))
				{
					N1.SetTitle(tmp.replace("title: ", "").replaceFirst("  ", ""));
				}
				
				if(tmp.contains("group: "))
				{
					N1.SetGroup(tmp.replace("group: ", "").replaceFirst("  ", ""));
				}
				
				if(tmp.contains("salesrank: "))
				{
					N1.SetSalesrank(tmp.replace("salesrank: ", "").replaceFirst("  ", ""));
				} 
				
				if(tmp.contains("ASIN: "))
				{
					N1.SetASIN(tmp.replace("ASIN: ", ""));
				}		
				
				if(tmp.contains("similar: "))
				{
					N1.SetSimilar(tmp.replace("similar: ", "").replaceFirst("  ", ""));
				}	
				
				tmp=SC.nextLine();
				
			}
			
			String xml = xstream.toXML(N1).replace("/data key=\"ASIN\"", "/data").replace("/data key=\"title\"", "/data").replace("/data key=\"group\"", "/data").replace("/data key=\"salesrank\"", "/data").replace("/data key=\"similar\"", "/data");;
			
			//System.out.println(xml);
			
			bw.write(xml);
			bw.newLine();
			
			String content1[]=tmp.split("\\W+");
			
			
			int newId1=Integer.parseInt(content1[1])+StartId;
			N1=new Node(Integer.toString(newId1));
		
			tmp=SC.nextLine();
				
		}

		SC.close();
		bw.close();
	}
	
	public static void main(String args[]) throws Exception
	{
		int init=548552;
		for (int i=1;i<10;i++)
		{
			EAN(i*init);
		}
	}
}
