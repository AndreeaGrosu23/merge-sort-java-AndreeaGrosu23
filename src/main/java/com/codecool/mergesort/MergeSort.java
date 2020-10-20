package com.codecool.mergesort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    /**
     * Sorts the given List in place
     * @param toSort the List to sort. Throws an error if its null
     */
    public List<Integer> sort(List<Integer> toSort) {

        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();

        int center;

        if (toSort == null) {
            throw new IllegalArgumentException();
        } else if (toSort.size() == 1) {
            return toSort;
        } else {
            center = toSort.size() / 2;
            for (int i = 0; i < center; i++) {
                left.add(toSort.get(i));
            }
            for (int i = center; i < toSort.size(); i++) {
                right.add(toSort.get(i));
            }
            left = sort(left);
            right = sort(right);

            merge(left, right, toSort);
        }
        return toSort;
    }

    private void merge(List<Integer> left, List<Integer> right, List<Integer> toSort) {
        int leftIndex = 0;
        int rightIndex = 0;
        int toSortIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( left.get(leftIndex) < right.get(rightIndex) ) {
                toSort.set(toSortIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                toSort.set(toSortIndex, right.get(rightIndex));
                rightIndex++;
            }
            toSortIndex++;
        }

        List<Integer> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been used up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            toSort.set(toSortIndex, rest.get(i));
            toSortIndex++;
        }
    }
}
