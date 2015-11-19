package testSpace;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileTest {
  public static void main(String[] args) {


    String fs = File.separator;
    File f = new File("supertest.txt");
    System.out.println(f.getAbsolutePath());

    try{
      FileWriter fw = new FileWriter(f,true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      pw.write("one line");;
      pw.write("2行目");
      pw.println("3行目");
      pw.println("4gilyoume");
      pw.close();
    }catch(IOException e){

    }finally{
      // close !!
    }
  }
}
