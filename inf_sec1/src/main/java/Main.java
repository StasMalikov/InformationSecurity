import at.favre.lib.bytes.Bytes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Введите строку: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        Bytes inputBytes = Bytes.from(input);

        List<Bytes> data = new ArrayList<>();


        int blocks = inputBytes.length()/8;
        blocks += inputBytes.length()%8 > 0 ? 1 : 0;
        //data.add(inputBytes.resize(8));
        for (int i = 0; i < blocks - 1; i++) {
            data.add(inputBytes.copy(i*8, 8));
        }

        if (blocks == 1) {
            data.add(inputBytes.resize(8));
        }

        if (blocks > 1) {
            data.add(inputBytes.copy((blocks-1)*8, inputBytes.length()%8).resize(8));
        }

        int rounds = 8;
        FeistelNetwork feistelNetwork = new FeistelNetwork(rounds, data);
        Bytes encryptedData = feistelNetwork.encrypt();
        Bytes decryptedData = feistelNetwork.decrypt();
//        Bytes test;
//        if (blocks ==1) {
//            test = data.get(0).resize(inputBytes.length()%8);
//        } else {
//            test = data.get(0);
//        }
//
//        for (int i = 1; i < blocks; i++) {
//            if(i == blocks -1) {
//                test = test.append(data.get(i).resize(inputBytes.length()%8));
//            } else {
//                test = test.append(data.get(i));
//            }
//        }

        //System.out.println("    Input: " + Arrays.toString(inputBytes.toCharArray(StandardCharsets.ISO_8859_1)));
        //System.out.println("Encrypted: " + Arrays.toString(encryptedData.toCharArray(StandardCharsets.ISO_8859_1)));
        //System.out.println("Decrypted: " + Arrays.toString(decryptedData.toCharArray(StandardCharsets.ISO_8859_1)));

        System.out.println("    Input: " + charArrToStr(inputBytes.toCharArray(StandardCharsets.ISO_8859_1)));
        System.out.println("Encrypted: " + charArrToStr(encryptedData.toCharArray(StandardCharsets.ISO_8859_1)));
        System.out.println("Decrypted: " + charArrToStr(decryptedData.toCharArray(StandardCharsets.ISO_8859_1)));
    }

    public static String charArrToStr(char[] arr) {
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }

}
