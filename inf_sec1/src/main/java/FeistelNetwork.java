import at.favre.lib.bytes.Bytes;

import java.util.ArrayList;
import java.util.List;

public class FeistelNetwork {

    private Bytes key64;

    private List<Bytes> keys;

    //исходные данные с которыми мы работаем (список блоков по 64 бит)
    private List<Bytes> data;

    private int rounds;

    List<Bytes> encryptedDataList;

    public FeistelNetwork(int rounds, List<Bytes> data) {
        key64 = Bytes.random(8); // случайная 64-битная последовательность
        keys = new ArrayList<>();
        for (int i = 0; i < rounds; i++) {
            keys.add(keyGenerator(i));
        }
        this.rounds = rounds;
        this.data = data;
    }

    public Bytes encrypt() {
        encryptedDataList = new ArrayList<>();
        encryptedDataList.add(encryptBlock(data.get(0)));
        Bytes result = encryptedDataList.get(0);
        for (int i = 1; i < data.size(); i++) {
            encryptedDataList.add(encryptBlock(data.get(i)));
            result = result.append(encryptedDataList.get(i));
        }

        return result;
    }

    public Bytes encryptBlock(Bytes block) {
        Bytes L_i = block.copy(0, 4);
        Bytes R_i = block.copy(4, 4);
        for (int i = 0; i < rounds; i++) {
            Bytes key_i = keys.get(i);

            Bytes l_i = L_i;
            Bytes r_i = R_i.xor(func(L_i, key_i));

            if (i < rounds - 1) {
                L_i = r_i;
                R_i = l_i;
            } else {
                L_i = l_i;
                R_i = r_i;
            }
        }

        return L_i.append(R_i);
    }

    public Bytes decrypt() {
        Bytes result = decryptBlock(encryptedDataList.get(0));

        for (int i = 1; i < encryptedDataList.size(); i++) {
            result = result.append(decryptBlock(encryptedDataList.get(i)));
        }

        return result;
    }

    public Bytes decryptBlock(Bytes block) {
        Bytes L_i = block.copy(0, 4);
        Bytes R_i = block.copy(4, 4);
        for (int i = rounds - 1; i >= 0; i--) {
            Bytes key_i = keys.get(i);

            Bytes l_i = L_i;
            Bytes r_i = R_i.xor(func(L_i, key_i));

            if (i > 0) {
                L_i = r_i;
                R_i = l_i;
            } else {
                L_i = l_i;
                R_i = r_i;
            }
        }

        return L_i.append(R_i);
    }

    private Bytes cyclicShiftLeft64(Bytes bytes, int t) {
        return Bytes.from(bytes).leftShift(t).or(Bytes.from(bytes).rightShift(64 - t)); //((x << t) | (x >> (64 - t)));
    }

    private Bytes cyclicShiftRight64(Bytes bytes, int t) {
        return Bytes.from(bytes).rightShift(t).or(Bytes.from(bytes).leftShift(64 - t)); //((x >> t) | (x << (64 - t)));
    }

    private Bytes cyclicShiftLeft32(Bytes bytes, int t) {
        return Bytes.from(bytes).leftShift(t).or(Bytes.from(bytes).rightShift(32 - t)); //((x << t) | (x >> (32 - t)));
    }

    private Bytes cyclicShiftRight32(Bytes bytes, int t) {
        return Bytes.from(bytes).rightShift(t).or(Bytes.from(bytes).leftShift(32 - t)); //((x >> t) | (x << (32 - t)));
    }

    private Bytes keyGenerator(int i) {
        return cyclicShiftRight64(key64, i*8).resize(4);
    }

    private Bytes func(Bytes L_i, Bytes Key_i) {
        Bytes f1 = cyclicShiftLeft32(L_i, 9);
        Bytes f2 = cyclicShiftRight32(Key_i, 11).or(L_i);
        return  f1.xor(f2);
    }
}
