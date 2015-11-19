package testSpace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProcessBuilderSample {

	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("ping","-c","3",args[0]);
		try{
			Process p = pb.start();
			p.waitFor();

			System.out.println(pb.redirectInput());

			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()))){

				for(String line = br.readLine(); line != null; line = br.readLine()){
					System.out.println(line);

				}
			}
		}catch (IOException | InterruptedException e){
			e.printStackTrace();
		}
	}

}
