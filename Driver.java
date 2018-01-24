
import java.util.*;

    public class Driver {



             public static void main(String[] args) {
                  Scanner input = new Scanner(System.in);
                  while (input.hasNext()) {

                       String plainText=input.next();
                       plainText=plainText.toUpperCase();
                       int[] A=str2int(plainText);
                       for(int i=0; i< A.length ;i++) {

                           char c = plainText.charAt(i);
                           if (c == 32)
                               System.out.println("26");
                           else
                               System.out.println(((int) c - 65) + " ");
                       }
                       System.out.println();
                   }
             }
             static int[] str2int(String plainText){

                   int[] output=new int[plainText.length()];

                    plainText=plainText.toUpperCase();
                    for(int i=0;i<plainText.length();i++)
                    {
                        char c=plainText.charAt(i);
                        if(c==32)
                            output[i] =26;
                        else
                            output[i] =(int) c-65;
                    }
                    return output;
              }

    }