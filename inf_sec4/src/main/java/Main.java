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

    public static void main(String[] args) {
        BigInteger e = BigInteger.valueOf(12341);
        BigInteger n = BigInteger.valueOf(Long.parseLong("565570077646207"));
        String encodedText = "277140870674302,260217431481485,329310844916399,448964498705119";

        RSA rsa = new RSA();

        System.out.println(rsa.decode(encodedText, e, n));
    }
}
