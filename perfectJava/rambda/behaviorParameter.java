package perfectJava.rambda;

import java.util.function.Consumer;

public class behaviorParameter {
  public static void main(String[] args) {

    long elapsed = elapsedTime( s -> {}, "abc");
    System.out.println(elapsed);
  }
  public static <T> long elapsedTime(Consumer<T> proc, T arg ){
    long start = System.nanoTime();
    proc.accept(arg);
    return System.nanoTime() - start;
  }


}
