package perfectJava.callbackPatern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class MyEcho2 {
  private final List<MyFilter> filters;

  public MyEcho2(List<MyFilter> filters) {
    this.filters = filters;
  }

  public void exec() {
    try ( BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      while(true) {
        System.out.println("Input some");
        String msg = br.readLine();

        String output = msg;
        for (MyFilter filter : filters) {
          output = filter.doJob(output);
        }
        System.out.println("(*-_-) < " + output);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) {
    MyEcho2 echo = new MyEcho2(Arrays.asList(new ReplaceFilter("he","she"), new CapitalizeFilter()));
    echo.exec();
  }
}
