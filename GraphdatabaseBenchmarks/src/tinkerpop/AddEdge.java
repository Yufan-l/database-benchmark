package tinkerpop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

import java.text.Normalizer;

public class AddEdge {

	public static void AE(int StartId,int nodeExtended) throws Exception
	{
		Scanner SC=new Scanner(new FileReader("C:/Amazon0601.txt"));
		File file = new File("C:/amazon-metaGraphml.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		final BufferedWriter bw = new BufferedWriter(fw);
		
		String tmp="";
		
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

		
		while(SC.hasNext())
		{
			
			if (tmp.contains("FromNodeId"))
				break;
			tmp=SC.nextLine();			
		}
		tmp=SC.nextLine();
	
		
		while(SC.hasNext())
		//for(int i=0;i<100;i++)
		{
			String []content=tmp.split("	");
			
			E1=new Edge(Integer.toString(StartId));
			int NumTmp=Integer.parseInt(content[0])+nodeExtended;
			E1.SetSource(Integer.toString(NumTmp));
			NumTmp=Integer.parseInt(content[1])+nodeExtended;
			E1.SetTarget(Integer.toString(NumTmp));
			
			String xml = xstream.toXML(E1).replace("/>", "></edge>");
			//System.out.println(xml);
			
			bw.write(xml);
			bw.newLine();
			
			tmp=SC.nextLine();
			
			StartId++;
				
		}
		
		bw.newLine();
		String EndTag="	</graph>\n</graphml>";
		bw.write(EndTag);

		SC.close();
		bw.close();
	}
	
	
	
	
	public static void main(String args[]) throws Exception
	{
		int init=403394;
		int Edgeinit=5485520;
		int EdgeNum=3387388;
//		AE(548552,0);
//		for(int i=0;i<10;i++)
//		{
//			AE(Edgeinit+i*EdgeNum,i*init);
//		}
//		
//		File file = new File("C:/amazon-metaGraphmlLarge.txt");
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
//		final BufferedWriter bw = new BufferedWriter(fw);
//		bw.newLine();
//		String EndTag="	</graph>\n</graphml>";
//		bw.write(EndTag);
//		bw.close();
		String ha="Nave: Ã?ndice TemÃÂ¡tico De La Biblia";
		System.out.println(Normalizer.normalize(ha,Normalizer.Form.NFD));
		System.out.println(Normalizer.isNormalized("Nave: A?ndice TemA?tico De La Biblia", Normalizer.Form.NFD));
		System.out.println(Normalizer.isNormalized(Normalizer.normalize(ha,Normalizer.Form.NFD),Normalizer.Form.NFD));
		
		
	}
}
