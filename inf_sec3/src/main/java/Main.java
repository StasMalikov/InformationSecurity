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

        MyParams params1 = new MyParams(8, 9, 1, 12);
        MyParams params2 = new MyParams(10, 7, 7, 7);

        List<Long> result1 = random.generate(count);
        List<Long> result2 = random.generate(count, params1);
        List<Long> result3 = random.generate(count, params2);

        System.out.println("result1 " + result1.toString());
        test1(result1);
        System.out.println("result3 " + result3.toString());
        test1(result3);
        System.out.println("result2 " + result2.toString());
        test1(result2);
    }

    public static <T> void test1(List<T> testList) {
        long index = 0;
        long maxIndex = 0;
        int lastIndex = 0;
        T firstIn = null;
        boolean continuously = false;
        for (int i = 0; i < testList.size() - 1; i++) {
            T current = testList.get(i);
            for (int j = lastIndex > 0 ? lastIndex : i + 1; j < testList.size(); j++) {
                if (current == testList.get(j)) {
                    if(firstIn != null) {
                        if (firstIn == testList.get(j)) {
                            if(maxIndex < index) {
                                maxIndex = index;
                            }
                            continuously = false;
                            index = 0;
                            lastIndex = 0;
                        }
                    }

                    if(!continuously) {
                        lastIndex = j + 1;
                        firstIn = testList.get(j);
                    } else {
                        lastIndex++;
                    }
                    continuously = true;
                    index++;
                    if(maxIndex < index) {
                        maxIndex = index;
                    }
                    break;
                } else {
                    if(continuously) {
                        if(maxIndex < index) {
                            maxIndex = index;
                        }
                        continuously = false;
                        index = 0;
                        lastIndex = 0;
                    }
                }
            }
        }
        System.out.println("Длина периода последовательности: " + maxIndex);
    }
}
