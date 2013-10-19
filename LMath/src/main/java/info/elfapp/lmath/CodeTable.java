/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * Решает первую задачку, кодирует последовательности с помощью заданной матирцы
 */
public class CodeTable {

    BitSequence[] data; //исходные последовательности
    BitMatrix source;
    BitSequence[] encodedData; //закодированные последовательности

    CodeTable(BitMatrix a) {
        data = new BitSequence[(int) Math.pow(2.0, a.getNumberOfRows())];
        encodedData = new BitSequence[data.length];
        source = a;
    }

    public BitSequence[] getData() {
        return data;
    }

    public BitSequence[] getEncodedData() {
        return encodedData;
    }

    private void generateData() {
        data[0] = new BitSequence(source.getNumberOfRows());
        for (int i = 1; i < data.length; ++i) {
            data[i] = data[i - 1].inc();
        }
    }

    private void generateCodes() {
        for (int i = 0; i < encodedData.length; ++i) {
            encodedData[i] = source.multOnVect(data[i]);
        }
    }

    public void generate() {
        generateData();
        generateCodes();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            result.append(data[i].toString());// заменить на String.format
            result.append("->");
            result.append(encodedData[i].toString());
            result.append("\n <br>");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CodeTable x = new CodeTable(new BitMatrix("100010010101001001", 3, 6));
        x.generate();
        System.out.println(x);
    }
}
