package perfectJava.rambda;

import java.util.function.Consumer;

public class LambdaExample2 {
  Consumer<String> createProc(String paramValue) { //paramValueは実質final
    String localValue = "local";
    Consumer<String> proc = s -> System.out.println(s + ": " + paramValue + ", " + localValue);
    return proc;
  }

  public static void main(String[] args) {
    LambdaExample2 my = new LambdaExample2();
    Consumer<String> proc = my.createProc("param");
    proc.accept("showing"); // =>"showing: param, local"

    //スコープはラムダ式で閉じる
    Consumer<String> consumer1 = s -> {int n = 1; System.out.println(s + n); };
    Consumer<String> consumer2 = s -> {int n = 2; System.out.println(s + n); };
  }
}
