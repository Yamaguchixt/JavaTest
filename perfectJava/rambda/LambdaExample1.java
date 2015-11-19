package perfectJava.rambda;

public class LambdaExample1 {
  @FunctionalInterface
  interface Appender {
    public void appendIn(StringBuilder sb , String s);
  }

  public static void main(String[] args) {
    Appender appender = (sb,s) -> sb.append(s);
    StringBuilder sb = new StringBuilder();
    appender.appendIn(sb, "foo");
    appender.appendIn(sb, "bar");
    System.out.println(sb);
  }
}
