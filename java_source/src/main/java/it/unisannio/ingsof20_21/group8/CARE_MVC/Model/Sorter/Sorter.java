/**
 * @author giuliano ranauro
 * Date: 13/05/2021 22:01
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Sorter;

public class Sorter<T extends Comparable<? super T>> {
    private T[] toBeSorted;

    /**from https://github.com/follen99/AlgorithmsAndDataStructures*/
    public Sorter(T[] arr){
        this.toBeSorted = arr;
    }
    public void sort(){
        this.quicksort(this.toBeSorted, 0,toBeSorted.length-1);
    }

    private void quicksort(T[] array, int startIndex, int endIndex)
    {
        if (startIndex < endIndex)
        {
            int pivotIndex = partition(array, startIndex, endIndex);
            // sort the left sub-array
            quicksort(array, startIndex, pivotIndex);
            // sort the right sub-array
            quicksort(array, pivotIndex + 1, endIndex);
        }
    }

    private int partition(T[] array, int startIndex, int endIndex)
    {
        int pivotIndex = (startIndex + endIndex) / 2;
        T pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true)
        {
            do startIndex++; while (array[startIndex].compareTo(pivotValue) < 0) ;

            do endIndex--; while (array[endIndex].compareTo(pivotValue) > 0) ;

            if (startIndex >= endIndex) return endIndex;

            T temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }

    public T[] getToBeSorted() {
        return toBeSorted;
    }
}
