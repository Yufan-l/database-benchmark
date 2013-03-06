package tinkerpop;

import java.util.*;
import java.io.*;
import com.thoughtworks.xstream.XStream;

public class TwitterNorm {
	
	
	
	
	
	public static void main(String args[])throws Exception
	{
		Scanner SC=new Scanner(new FileReader("C:/celebrities_profiles.txt"));
		
		File file = new File("C:/TwitterGraphml.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		final BufferedWriter bw = new BufferedWriter(fw);
		String header="<?xml version=\"1.0\" ?>"
				+"\n<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\">"
			    +"<key id=\"followersCount\" for=\"node\" attr.name=\"followersCount\" attr.type=\"int\"></key>"
			    +"<key id=\"location\" for=\"node\" attr.name=\"location\" attr.type=\"string\"></key>"
			    +"<key id=\"description\" for=\"node\" attr.name=\"description\" attr.type=\"string\"></key>"
			    +"<key id=\"friendsCount\" for=\"node\" attr.name=\"friendsCount\" attr.type=\"int\"></key>"
			    +"<key id=\"screenName\" for=\"node\" attr.name=\"screenName\" attr.type=\"int\"></key>"
			    +"<key id=\"favouritesCount\" for=\"node\" attr.name=\"favouritesCount\" attr.type=\"int\"></key>"
			    +"<key id=\"name\" for=\"node\" attr.name=\"name\" attr.type=\"string\"></key>"
			    +"<key id=\"url\" for=\"node\" attr.name=\"url\" attr.type=\"string\"></key>"
			    +"<key id=\"createdAt\" for=\"node\" attr.name=\"createdAt\" attr.type=\"string\"></key>"
			    +"<key id=\"gender\" for=\"node\" attr.name=\"gender\" attr.type=\"string\"></key>"
			    +"<graph id=\"G\" edgedefault=\"directed\">";
					
		bw.write(header);
		bw.newLine();
		String tmp="";
		
		TwitterNode T1;
		
		XStream xstream = new XStream();
		
		xstream.alias("node", TwitterNode.class);
		xstream.useAttributeFor(TwitterNode.class, "ID");
		
		xstream.aliasField("id", TwitterNode.class, "ID");	
		xstream.aliasField("data key=\"followersCount\"", TwitterNode.class, "followersCount");	
		xstream.aliasField("data key=\"location\"", TwitterNode.class, "location");
		xstream.aliasField("data key=\"description\"", TwitterNode.class, "description");
		xstream.aliasField("data key=\"friendsCount\"", TwitterNode.class, "friendsCount");
		xstream.aliasField("data key=\"screenName\"", TwitterNode.class, "screenName");
		xstream.aliasField("data key=\"favouritesCount\"", TwitterNode.class, "favouritesCount");
		xstream.aliasField("data key=\"name\"", TwitterNode.class, "name");
		xstream.aliasField("data key=\"url\"", TwitterNode.class, "url");
		xstream.aliasField("data key=\"createdAt\"", TwitterNode.class, "createdAt");
		xstream.aliasField("data key=\"gender\"", TwitterNode.class, "gender");

		String KOP="";
		int pop=0;
		while(SC.hasNext())
		{	
		
			tmp=SC.nextLine();
			String content[]=tmp.split("\t");
			T1=new TwitterNode(content[0]);
			T1.SetFollowers(content[4]);
			if(Integer.parseInt(content[4])>pop)
			{
				KOP=tmp;
				pop=Integer.parseInt(content[4]);
			}
			T1.SetLocation(content[6]);
			T1.SetDescription(content[10]);
			T1.SetFriends_count(content[11]);
			T1.SetScreen_name(content[16]);
			T1.SetFavourites_count(content[18]);
			T1.SetName(content[19]);
			T1.SetUrl(content[20]);
			T1.SetCreated_at(content[21]);
			T1.SetGender(content[25]);
			
			String xml = xstream.toXML(T1).replace("/data key=\"followersCount\"", "/data").replace("/data key=\"location\"", "/data").replace("/data key=\"description\"", "/data").replace("/data key=\"friendsCount\"", "/data").replace("/data key=\"screenName\"", "/data").replace("/data key=\"favouritesCount\"", "/data").replace("/data key=\"name\"", "/data").replace("/data key=\"url\"", "/data").replace("/data key=\"createdAt\"", "/data").replace("/data key=\"gender\"", "/data");
			
			bw.write(xml);
		
			bw.newLine();
		}
		
		//bw.newLine();
		//String EndTag="</graph>\n</graphml>";
		//bw.write(EndTag);
		
		SC.close();
		bw.close();
	}
}
