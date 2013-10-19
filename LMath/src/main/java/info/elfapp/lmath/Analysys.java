/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * Решает четвертую задачку(похожую на 2-ую) определяет хар-ки полученного кода
 */
public class Analysys {

    BitSequence[] codes;
    StringBuilder pPP = new StringBuilder(); //Вероятность правильной передачи
    int weights[]; //Веса
    StringBuilder mistakes = new StringBuilder(); //Кол-во испр. ошибок

    Analysys(BitSequence[] c) {
        codes = c;
    }

    public void generatepPP() { //Похоже на generatePnoo
        weights = new int[codes[0].length() + 1];
        for (int i = 1; i < codes.length; ++i) {
            weights[codes[i].weight()]++;
        }
        pPP.append(String.format("P<sub>пп</sub>=p<sup>%d</sup>+", weights.length-1));
        for (int i = 1; i < weights.length; ++i) {
            if (weights[i] != 0) {
                pPP.append(String.format("%dp<sup>%d</sup>q<sup>%d</sup>+", weights[i], weights.length - i - 1, i));
                mistakes.append(String.format("Исправляет %d %d-кратных ошибок\n <br>", weights[i], i));
            }
        }
        pPP.deleteCharAt(pPP.length() - 1).append("\n <br>");
    }

    @Override
    public String toString() {
        return pPP.append(mistakes.toString()).toString();
    }

    public static void main(String[] args) {
        CodeTable a = new CodeTable(new BitMatrix("1010101110", 2, 5));
        a.generate();
        DecodingMatrix x = new DecodingMatrix(a.getEncodedData(), 2);
        x.generateMatrix();
        Analysys b = new Analysys(x.getLeaders());
        b.generatepPP();
        System.out.println(b);
    }
}
