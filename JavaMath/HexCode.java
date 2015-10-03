package JavaMath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HexCode {
	public static void main(String[] args) throws IOException {

		System.out.println("10進数を入力");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		while(input != null){
			int num = Integer.parseInt(input);
			long numl = Long.parseLong(input);
			float numf = Float.parseFloat(input);
			double numd = Double.parseDouble(input);

			System.out.println("[int](0x" + Integer.toHexString(num) + ")H");
			System.out.println("[long](0x" + Long.toHexString(numl) + ")H");
			System.out.println("[float](0x" + Integer.toHexString(Float.floatToIntBits(numf)) + ")H");
			System.out.println("[double](0x" + Long.toHexString(Double.doubleToRawLongBits(numd)) + ")H");

			input = br.readLine();
		}
	}
}
