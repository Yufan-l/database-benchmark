package neo4j;

import java.util.*;
import java.io.*;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.kernel.impl.nioneo.store.FileSystemAbstraction;
import org.neo4j.unsafe.batchinsert.*;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;


public class BatchInsertion {
	
	public static long Insert(BatchInserter inserter,String id,Map<String, Object> properties)throws IOException{
		BatchInserterIndexProvider provider =
                new LuceneBatchInserterIndexProvider( inserter );
		BatchInserterIndex nodeId=provider.nodeIndex("nodeId", MapUtil.stringMap( "type", "exact" ));
		if(!nodeId.query("name", id).hasNext()){
			long newNode=inserter.createNode(properties);
			Map<String,Object>newMap=new HashMap<String,Object>();
			newMap.put("name", id);
			nodeId.add(newNode, newMap);
			nodeId.flush();
			provider.shutdown();
			inserter.shutdown();
			return newNode;
		}
		else{
			provider.shutdown();
			inserter.shutdown();
			System.out.println(nodeId);
			return nodeId.query("name", id).next();
		}
	}
	
	public static void test() throws IOException{
		BatchInserter inserter = BatchInserters.inserter( "/Users/yliu/Documents/testdataBatch1" );
		BatchInserterIndexProvider indexProvider =
                new LuceneBatchInserterIndexProvider( inserter );
        BatchInserterIndex actors =
                indexProvider.nodeIndex( "actors", MapUtil.stringMap( "type", "exact" ) );
        actors.setCacheCapacity( "name", 100000 );

        Map<String, Object> properties = MapUtil.map( "name", "Keanu Reeves" );
        long node = inserter.createNode( properties );
        actors.add( node, properties );
        
        //make the changes visible for reading, use this sparsely, requires IO!
        actors.flush();

        // Make sure to shut down the index provider as well
        indexProvider.shutdown();
        inserter.shutdown();
        // END SNIPPET: batchInsert

        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( "/Users/yliu/Documents/testdataBatch1" );
        Index<Node> index = db.index()
                .forNodes( "actors" );
        Node reeves = index.get( "name", "Keanu Reeves" ).next();
        System.out.println(reeves.getProperty("name"));
        db.shutdown();
	}
	public static void main(String args[])throws IOException
	{
		
	    
		BatchInserter inserter = BatchInserters.inserter( "/Users/yliu/Documents/testdataBatch" );
		
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put( "name", "Mattias1" );
        //long mattiasNode = inserter.createNode( properties );
        //long mattiasNode2 = inserter.createNode( properties );
        long mattiasNode=Insert(inserter, "Mattias1", properties);
        long mattiasNode2=Insert(inserter, "Mattias1",  properties);
        //properties.put( "name", "Chris1" );
        //long chrisNode = inserter.createNode( properties );
        
        
        
        
        RelationshipType knows = DynamicRelationshipType.withName( "KNOWS" );
        // To set properties on the relationship, use a properties map
        // instead of null as the last parameter.
        //inserter.createRelationship( mattiasNode, chrisNode, knows, null );
        inserter.shutdown();
        // END SNIPPET: insert
        
        // try it out from a normal db
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(
                "/Users/yliu/Documents/testdataBatch" );
        Node mNode = db.getNodeById( mattiasNode );
        Node m2Node = db.getNodeById( mattiasNode2 );
        //Node cNode = mNode.getSingleRelationship( knows, Direction.OUTGOING ).getEndNode();
        System.out.println(mNode.getProperty("name"));
        System.out.println(mNode);
        System.out.println(m2Node.getProperty("name"));
        System.out.println(m2Node);
        //System.out.println(cNode.getProperty("name"));
        //System.out.println(cNode);
        db.shutdown();
        
		
		//test();
	}
	

}
