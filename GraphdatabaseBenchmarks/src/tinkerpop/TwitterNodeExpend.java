package tinkerpop;

import java.util.*;
import java.io.*;
import com.thoughtworks.xstream.XStream;

public class TwitterNodeExpend {
	
	
	
	
	
	public static void main(String args[])throws Exception
	{
		
		
		
		
		File file = new File("C:/TwitterGraphmlExpended.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		final BufferedWriter bw = new BufferedWriter(fw);
		
		String tmp="";
		String tmp2="";
		
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

//		String KOP="";
//		int pop=0;
		Scanner SC2=new Scanner(new FileReader("C:/numeric2screen"));
		
		for(int i=0;i<50;i++)
		{
			Scanner SC=new Scanner(new FileReader("C:/celebrities_profiles.txt"));
			
			
			while(SC.hasNext())
			{	
			
				tmp=SC.nextLine();
				tmp2=SC2.nextLine();
				
				String content[]=tmp.split("\t");
				String content2[]=tmp2.split(" ");
				
				T1=new TwitterNode(content2[0]);
				T1.SetFollowers(content[4]);
//				if(Integer.parseInt(content[4])>pop)
//				{
//					KOP=tmp;
//					pop=Integer.parseInt(content[4]);
//				}
				T1.SetLocation(content[6]);
				T1.SetDescription(content[10]);
				T1.SetFriends_count(content[11]);
				T1.SetScreen_name(content2[1]);
				T1.SetFavourites_count(content[18]);
				T1.SetName(content[19]);
				T1.SetUrl(content[20]);
				T1.SetCreated_at(content[21]);
				T1.SetGender(content[25]);
				
				String xml = xstream.toXML(T1).replace("/data key=\"followersCount\"", "/data").replace("/data key=\"location\"", "/data").replace("/data key=\"description\"", "/data").replace("/data key=\"friendsCount\"", "/data").replace("/data key=\"screenName\"", "/data").replace("/data key=\"favouritesCount\"", "/data").replace("/data key=\"name\"", "/data").replace("/data key=\"url\"", "/data").replace("/data key=\"createdAt\"", "/data").replace("/data key=\"gender\"", "/data");
				
				bw.write(xml);
			
				bw.newLine();
				if(!SC2.hasNext())
				{
					break;		
				}
			}
			
			SC.close();
			
		}
		
		
		
		SC2.close();
		//bw.newLine();
		//String EndTag="</graph>\n</graphml>";
		//bw.write(EndTag);
		
	
		bw.close();
	}
}
