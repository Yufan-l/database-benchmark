package tinkerpop;

public class TwitterEdge {
	  private String ID;
	  private String source;
	  private String target;
	  private String label="FollowedBy";
	  TwitterEdge(String ID)
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