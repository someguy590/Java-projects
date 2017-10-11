import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by someguy590 on 4/11/2016.
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String string = "0123456789";


        byte[] byteString = string.getBytes("UTF-16");
        System.out.println(byteString.length);

        System.out.println(Arrays.toString(byteString));
    }
}
