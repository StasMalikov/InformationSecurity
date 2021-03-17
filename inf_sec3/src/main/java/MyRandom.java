import java.util.ArrayList;
import java.util.List;

public class MyRandom {

    public MyRandom() {}

    public List<Long> generate(int count) {
        MyParams params = new MyParams();
        List<Long> result = new ArrayList<>();
        result.add((long) params.getU0());
        for (int i = 1; i < count; i++) {
            result.add((long) ((params.getA() * result.get(i - 1) + params.getC()) % params.getM()));
        }

        return result;
    }

    public List<Long> generate(int count, MyParams params) {
        List<Long> result = new ArrayList<>();
        result.add((long) params.getU0());
        for (int i = 1; i < count; i++) {
            result.add((long) ((params.getA() * result.get(i - 1) + params.getC()) % params.getM()));
        }

        return result;
    }
}
