/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.elfapp.lmath;

/**
 *
 * писал не я, выдает все перестановки массива, причем итеративно
 */
import java.util.*;

class Permutations implements Iterator<BitSequence> {

    private Boolean[] arr;
    private int[] ind;
    private boolean has_next;
    public BitSequence output;

    Permutations(BitSequence x) {
        Boolean[] arrs = x.getData();
        this.arr = arrs.clone();
        ind = new int[arrs.length];
        Map<Boolean, Integer> hm = new HashMap<Boolean, Integer>();
        for (int i = 0; i < arr.length; i++) {
            Integer n = hm.get(arr[i]);
            if (n == null) {
                hm.put(arr[i], i);
                n = i;
            }
            ind[i] = n.intValue();
        }
        Arrays.sort(ind);
        output = new BitSequence(arr.length);
        has_next = true;
    }

    @Override
    public boolean hasNext() {
        return has_next;
    }

    @Override
    public BitSequence next() {
        if (!has_next) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < ind.length; i++) {
            output.set(i, arr[ind[i]]);
        }
        has_next = false;
        for (int tail = ind.length - 1; tail > 0; tail--) {
            if (ind[tail - 1] < ind[tail]) {
                int s = ind.length - 1;
                while (ind[tail - 1] >= ind[s]) {
                    s--;
                }
                swap(ind, tail - 1, s);
                for (int i = tail, j = ind.length - 1; i < j; i++, j--) {
                    swap(ind, i, j);
                }
                has_next = true;
                break;
            }
        }
        return output;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    @Override
    public void remove() {
    }

    public static void main(String[] args) {
        Permutations x = new Permutations(new BitSequence("000011"));
        while (x.hasNext()) {
            System.out.println(x.next());
        }
    }
}
