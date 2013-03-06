package benchmarks;

import com.tinkerpop.blueprints.*;

import java.util.*;



public class BFS1
{
    /**
     * Performs a depth-bound BFS search using the provided graph, from the provided source to the target.
     * @return  -1 if the vertex is not found, or the distance from source to target otherwise
     */
    public static int search(Graph graph, Vertex source, Vertex target, int maxDepth)
    throws Exception
    {
        HashSet<Vertex> discoveredVertices = new HashSet<Vertex>();      
        LinkedList<Vertex> CurrentLevel = new LinkedList<Vertex>();
        List<Vertex> NextLevel = new ArrayList<Vertex>();
        int depth = 0;

        discoveredVertices.add(source);
        CurrentLevel.add(source);
        
        while (!CurrentLevel.isEmpty())
        {
            if(depth>maxDepth)
            {
			    return -1;    //exceed the max depth 
            }
            
            Vertex e=CurrentLevel.poll();
            
		    if(e.equals(target))
            {
                return depth;
            }
		    
            for(Vertex e1 : e.getVertices(Direction.OUT)) 
            {
                if (discoveredVertices.contains(e1))
                {
                    continue;
                }
                discoveredVertices.add(e1);
                NextLevel.add(e1);
            }
            
            if(CurrentLevel.isEmpty())
            {
                if(NextLevel.isEmpty())
                {
                    break;
                }
                depth++;
                CurrentLevel.addAll(NextLevel);
                NextLevel.clear();
            }
        }
        return -2;    //reach the end of the graph
    }
}


