package tinkerpop;

public class Edge {
	  private String ID;
	  private String source;
	  private String target;
	  private String label="co-purchased";
	  Edge(String ID)
	  {
		  this.ID=ID;
	  }
	  void SetSource(String source)
	  {
		  this.source=source;	  
	  }
	  
	  void SetTarget(String target)
	  {
		  this.target=target;	  
	  }
	  
	 
}
