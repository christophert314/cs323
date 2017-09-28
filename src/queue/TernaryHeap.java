/* Christopher Tseng CS323 Quiz 1 */
package queue;

import java.util.*;

public class TernaryHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;
    private int size;

    public TernaryHeap() {
        this(Comparator.naturalOrder());
    }

    //Starting node is 0
    //Parent -> child: 3k+1,3k+2,3k+3
    //Child -> parent: (k-1)/3
    public TernaryHeap(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();
        size=0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(size++);
    }

    //recursive method for performing swim
    private void swim(int k) {
        while (k > 0 && comparator.compare(keys.get((k-1)/3),keys.get(k)) < 0) {
            Collections.swap(keys,(k-1)/3,k);
            k=(k-1)/3;
        }
    }

    public T removeAux() {
        Collections.swap(keys,0,--size); //root is index 0
        T max = keys.remove(size);
        sink(0);
        return max;
    }

    private void sink(int k) {
        //i is index of child node to potentially swap with, k is node of parent that may be swapped
        for (int i=k*3+1; i<size; k=i,i=k*3+1) {
            if (i < size-1 && comparator.compare(keys.get(i),keys.get(i+1)) < 0) i++;
            else if (i < size-2 && comparator.compare(keys.get(i),keys.get(i+2)) < 0) i+=2;
            if (i < size-1 && comparator.compare(keys.get(i),keys.get(i+1)) < 0) i++;
            if (comparator.compare(keys.get(k),keys.get(i))>=0) break;
            Collections.swap(keys,k,i);
        }
    }

}
