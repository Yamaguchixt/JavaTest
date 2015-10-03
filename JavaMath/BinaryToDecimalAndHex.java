package JavaMath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinaryToDecimalAndHex {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("2進数を入力");
		String input;
		while((input = br.readLine()) != null){
			int result = 0;
			int cardinal = 0;
			for(int i=input.length()-1;i >= 0;i--){
				result += Character.getNumericValue(input.charAt(i)) * (Math.pow(2, cardinal++));
				System.out.println(Character.getNumericValue(input.charAt(i)));
			}
			System.out.println("result:" + result);
		}
	}
}
