import java.math.BigInteger;

public class RSA {

    public String decode(String encodedText, BigInteger e, BigInteger n) {
        BigInteger p = BigInteger.valueOf(1546379);
        BigInteger q = BigInteger.valueOf(365738333);
        String[] c_text = encodedText.split(",");

        BigInteger phi = p.subtract(BigInteger.valueOf(-1)).multiply(q.subtract(BigInteger.valueOf(-1)));
        System.out.println("phi = " + phi);

        BigInteger d = BigInteger.valueOf(143672396238821L);
        System.out.println("d = " + d);

        StringBuilder text = new StringBuilder();

        for (int i = 0; i < c_text.length; i++) {
            BigInteger c_msg = BigInteger.valueOf(Long.parseLong(c_text[i]));
            BigInteger msg = c_msg.modPow(d, n);
            text.append(msg);
        }
        return parse_str(text.toString());
    }

    public static String parse_str(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length()/2; i++) {
            result.append((char) Integer.parseInt(string.substring(i*2, i*2 + 2)));
        }
        return result.toString();
    }
}
