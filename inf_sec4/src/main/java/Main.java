import at.favre.lib.bytes.Bytes;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        BigInteger e = BigInteger.valueOf(12341);
        BigInteger n = BigInteger.valueOf(Long.parseLong("565570077646207"));

        BigInteger p = BigInteger.valueOf(1546379);
        BigInteger q = BigInteger.valueOf(365738333);

        BigInteger phi = p.subtract(BigInteger.valueOf(-1)).multiply(q.subtract(BigInteger.valueOf(-1)));
        System.out.println("phi = " + phi);

        BigInteger d = BigInteger.valueOf(143672396238821L);
        System.out.println("d = " + d);

        String[] c_text = "277140870674302,260217431481485,329310844916399,448964498705119".split(",");

        StringBuilder text = new StringBuilder();

        for (int i = 0; i < c_text.length; i++) {
            BigInteger c_msg = BigInteger.valueOf(Long.parseLong(c_text[i]));
            BigInteger msg = c_msg.modPow(d, n);
            text.append(msg);
        }
        System.out.println(parse_str(text.toString()));
    }

    public static String parse_str(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length()/2; i++) {
            result.append((char) Integer.parseInt(string.substring(i*2, i*2 + 2)));
        }
        return result.toString();
    }
}
