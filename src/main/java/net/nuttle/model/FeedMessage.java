package net.nuttle.model;

public class FeedMessage extends Message {
  
  private String feedID;
  private String feedPath;
  
  public FeedMessage(String jobID, String feedID, String feedPath) {
    super(jobID);
    this.feedID = feedID;
    this.feedPath = feedPath;
  }
  
  public String getFeedID() {
    return feedID;
  }
  
  public String getFeedPath() {
    return feedPath;
  }
  
  public String getProp1() {
    return "property 1 value";
  }
  
  public String getProp2() {
    return "property 2 value";
  }

}
