import java.util.Iterator;
import java.util.Properties;

public class JVM {
	public static void main(String[] args) throws Exception {
		Properties p = System.getProperties();
		Iterator<String> i = p.stringPropertyNames().iterator();
		while(i.hasNext()){
			String key = i.next();
			System.out.print(key+" = ");
			System.out.println(System.getProperty(key));
		}
		System.out.println("line separator : "+System.getProperty("line.separator")+" next line");
	}
}
