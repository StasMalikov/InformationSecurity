import at.favre.lib.bytes.Bytes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.print("Введите длинну последовательности чисел: ");
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        MyRandom random = new MyRandom();

        List<Double> result1 = random.generate(count);

        MyParams params1 = new MyParams(2, 10, 10, 10);
        List<Double> result2= random.generate(count,params1);

        System.out.println(result1.toString());
        System.out.println(result2.toString());
        //System.out.println(result.stream().sorted().collect(Collectors.toList()).toString());
    }

    public static void test1(List<Long> testList) {
        //testList.stream().filter(Long item -> item == )
    }
}
