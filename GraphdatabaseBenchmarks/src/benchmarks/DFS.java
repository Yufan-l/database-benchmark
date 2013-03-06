package benchmarks;
import java.util.*;
import com.tinkerpop.blueprints.*;

public class DFS {
	
	private static Set<Vertex> visitedNodes = new HashSet<Vertex>();

    public static void clear()
    {
        visitedNodes.clear();
    }

    public static boolean search(Graph graph, Direction direction, Vertex source, Vertex target, int maxDepth)
    {
        if (maxDepth < 0)
        {
            return false;
        }
        else if (source.equals(target))
        {
            return true;
        }
        else
        {
            for (Vertex neighbor : source.getVertices(direction))
            {
                if (!visitedNodes.contains(neighbor) && search(graph, direction, neighbor, target, maxDepth-1))
                {
                    return true;
                }
            }
            return false;
        }
    }
    
    public static void DFSrecursive(Vertex v, Vertex target, int length,int maxlength, HashMap<Vertex,Integer> index)
    {
        if(length>maxlength)
        {
    	    index.put(target, -1);
    	    return;
        }
        
        if(v.equals(target))
        {
		    index.put(target, 1);
		    return;
	    }
        
        for(Vertex v1 : v.getVertices(Direction.OUT)) 
        {
            if(index.containsKey(v1)&&!v1.equals(target))
            {
                continue;
            }
            index.put(v1, 0);
            DFSrecursive(v1,target,length+1,maxlength,index); 
        }
    }
}
