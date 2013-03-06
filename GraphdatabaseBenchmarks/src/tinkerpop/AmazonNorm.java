package tinkerpop;
import java.util.*;
import java.io.*;
import com.thoughtworks.xstream.XStream;
import java.text.Normalizer;
import java.util.regex.*;

public class AmazonNorm {
	
	
	
	public static String stripDiacritics(String str) throws Exception{
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        String regex = "[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+";
        String s2 = new String(str.replaceAll(regex, "").getBytes("ascii"), "ascii");
        return s2;
}

	
	public static void main(String args[])throws Exception
	{
		
		
		Scanner SC=new Scanner(new FileReader("C:/amazon-meta.txt"));
		
		File file = new File("C:/amazon-metaGraphml.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		final BufferedWriter bw = new BufferedWriter(fw);
		String header="<?xml version=\"1.0\" ?>"
	+"\n<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\">"
    +"<key id=\"ASIN\" for=\"node\" attr.name=\"ASIN\" attr.type=\"string\"></key>"
    +"<key id=\"title\" for=\"node\" attr.name=\"title\" attr.type=\"string\"></key>"
    +"<key id=\"group\" for=\"node\" attr.name=\"group\" attr.type=\"string\"></key>"
    +"<key id=\"similar\" for=\"node\" attr.name=\"similar\" attr.type=\"string\"></key>"
    +"<key id=\"salesrank\" for=\"node\" attr.name=\"salesrank\" attr.type=\"int\"></key>"
    +"<graph id=\"G\" edgedefault=\"directed\">";
		
		bw.write(header);
		bw.newLine();
		
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
			
			if (tmp.contains("Id: "))
				break;
			tmp=SC.nextLine();			
		}
		
		String []content=tmp.split("\\W+");
		
		N1=new Node(content[1]);
		
		tmp=SC.nextLine();
		
		
		
		while(SC.hasNext())
		{
			while(!tmp.contains("Id: ")&&SC.hasNext())
			{
				if(tmp.contains("title: "))
				{
					N1.SetTitle(stripDiacritics(tmp.replace("title: ", "").replaceFirst("  ", "")));
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
			
			String xml = xstream.toXML(N1).replace("/data key=\"ASIN\"", "/data").replace("/data key=\"title\"", "/data").replace("/data key=\"group\"", "/data").replace("/data key=\"salesrank\"", "/data").replace("/data key=\"similar\"", "/data");
			
			//System.out.println(xml);
			
			bw.write(xml);
			bw.newLine();
			
			String []content1=tmp.split("\\W+");
			
			N1=new Node(content1[1]);		
			
			tmp=SC.nextLine();
				
		}

		SC.close();
		bw.close();
	}
}
