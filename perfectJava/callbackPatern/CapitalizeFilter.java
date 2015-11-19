package perfectJava.callbackPatern;

public class CapitalizeFilter implements MyFilter{
  @Override
  public String doJob(String input) {
    return input.toUpperCase();
  }
}
