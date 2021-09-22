package input;

import java.util.Scanner;


public class Bootstrap {


    public static void main(String[] args) {

      ConsoleAdapter consoleAdapter = new ConsoleAdapter();

      consoleAdapter.setScanner( new Scanner(System.in));


}}


