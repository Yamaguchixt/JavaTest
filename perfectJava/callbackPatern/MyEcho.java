package perfectJava.callbackPatern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyEcho {
  //replaceとcapitalizeは下請け処理メソッド
  private String replace(String input, String oldStr, String newStr) {
    return input.replaceAll(oldStr, newStr);
  }
  private String capitalize(String input) {
    return input.toUpperCase();
  }

  public void exec() {
    try ( BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        System.out.println("Input any text");
        String msg = stdin.readLine();
        //実際はもっと複雑なコード
        System.out.println("(^-^) < " + capitalize(replace(msg,"he","she")));
      }
    } catch(IOException e){}
  }

  public static void main(String[] args) {
    MyEcho echo = new MyEcho();
    echo.exec();
  }
}

