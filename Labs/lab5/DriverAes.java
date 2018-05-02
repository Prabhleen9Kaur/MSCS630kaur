/**
 * file: DriverAES.java
 * author: Prabhleen Kaur
 * course: MSCS 630
 * assignment: Lab 5
 * due date: Wesdnesday April 18, 2018
 * version: 1.0
 *
 * This file contains driver class which accepts the input from User
 * to aes function as to generate the cipher text
 */
 
import java.util.Scanner;

/*
 * Driver class implements AES Algorithm
 * it call AESCipher class 
 */
class DriverAES
{
  static String inputKey;
  static String inputPlainText;
  public static void main(String[] args) {
    AESCipher aes= new AESCipher();
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter the Key and plain text");
    if(sc.hasNext()) {
      inputKey = sc.nextLine();
    }
    else {
      System.out.println(" No Input Key ");
      System.exit(0);
    }
    if(sc.hasNext()) {
      inputPlainText = sc.nextLine();
    }
    else {
      System.out.println("No Input");
      System.exit(0);
    }
  
   
    if(inputKey.length()==32 && inputPlainText.length()==32) {
      aes.aes(inputKey, inputPlainText);
    }
    else {
      System.out.println("Enter a valid key");
    }
  }
}