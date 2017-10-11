/**
 * Created by someguy590 on 4/20/2016.
 */
public class Main {
    public static void main(String[] args) {
        Zn a = new Zn(-5);
        Zn b = new Zn(-4);

        System.out.println(a.add(b));
        System.out.println(a.sub(b));
        System.out.println(a.mul(b));

        System.out.println(-5 % 3);

        System.out.println(b.add(a));
        System.out.println(b.sub(a));
        System.out.println(b.mul(a));
    }
}
