package input;

import java.util.Scanner;


public class Bootstrap {


    public static void main(String[] args) {


        ConsoleAdapter consoleAdapter = new ConsoleAdapter(new Config(new Scanner(System.in)));

        consoleAdapter.startApp();


    }
}


