package com.egs;

import lombok.Data;

import java.util.LinkedList;


public class ArrayAndLinkedLists {
    private static final int[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
    int[] array = new int[DEFAULT_CAPACITY];
    int[] deleteByIndex;
    private int k;
    public int temp;
    private int size;
    private int[] elementData;

    public static final int DEFAULT_CAPACITY = 10;


    static class MyNode<E> {
        E data;
        MyNode<E> next;
        MyNode<E> previous;

        public MyNode(MyNode<E> previous, E data, MyNode<E> next) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
        //Add the element in array list or linked list
        boolean add(E e) {
            ArrayAndLinkedLists list = new ArrayAndLinkedLists();
            for (int i = list.array.length; i > 0; i--) {
                list.array[list.array.length - 1] = (int) e;
            }
            return true;
        }

        //Add the element in array list or linked list by index
        void add(int index, E element) {
            ArrayAndLinkedLists list = new ArrayAndLinkedLists();
            for (int i = 0; i < list.array.length; i++) {
                if (i == index) {
                    list.array[i] = (int) element;
                }
            }
        }

        //Remove the element from LinkedList
        public MyNode<LinkedList<Integer[]>> remove(LinkedList<E> e, int index) {
            ArrayAndLinkedLists list = new ArrayAndLinkedLists();
            for (int i = 0; i < e.size(); i++) {
                if (i != index) {
                    list.array[i] = (int) e.get(i);
                } else {
                    list.array[i] = (int) e.get(i + 1);
                }
            }
            return null;
        }

        public void remove(int index, Languages languages) {
            LinkedList<E> e = new LinkedList<>();
            e.add(0, null);
            MyNode<LinkedList<Integer[]>> arr = remove(e, 0);
            for (int i = 0; i < arr.data.size(); i++) {
                if (i != index) {
                    System.out.println(arr.data);
                }
            }
            if (languages == Languages.hy) {
                System.out.println("Դուք հաջողությամբ հեռացրել եք էլեմենտը");
            } else if (languages == Languages.fr) {
                System.out.println("Vous avez supprimé l'article avec succès");
            } else System.out.println("You have successfully removed the item");

        }
    }

    //It is used to insert the specified element at the specified position in a list.
    public <E> void add(int index, E element) {
        for (int i = 0; i < array.length - 1; i++) {
            if (i == index) {
                temp = array[i];
                array[i] = (int) element;
                array[i + 1] = temp;

                System.out.print(array[i] + " ");
            }
        }
    }

    //It is used to append the specified element at the end of a list.


    //It is used to remove all of the elements from this list.
    public void clear() {
        final int[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = 0;
    }

    //It is used to enhance the capacity of an ArrayList instance.
    public int ensureCapacity(int requiredCapacity) {
        return DEFAULT_CAPACITY + requiredCapacity;
    }

    //It is used to return the index in this list of the last occurrence of
    // the specified element, or -1 if the list does not contain this element.
    public int lastIndexOf(Object o) {
        int[] a = {1, 40, 25, 33, 40, 5};
        for (int i = a.length - 1; i >= 0; i--) {
            if (o.equals(a[i])) {
                return i;
            }
        }
        return -1;
    }

    //It is used to return the first element in a list.
    public void getFirst() {
        int[] a = {1, 40, 25, 33, 40, 5};
        System.out.println(a[0]);
    }

    //It is used to return the last element in a list.
    public void getLast() {
        int[] a = {1, 40, 25, 33, 40, 5};
        System.out.println(a[a.length - 1]);
    }

    public void deleteElement(int index) {
        this.deleteByIndex = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                deleteByIndex[i] = array[i];
            } else {
                k = i;
                break;
            }
            System.out.print(deleteByIndex[i] + " ,");
        }
        for (int i = k + 1; i < array.length; i++) {
            deleteByIndex[i] = array[i];
            System.out.print(deleteByIndex[i] + " ,");
        }
    }
}
