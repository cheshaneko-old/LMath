/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * Битовая матрица
 */
public class BitMatrix {

    private BitSequence data[];
    private int row; //число строк 
    private int col; //число слобцов

    BitMatrix(int row, int col) {
        this.row = row;
        this.col = col;
        data = new BitSequence[row];
        for (int i = 0; i < row; ++i) {
            data[i] = new BitSequence(col);
        }
    }

    BitMatrix(String s, int row, int col) { //Конструктор от одной строки, можно
        this.row = row;                      //переделать по массив строк.
        this.col = col;
        data = new BitSequence[row];
        for (int i = 0; i < row; ++i) {
            data[i] = new BitSequence(s.substring(i * col, col * (i + 1)));
        }
    }

    public BitSequence getRow(int i) { //получить одну строку
        return data[i];
    }

    public int getNumberOfRows() { //число строк
        return row;
    }

    public int getNumberOfCols() { // число стобцов
        return col;
    }

    public BitSequence getCol(int j) { //получить один столбец
        BitSequence result = new BitSequence(row);
        for (int i = 0; i < row; ++i) {
            result.set(i, data[i].get(j));
        }
        return result;
    }

    public BitSequence multOnVect(BitSequence v) { //Умножить матрицу на вектор
        BitSequence result = new BitSequence(col);
        if (v.length() > row) {
            throw new RuntimeException("Много");
        }
        for (int i = 0; i < col; ++i) {
            result.set(i, v.and(this.getCol(i)).summary());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < row; ++i) {
            result.append(data[i].toString()); //Хорошо бы заменить на String.format()
            result.append('\n');
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new BitMatrix("1010101110", 2, 5).multOnVect(new BitSequence("11")));
    }
}
