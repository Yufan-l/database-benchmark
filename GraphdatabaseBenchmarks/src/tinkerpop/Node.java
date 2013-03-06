package tinkerpop;

public class Node {
	  private String ID;
	  private String ASIN;
	  private String title;
	  private String group;
	  private String salesrank;
	  private String similar;
	  
	  Node(String ID)
	  {
		  this.ID=ID;  
	  }
	  
	  void SetTitle(String title)
	  {
		  this.title=title;
	  }
	  
	  void SetASIN(String ASIN)
	  {
		  this.ASIN=ASIN;
	  }
	  
	  void SetGroup(String group)
	  {
		  this.group=group;
	  }
	  
	  void SetSalesrank(String salesrank)
	  {
		  this.salesrank=salesrank;
	  }
	  
	  void SetSimilar(String similar)
	  {
		  this.similar=similar;
	  }
	  
	  
	}