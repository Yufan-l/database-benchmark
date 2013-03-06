package tinkerpop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class TwitterAddEdge {
	public static void main(String args[])throws Exception
	{
		Scanner SC=new Scanner(new FileReader("C:/twitter_rv.net"));
		File file = new File("C:/TwitterGraphmlExpendedSmall.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		final BufferedWriter bw = new BufferedWriter(fw);
		
		
		String tmp=" ";
		XStream xstream = new XStream(); 
		
		xstream.alias("edge", TwitterEdge.class);
		xstream.useAttributeFor(TwitterEdge.class, "ID");
		xstream.useAttributeFor(TwitterEdge.class, "source");
		xstream.useAttributeFor(TwitterEdge.class, "target");
		xstream.useAttributeFor(TwitterEdge.class, "label");

		xstream.aliasField("id", TwitterEdge.class, "ID");	
		xstream.aliasField("source", TwitterEdge.class, "source");	
		xstream.aliasField("target", TwitterEdge.class, "target");
		xstream.aliasField("label", TwitterEdge.class, "label");
		
		int startId=7765763;
		
		
		
		while(SC.hasNext())
		//for(int i=0;i<100;i++)
		{
			tmp=SC.nextLine();
			String content[]=tmp.split("\t");
			
			if(content[0].equals("7765762"))
			{
				break;
			}
			
			if(Integer.parseInt(content[1])>7765762)
			{
				continue;
			}
			
			TwitterEdge T1=new TwitterEdge(Integer.toString(startId));
			T1.SetSource(content[0]);
			T1.SetTarget(content[1]);
			
			String xml = xstream.toXML(T1).replace("/>", "></edge>");
			
			
			//System.out.println(xml);	
			bw.write(xml);
			bw.newLine();
			
			startId++;
		}
		
		SC.close();
		bw.close();
		
		
	}

}
