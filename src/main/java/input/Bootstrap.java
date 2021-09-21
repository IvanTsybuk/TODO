package input;

import java.util.Scanner;


public class Bootstrap {


    public static void main(String[] args) {

      ConsoleAdapter consoleAdapter = new ConsoleAdapter();

      consoleAdapter.setCommand( new Scanner(System.in));


}}


