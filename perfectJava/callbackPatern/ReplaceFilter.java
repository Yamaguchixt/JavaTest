package perfectJava.callbackPatern;

public class ReplaceFilter implements MyFilter {
  private final String oldStr;
  private final String newStr;

  public ReplaceFilter(String oldStr, String newStr) {
    this.oldStr = oldStr;
    this.newStr = newStr;
  }

  @Override
  public String doJob(String input) {
    return input.replaceAll(oldStr, newStr);
  }
}
