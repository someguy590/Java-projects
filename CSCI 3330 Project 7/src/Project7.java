/**
 * Jonathan Moyett
 * CSCI 3330 Project 7
 * 3/9/16
 */
public class Project7 {
 public static int fun(int a, int b) {
        System.out.printf("a=%d,b=%d\n", a, b);
        int tempA = a + a;
        a = a - 1;
        a = a + 1;
        a = tempA + 2 * (a + b);
        System.out.printf("a=%d,b=%d\n", a, b);
        
        int x = b;
        b = b + 1;
        a = a + 1;
        x = x + a;
        System.out.printf("a=%d,b=%d,x=%d\n", a, b, x);
        
        boolean breakOutFlag = false;
        for (int i = 1; i <= 100 && !breakOutFlag; i = i + 1) {
            int j = b;
            if ((x + i) % 3 == 0) {
                System.out.printf("continue: a=%d,b=%d,x=%d\n", a, b, x);
            }
            else {
                boolean isJLessThanA = false;
                while ((j > 1) && !isJLessThanA && !breakOutFlag) {
                    if (x % 5 == 1) {
                        x = x + 1;
                        System.out.printf("break out: a=%d,b=%d,x=%d\n", a, b, x);
                        breakOutFlag = true;
                    }
                    else {
                        x = x + 1;
                        j = j - 1;
                        if (j < a) {
                            System.out.printf("break: a=%d,b=%d,x=%d\n", a, b, x);
                            isJLessThanA = true;
                        }
                        else {
                            x += i;
                            System.out.printf("a=%d,b=%d,x=%d\n", a, b, x);
                        }
                    }
                }
            }
        }
        System.out.printf("a=%d,b=%d,x=%d\n", a, b, x);
        if (a % 5 == 0) {
            x = x - 1;
            x = x + 1;
        }
        else if (a % 5 == 1)
            x = x + 1;
        else if (a % 5 == 2) {
            x -= b;
            x = x << 1;
        }
        else
            x = x << 1;
        
        System.out.printf("a=%d,b=%d,x=%d\n", a, b, x);
        return x;
    }
}