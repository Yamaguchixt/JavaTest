import java.lang.reflect.Method;
public class Reflection {

	public static void main(String[] args) {
		Class<?> info1 = String.class;
		System.out.println(info1.getSimpleName());
		System.out.println(info1.getName());
		System.out.println(info1.getPackage().getName());

		Class clazz = ProcessBuilder.class;
		Method[] methods = clazz.getMethods();
		for(Method m: methods){
			System.out.println(m);
		}

	}

}
