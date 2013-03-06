package tinkerpop;

public class TwitterNode {

	  private String ID;
	  private String followersCount;
	  private String location;
	  private String description;
	  private String friendsCount;
	  private String screenName;
	  private String favouritesCount;
	  private String name;
	  private String url;
	  private String createdAt;
	  private String gender;
	  
	  TwitterNode(String ID)
	  {
		  this.ID=ID;  
	  }
	  
	  void SetFollowers(String followersCount)
	  {
		  this.followersCount=followersCount;
	  }
	  
	  void SetLocation(String location)
	  {
		  this.location=location;
	  }
	  
	  void SetDescription(String description)
	  {
		  this.description=description;
	  }
	  
	  void SetFriends_count(String friendsCount)
	  {
		  this.friendsCount=friendsCount;
	  }
	  
	  void SetScreen_name(String screenName)
	  {
		  this.screenName=screenName;
	  }
	  
	  void SetFavourites_count(String favouritesCount)
	  {
		  this.favouritesCount=favouritesCount;
	  }
	  
	  void SetName(String name)
	  {
		  this.name=name;
	  }
	  
	  void SetUrl(String url)
	  {
		  this.url=url;
	  }
	  
	  void SetCreated_at(String createdAt)
	  {
		  this.createdAt=createdAt;
	  }
	  
	  void SetGender(String gender)
	  {
		  this.gender=gender;
	  }
	  

}
