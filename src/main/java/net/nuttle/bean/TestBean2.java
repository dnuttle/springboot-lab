package net.nuttle.bean;

public class TestBean2 {

  private String arg;
  
  TestBean2(String arg) {
    this.arg = arg;
  }
  
  public String getArg() {
    return arg;
  }
  public static class Builder {
    private String arg;
    private Builder() {
    }
    public static Builder instance() {
      return new Builder();
    }
    public Builder setArg(String arg) {
      this.arg = arg;
      return this;
    }
    public TestBean2 build() {
      return new TestBean2(arg);
    }
  }
}
