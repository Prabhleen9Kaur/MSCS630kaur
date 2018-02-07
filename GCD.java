import java.util.*;

public class GCD {
    public static void main(String[] args) {
        long a, b = 0;
        long gcd;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First No:");
        a = sc.nextLong();
        System.out.println("Enter Second No:");
        b = sc.nextLong();
        gcd=euclid(a,b);
         System.out.println("GCD:"+gcd);
        //long[] gcd1 = euclidAlgExt(a, b);
        //System.out.println("gcd:" + gcd1);

    }

    public static long euclid(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        // while(b!=0){
        long q = a / b;
        long r = a - b * q;
        if (r == 0) {
            return b;
        } else {
            a = b;
            b = r;
            return euclid(a, b);
            //  b=a%b;
            // a=r;
        }

    }

   

}
