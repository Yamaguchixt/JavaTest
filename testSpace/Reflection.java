package testSpace;
public class Reflection {
	public static void main(String[] args) {
	  try {
	    //classが予約語なのでclazzを使う。
	    Class<StringBuilder> clazz1 = StringBuilder.class;

	    StringBuilder sb = new StringBuilder();
	    Class<? extends StringBuilder> clazz2 = sb.getClass();

	    Class<?> clazz3 = Class.forName("java.lang.StringBuilder");

	    System.out.println(System.identityHashCode(clazz1) + ", " + System.identityHashCode(clazz2) + ", " +
	    System.identityHashCode(clazz3));

	  }catch(ReflectiveOperationException e){
	    e.printStackTrace();
	  }
	}
}
