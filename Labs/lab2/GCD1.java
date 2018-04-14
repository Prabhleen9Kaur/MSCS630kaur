import java.util.*;

public class GCD1 {
    public static void main(String[] args) {
        long a, b ;
        //long gcd;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First No:");
        a = sc.nextLong();
        System.out.println("Enter Second No:");
        b = sc.nextLong();
        //gcd=euclid(a,b);
        // System.out.println("GCD:"+gcd);
        long gcd1[] =new long[3];
        gcd1=euclidAlgExt(a, b);
        System.out.println("gcd:" + gcd1[0]+gcd1[1]+gcd1[2]);

    }

    

    public static long[] euclidAlgExt(long a, long b) {

        long[] arr = {1, 0, a};
        //long x[] = {1, 0, a};
        //long y[] = {0, 1, b};
        if (b == 0) {
            arr[0]=a;
            arr[1]=1;
            arr[2]=0;
            return arr;
        }
        
        while (b > 0) {
            long q = a / b;
            long r = a % b;
            long m[]=euclidAlgExt(b,r);
            long g=m[0];
            long x=m[2];
            long y=m[1]-q*a;
           arr[0]=g;
           arr[1]=x;
           arr[2]=y;
        }
        return arr;

    }

}







