/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * Решает 5-ую задачку, декодирует последовательности с помощью матрицы
 */
public class Decoder {

    DecodingMatrix matr;
    BitSequence[] data; //во что декодируем
    BitSequence[] encodedData; //из чего дикодируем
    StringBuilder result = new StringBuilder();

    Decoder(DecodingMatrix m, BitSequence[] d, BitSequence[] ed) {
        matr = m;
        data = d;
        encodedData = ed;
    }

    public int find(BitSequence[] e, BitSequence fi) { //просто поиск
        for (int i = 0; i < e.length; ++i) {
            if (e[i].equals(fi)) {
                return i;
            }
        }
        return -1;
    }

    public void decode(BitSequence... code) {
        for (int i = 0; i < code.length; ++i) {
            result.append(code[i].toString());
            result.append("->");
            BitSequence decoded = matr.get(0, matr.find(code[i]).second);
            result.append(decoded.toString());
            result.append("->");
            result.append(data[find(encodedData, decoded)].toString());
            result.append("\n <br>");
        }
    }

    @Override
    public String toString() {
        return result.toString();
    }

    public static void main(String[] args) {
        CodeTable a = new CodeTable(new BitMatrix("1010101110", 2, 5));
        a.generate();
        DecodingMatrix x = new DecodingMatrix(a.getEncodedData(), 2);
        x.generateMatrix();
        Decoder d = new Decoder(x, a.getData(), a.getEncodedData());
        d.decode(new BitSequence("10001"), new BitSequence("01110"), new BitSequence("10101"));
        System.out.println(d);
    }
}
