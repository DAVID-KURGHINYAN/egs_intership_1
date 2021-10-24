package com.egs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayAndLinkedListsTest {
    private final ArrayAndLinkedLists lists;


    public ArrayAndLinkedListsTest() {
        lists = new ArrayAndLinkedLists();

    }

    @Test
    void add() {
        Assertions.assertEquals(lists.array.length,10);
    }


    @Test
    void remove() {
    }

    @Test
    void clear() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void getFirst() {
    }

    @Test
    void getLast() {
    }
}