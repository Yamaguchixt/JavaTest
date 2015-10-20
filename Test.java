import java.io.UnsupportedEncodingException;


public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "HTTP1.1 200 OK\r\n";
		byte[] bytes = s.getBytes("UTF-8");
		for(byte i:bytes){
			System.out.print(i+ " ");
		}
	}
}
