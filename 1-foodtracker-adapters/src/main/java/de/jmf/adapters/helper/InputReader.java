package de.jmf.adapters.helper;

import java.util.Scanner;

public class InputReader{
    public final Scanner scanner;

    public InputReader(){
        this.scanner = new Scanner(System.in);
    }
    
    public int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.next().trim());
            } catch (Exception e) {
                System.out.println(Strings.WRONG_INPUT); 
            }
        }
    }

    public String getString(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return scanner.next().trim();
            } catch (Exception e) {
                System.out.println(Strings.WRONG_INPUT);
            }
        }
    }

    public double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.next().trim());
            } catch (Exception e) {
                System.out.println(Strings.WRONG_INPUT);
            }
        }
    }
}