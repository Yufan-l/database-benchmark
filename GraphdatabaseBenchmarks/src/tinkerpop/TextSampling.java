package tinkerpop;
import java.util.*;
import java.io.*;


public class TextSampling {
	
	public static void main(String args[]) throws Exception
	{
		int SampleLen=1000;
		
		Scanner SC=new Scanner(new FileReader("C:/amazon-meta.txt"));
		
		File file = new File("C:/amazon-metaSample.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		final BufferedWriter bw = new BufferedWriter(fw);
		
		
		for (int i=0;i<SampleLen;i++)
		{
			if(!SC.hasNext())
				break;
			bw.write(SC.nextLine());
			bw.newLine();
		}
		bw.close();
		SC.close();
	}

}
