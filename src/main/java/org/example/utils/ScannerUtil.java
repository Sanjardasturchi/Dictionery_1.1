package org.example.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerUtil {

    private Scanner scanner=new Scanner(System.in);

    public int nextInt() {
        int i=-125;
        do {
            try {
                i=scanner.nextInt();
                scanner.nextLine();
                return i;
            }catch (Exception e){
                scanner.nextLine();
                System.out.println("Enter only number!!!");
            }
        }while (i!=-125);
        return i;
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
