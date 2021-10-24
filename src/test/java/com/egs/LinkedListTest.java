package com.egs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedListTest {
    private final LinkedList list;

    public
    LinkedListTest() {
        list = new LinkedList();
    }

    @Test
    //Check the method that takes an argument (LinkedList list, int data).
    void insert() {
        try {
            Assertions.assertNull(list);
            throw new NullPointerException();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

        System.out.println(list.getClass().getModifiers());
        int countModifiers = list.getClass().getModifiers();

        //Check how many access modifiers we have in method and equals.
        Assertions.assertEquals(countModifiers,5);

        //Does our class have an inner class?
        Assertions.assertTrue(list.getClass().isAnonymousClass());
    }

    @Test
    //Check the method that takes an argument (LinkedList list).
    void printList() {
        try {
            Assertions.assertEquals(list.getClass().getMethod("printList"),"printList");
            throw new NoSuchMethodException();
        }catch (NoSuchMethodException e){
            System.out.println("It`s bad");
        }

    }
}