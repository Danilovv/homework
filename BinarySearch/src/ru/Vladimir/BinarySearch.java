package ru.Vladimir;

/**
 * Created by vladimir_danilov on 17-Dec-14.
 */
public class BinarySearch {
    /*
     * Take two lines, sort second and search nums from first line at second.
     */
    private static int[] _toSearch;
    private static int[] _whereSearch;

    public static int[][] start (int[][] nums) {
        _toSearch = nums[0];
        _whereSearch = nums[1];
        int[] found = new int[_toSearch.length];
        sort();
        for(int i=0;i<found.length;i++) {
            System.err.println(_toSearch[i] + " " + _whereSearch[i]);
            found[i] = search(_toSearch[i]);
        }
        return new int[][] {found, _whereSearch};
    }

    private static int search(int num) {
        int middleIndex = _whereSearch.length/2;
        int di = 1;
        if (num == _whereSearch[middleIndex]) return middleIndex;
        if (num < _whereSearch[middleIndex]) di *= -1;
        for(int index = 0; index >= 0 && index <= _whereSearch.length-1; index += di) {
            if(num == _whereSearch[index]) return index;
        }
        return -1;
    }

    private static void sort() {
        int buffer;
        for(int i=0;i<_whereSearch.length;i++) {
            for(int j=0;j<_whereSearch.length-1;j++) {
                if(_whereSearch[j]>_whereSearch[j+1]) {
                    buffer = _whereSearch[j];
                    _whereSearch[j] = _whereSearch[j+1];
                    _whereSearch[j+1] = buffer;
                }
            }
        }
    }

}
