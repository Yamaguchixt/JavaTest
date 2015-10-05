package JavaMath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecimalToBinary {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("10進数を入力");
		String line;
		while((line =br.readLine()) != null){
			int desimal = Integer.parseInt(line);
			int[] result = new int[line.length()*4];
			int index = 0;
			while(desimal != 0){
				result[index++] = desimal % 2;
				desimal /=  2;
			}

			for(int i = result.length - 1;i >= 0;i--){
				System.out.print(result[i]);
			}
			System.out.println("");
		}
	}
}
