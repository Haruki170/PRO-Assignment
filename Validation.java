/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Acer - PC
 */
import java.util.Scanner;

/**
 *
 * @author kan3v
 */
public class Validation {
    
     private Scanner scanner = new Scanner(System.in);
    
    public String inputString(String mess,String regex) {
        System.out.print(mess);
        //force user input exectly non-empty string
        while(true) {
            String input = scanner.nextLine();
            if (!input.matches(regex)) {
                System.out.print("Invalid input! Please try again. ");
                continue;
            }
            return input;
        }
    }
    
    public double inputDouble(String mess) {
        System.out.print(mess);
        //force user input exectly double number
        while(true) {
            String input = scanner.nextLine();
            try {
                double number = Double.parseDouble(input);
                //check range of number
                if (number <= 0) {
                    System.out.print("Please input value > 0 ");
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.print("Please input an double number: ");
            }
        }
    }
    
    public boolean checkInputYN(String mess) {
        System.out.println(mess);
        while (true) {
             String result = scanner.nextLine();
         
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
           
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
 
    public int inputInt() {
        //force user input exectly integer number
        while(true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                //check range of number
                return number;
            } catch (Exception e) {
                System.out.print("Please input an integer number: ");
            }
        }
    }
    
    public String normalizeString(String input) {
        return input.trim().replaceAll("\\s+", " ").toLowerCase(); // Sử dụng regex để thay thế khoảng trắng liên tiếp
    }
    
}
