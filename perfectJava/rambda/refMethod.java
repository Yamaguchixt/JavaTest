package perfectJava.rambda;

public class refMethod {
  //メソッド参照を渡す側
  public static void main(String[] args) {
    execPrinter(refMethod::printMessage, "foo");
  }
  //メソッド参照うける側
  static void execPrinter(Printer printer, String str) {
    printer.print(str);
  }

  //メソッド参照の参照先メソッド
  static void printMessage(String message) {
    System.out.println(message);
  }

  //間をつなぐ魔法
  @FunctionalInterface //なくてもよい
  interface Printer {
    public void print(String msg);
  }
}
