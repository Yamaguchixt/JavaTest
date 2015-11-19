package testSpace;

import java.util.Properties;


public class Test {
	public static void main(String[] args)   {
	  Properties p = System.getProperties();
	  p.keySet().forEach((key) -> System.out.println( key + " : " + p.getProperty((String) key)));
	}
}
