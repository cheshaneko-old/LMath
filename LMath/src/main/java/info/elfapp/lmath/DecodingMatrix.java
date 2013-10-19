/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * Решает третью задачку, строит матрицу декодирования
 */
public class DecodingMatrix {

    BitSequence[] codes; //кода (0-ая строка)
    BitSequence[] leaders; //лидеры (0-ой стобец)
    int rowNumber = 0;
    int colNumber = 0;
    BitSequence[][] table;
    Permutations leaderGenerator; //Генерирует все перестановки массива

    class Pair {

        public int first;
        public int second;

        Pair(int f, int s) {
            first = f;
            second = s;
        }

        Pair() {
            first = -1;
            second = -1;
        }
    }

    DecodingMatrix(BitSequence[] cod, int len) { //cod- кодированные слова
        codes = cod;                              //len - длинна исходных последовательностей
        colNumber = cod.length;
        rowNumber = (int) Math.pow(2, (cod[0].length() - len));
        leaders = new BitSequence[rowNumber];
        leaders[0] = codes[0];
        table = new BitSequence[rowNumber][colNumber];
        for (int i = 0; i < colNumber; ++i) {
            table[0][i] = codes[i];
        }

    }

    public Pair find(BitSequence x) {
        for (int i = 0; i < rowNumber; ++i) {
            for (int j = 0; j < colNumber; ++j) {
                if (table[i][j] == null) {
                    throw new NullPointerException();
                }
                if (x.equals(table[i][j])) {
                    return new Pair(i, j);
                }
            }
        }
        throw new NullPointerException();
    }

    public BitSequence generateStartMinor(int num) { //num- число "1" в последовательности
        StringBuilder result = new StringBuilder(); //Генерирует BitSequence
        int zeros = codes[0].length() - num;//заданной длинны с num единиц в конце
        for (int i = 0; i < zeros; ++i) {
            result.append("0");
        }
        for (int j = zeros; j < codes[0].length(); ++j) {
            result.append("1");
        }
        return new BitSequence(result.toString());
    }

    public void createRow(BitSequence leader, int row) { //заполняет строку
        for (int i = 1; i < colNumber; ++i) { //с помощью выбранного лидера
            table[row][i] = table[0][i].xor(leader);
        }
        table[row][0] = new BitSequence(leader);
    }

    public void generateMatrix() {
        int k = 1;
        leaderGenerator = new Permutations(generateStartMinor(k));
        for (int i = 1; i < rowNumber; ++i) {
            BitSequence temporaryLeader;
            do { //пока не найден лидер, которого ещё не было выдавать следующий
                if (!leaderGenerator.hasNext()) {//если кончились, добавить "1"
                    leaderGenerator = new Permutations(generateStartMinor(++k));
                }
                temporaryLeader = leaderGenerator.next();
                try {
                    find(temporaryLeader);
                } catch (NullPointerException e) {
                    break;
                }
            } while (true);
            leaders[i] = new BitSequence(temporaryLeader); //сохраняем найденного лидера
            createRow(temporaryLeader, i); //создаем строку
        }
    }

    public BitSequence[][] getData() {
        return table;
    }

    public BitSequence get(int i, int j) {
        return table[i][j];
    }

    public BitSequence[] getLeaders() {
        return leaders;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rowNumber; ++i) {
            for (int j = 0; j < colNumber; ++j) {
                result.append(table[i][j].toString());
                result.append(' ');

            }
            result.append("\n <br>");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CodeTable a = new CodeTable(new BitMatrix("1010101110", 2, 5));
        a.generate();
        DecodingMatrix x = new DecodingMatrix(a.getEncodedData(), 2);
        x.generateMatrix();
        System.out.println(x);
    }
}
