package tinkerpop;
import java.util.*;
import java.io.*;


public class TwitterLoading {
	public static void main(String args[])throws Exception
	{
		Scanner SC=new Scanner(new FileReader("C:/twitter_rv.net"));
		File file = new File("C:/Twitter_edge.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		final BufferedWriter bw=new BufferedWriter(fw);
		
		long count=0;
		String tmp=" ";
		//for(int i=0;i<1000;i++)
		while(SC.hasNext())
		{
			tmp=SC.nextLine();
			String content[]=tmp.split("\t");
			if(content[0].equals("13"))
			{
				break;
			}
			//System.out.println(tmp);	
			//bw.write(SC.nextLine());
			//bw.newLine();
			count++;
		}
		System.out.println(count);
		SC.close();
		bw.close();
	}

}
