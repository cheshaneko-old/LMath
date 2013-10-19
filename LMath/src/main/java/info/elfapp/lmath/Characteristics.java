/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * решает вторую задачку, определяет характеристики кода
 */
public class Characteristics {

    private BitSequence[] codes; //закодированные последовательсности
    private int minD;
    private int kf;
    private int kc;
    private String pnoo; //вероятность необнаружения ошибки

    Characteristics(BitSequence[] cod) {
        codes = cod;
    }

    public int findMinD(BitSequence[] cod) { //минимальное расстояние
        int min = cod[1].weight();
        for (int i = 2; i < cod.length; ++i) {
            if (cod[i].weight() < min) {
                min = cod[i].weight();
            }
        }
        return min;
    }

    public void findMinD() {
        this.minD = findMinD(codes);
    }

    public int getKf(int min) { //что-то странное
        return min - 1;
    }

    public int getKc(int min) {
        return (min - 1) / 2;
    }

    public void generateKf() { // К -обнаружения
        kf = getKf(minD);
    }

    public void generateKc() { //К - исправления
        kc = getKc(minD);
    }

    public void generatePnoo() { //Вероятн. необнаружения ошибки
        int weights[] = new int[codes[0].length() + 1]; // индексы-число ошибок
        for (int i = 1; i < codes.length; ++i) {//значение-их кол-во
            weights[codes[i].weight()]++;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < weights.length; ++i) {
            if (weights[i] != 0) {
                result.append(String.format("%dp<sup>%d</sup>q<sup>%d</sup>+", weights[i], weights.length - i - 1, i));
            }

            pnoo = result.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("min d=");//ваще жесть
        result.append(minD);
        result.append("\n <br>");
        result.append("max k<sub>обн</sub>=");
        result.append(kf);
        result.append("\n <br>");
        result.append("max k<sub>исп</sub>=");
        result.append(kc);
        result.append("\n <br>");
        result.append("P<sub>ноо</sub>=");
        result.append(pnoo.substring(0, pnoo.length() - 1));
        result.append("\n <br>");
        return result.toString();
    }

    public void generateAll() {
        findMinD();
        generateKf();
        generateKc();
        generatePnoo();
    }

    public static void main(String[] args) {
        CodeTable a = new CodeTable(new BitMatrix("100110010011001111", 3, 6));
        a.generate();
        Characteristics x = new Characteristics(a.getEncodedData());
        x.generateAll();
        System.out.println(x);
    }
}
