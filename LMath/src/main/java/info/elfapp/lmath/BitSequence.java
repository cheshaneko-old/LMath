/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

import java.util.Arrays;

/**
 *
 * Базовый класс, на обычном массиве.
 */
public class BitSequence {

    private boolean[] data;

    public Boolean[] getData() {
        Boolean[] result = new Boolean[data.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = data[i];
        }
        return result;
    }

    public BitSequence(int len) {
        data = new boolean[len];
    }

    public BitSequence(String d) {
        data = new boolean[d.length()];
        for (int i = 0; i < d.length(); ++i) {
            data[i] = false;
            if (d.charAt(i) == '1') {
                data[i] = true;
            }
        }
    }

    public BitSequence(BitSequence x) {
        data = new boolean[x.length()];
        for (int i = 0; i < x.length(); ++i) {
            data[i] = x.get(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            if (data[i]) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    public int length() {
        return data.length;
    }

    public void set(int index, boolean val) {
        data[index] = val;
    }

    public boolean get(int i) {
        return data[i];
    }

    public boolean summary() {
        boolean result = false;
        for (int i = 0; i < data.length; ++i) {
            result ^= data[i];
        }
        return result;
    }

    public BitSequence and(BitSequence other) {
        if (this.length() != other.length()) {
            throw new RuntimeException();
        }
        BitSequence result = new BitSequence(this.length());
        for (int i = 0; i < this.length(); ++i) {
            result.set(i, (other.get(i) && this.get(i)));
        }
        return result;
    }

    public BitSequence xor(BitSequence other) {
        if (this.length() != other.length()) {
            throw new RuntimeException();
        }
        BitSequence result = new BitSequence(this.length());
        for (int i = 0; i < this.length(); ++i) {
            result.set(i, (other.get(i) ^ this.get(i)));
        }
        return result;
    }

    public BitSequence inc() { //увеличить значение на 1
        int i1 = Integer.parseInt(this.toString(), 2);
        int i2 = 1;
        String calc = Integer.toBinaryString(i1 + i2);
        String result = generateString(data.length - calc.length()) + calc;
        return new BitSequence(result);

    }

    private String generateString(int len) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            result.append("0");
        }
        return result.toString();
    }

    public int weight() { //Количество единиц
        int result = 0;
        for (int i = 0; i < data.length; ++i) {
            if (data[i]) {
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BitSequence) {
            BitSequence tmp = (BitSequence) obj;
            for (int i = 0; i < this.length(); ++i) {
                if (tmp.get(i) != this.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Arrays.hashCode(this.data);
        return hash;
    }

    public BitSequence moveLeft(int len) { //сдвиг влево (не пригодился)
        BitSequence result = new BitSequence(data.length);
        if (len >= data.length) {
            return result;
        }
        for (int i = 0; i < result.length() - len; ++i) {
            result.set(i, this.get(i + len));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new BitSequence("00000").equals(new BitSequence("10000")));
    }
}
