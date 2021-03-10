import java.util.ArrayList;
import java.util.List;

public class MyRandom {

    public MyRandom() {}

    public List<Double> generate(int count) {
        MyParams params = new MyParams();
        List<Double> result = new ArrayList<>();
        result.add(params.getU0());
        for (int i = 1; i < count; i++) {
            result.add((params.getA() * result.get(i - 1) + params.getC()) % params.getM());
        }

        return result;
    }

    public List<Double> generate(int count, MyParams params) {
        List<Double> result = new ArrayList<>();
        result.add(params.getU0());
        for (int i = 1; i < count; i++) {
            result.add((params.getA() * result.get(i - 1) + params.getC()) % params.getM());
        }

        return result;
    }
}
