//**
 * file: DriverAES.java
 * author: Prabhleen Kaur
 * course: MSCS 630
 * assignment: Lab 4
 * due date: Wednesday , April 4, 2018.
 * version: 9.0.4
 *
 * This file accepts the input from User and passes this input to aesRoundKeys function as
 * a parameter to generate the Round Keys.
 */

import java.util.Scanner;

    public class DriverAES {

      public static void main(String[] args) {


        AESCiper a= new AESCiper();
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the System Key");
        boolean t=true;
        do {
           String inHex = sc.next();
            if(inHex!="" && inHex!=null && inHex.length()==32) {
                a.roundKeyHex(inHex);
            }
            else {
                System.out.println("Enter a valid key");
                t= false;
            }
          }while(!t);
       }
   }
