package titan;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;


public class hbaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration conf = new BaseConfiguration();
		
		//conf.setProperty("storage.port","2182");
		
		//conf.setProperty("storage.backend","hbase");
		
		conf.setProperty("storage.backend","cassandra");
		conf.setProperty("storage.hostname","127.0.0.1");
		
		TitanGraph g = TitanFactory.open(conf);
		
		
		
	}

}
