/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * Просто все вместе. Можно было сразу все делать в одном, но я решил разбить
 * по заданиям.
 */
public class MatrixCoding {

    StringBuilder result = new StringBuilder();
    DecodingMatrix matr;
    CodeTable tab;
    Characteristics car;
    Analysys anal;
    Decoder dec;
    int length;
    int codeLength;

    MatrixCoding(String s, int len, int cod) {
        length = len;
        codeLength = cod;
        tab = new CodeTable(new BitMatrix(s, len, cod));
    }

    public void doJob() {
        tab.generate();
        matr = new DecodingMatrix(tab.getEncodedData(), length);
        matr.generateMatrix();
        car = new Characteristics(tab.getEncodedData());
        car.generateAll();
        anal = new Analysys(matr.getLeaders());
        anal.generatepPP();
        dec = new Decoder(matr, tab.getData(), tab.getEncodedData());
    }

    public String getFirst() {
        return tab.toString();
    }

    public String getSecond() {
        return car.toString();
    }

    public String getThird() {
        return matr.toString();
    }

    public String getFourth() {
        return anal.toString();
    }

    public String getFifth(String... x) {
        BitSequence[] onDecoding = new BitSequence[x.length];
        for (int i = 0; i < x.length; ++i) {
            onDecoding[i] = new BitSequence(x[i]);
        }
        dec.decode(onDecoding);
        return dec.toString();
    }

    public static void main(String[] args) {
        MatrixCoding a = new MatrixCoding("011010101101", 2, 6);
        a.doJob();
        System.out.println(a.getFirst());
        System.out.println(a.getSecond());
        System.out.println(a.getThird());
        System.out.println(a.getFourth());
        System.out.println(a.getFifth("10001", "01110", "10101"));
    }
}
