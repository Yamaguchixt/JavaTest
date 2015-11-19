package perfectJava.rambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class functionalCompose {
  public static void main(String[] args) {
    Function<String,Integer> strLength = String::length;
    Function<Integer,String> itoHex = i -> Integer.toString(i,16);
    Function<Integer,Integer> inc = n -> n + 1;

    Function<String,String> newFn = strLength.andThen(itoHex);
    newFn.apply("0123456789"); //=> "a"を出力

    Function<Integer,Integer> inc3 = inc.andThen(inc).andThen(inc);
    inc3.apply(10); //=> 13

    //Consumerの合成処理
    Consumer<String> proc1 = msg -> System.out.println("showing1: " + msg);
    Consumer<String> proc2 = msg -> System.out.println("showing2: " + msg);

    Consumer<String> proc1then2 = proc1.andThen(proc2);
    proc1then2.accept("foobar"); // => "showing1: foobar"
                                 //    "showing2: foobar" を表示

    //Predicateの合成
    Predicate<Integer> isPositive = n -> n > 0;
    Predicate<Integer> isEven     = n -> n % 2 == 0;

    Predicate<Integer> isNegative = isPositive.negate(); //negateで論理反転 isNegative.test(-1); => true
    Predicate<Integer>isPositiveEven = isPositive.and(isEven);
    isPositiveEven.test(4); //=>true

    Function<Integer,Function<Integer,Integer>> fnPlus = a -> b -> a + b;
    Function<Integer,Function<Integer,Integer>>_fnPlus = a -> { Function<Integer,Integer> plus = b -> a + b;
                                                                return plus;
                                                               };
    fnPlus.apply(1).apply(2); // => 3

    Function<Integer,Function<Integer,Function<Integer,Integer>>> fn3arg = a -> b -> c -> (a + b) * c;
    fn3arg.apply(1).apply(3).apply(3);


  }
}
