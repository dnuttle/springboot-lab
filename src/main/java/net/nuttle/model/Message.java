package net.nuttle.model;

public abstract class Message {

  private String jobID;
  
  public Message(String jobID) {
    this.jobID = jobID;
  }
  
  public String getJobID() {
    return jobID;
  }
}
