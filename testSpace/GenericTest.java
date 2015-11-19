package testSpace;

import java.util.ArrayList;
import java.util.List;

public class GenericTest<T> {

   T exec1(T t) {
     return t;
   }

   <T> T exec2(T t) {
     return t;
   }

   static <T> T exec3(T t){
     return t;
   }

   static void exec4(List<? extends CharSequence> list){
     CharSequence cs = list.get(0);
   }

   public static void main(String[] args) {
    GenericTest<Integer> my = new GenericTest<>();

    my.exec1(0);
    //型推論依存
    my.exec2("abc");
    my.exec3("abc");

    //型明治
    my.<String>exec2("abc");
    my.<String>exec3("abc");

    exec4(new ArrayList<String>());


  }
}
