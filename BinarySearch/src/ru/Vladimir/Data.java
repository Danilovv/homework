package ru.Vladimir;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Vladimir_Danilov on 19-Dec-14.
 */
public class Data {

    private final int MAX_COUNT;   // = ((int) sSize.getWidth() - 20) / (25 + 5);
    private final int MIN_COUNT = 2;

    private ArrayList<Integer> _whatToSearch;
    private ArrayList<Integer> _whereSearch;
    private ArrayList<Integer>[] _searchArrays;

    Data(int _MAX_COUNT) {
        MAX_COUNT = _MAX_COUNT;
        _whatToSearch = new ArrayList<Integer>(MAX_COUNT);
        _whereSearch = new ArrayList<Integer>(MAX_COUNT);
        _searchArrays = new ArrayList[] { _whatToSearch, _whereSearch };
    }

    public void setLengths(int[] lengths) {
        for (int i = 0; i < _searchArrays.length; i++) {
            if (lengths[i] == _searchArrays[i].size()) continue;
            changeLength(_searchArrays[i], lengths[i]);
        }
    }

    public ArrayList<Integer>[] get_searchArrays () {
        return _searchArrays;
    }

    public int[] getLengths() {
        return new int[]{ _searchArrays[0].size(), _searchArrays[1].size()};
    }

    public boolean isLengthsFitLimits(int[] lengths) {
        for(int length : lengths) {
            if (length < MIN_COUNT) return false;
            if (length > MAX_COUNT) return false;
        }
        return true;
    }

    public void normalizeLengths(int[] lengths) {
        for(int i = 0; i<lengths.length;i++) {
            if (lengths[i] < MIN_COUNT) lengths[i] = MIN_COUNT;
            if (lengths[i] > MAX_COUNT) lengths[i] = MAX_COUNT;
        }
        setLengths(lengths);
    }

    private void changeLength(ArrayList<Integer> searchArray, int newLength) {
        Random randomNum = new Random();
        do {
            if (newLength > searchArray.size()) {
                searchArray.add(randomNum.nextInt(100));
            }
            if (newLength < searchArray.size()) {
                searchArray.remove(searchArray.size() - 1);
            }
        } while (searchArray.size() != newLength);
    }
}
