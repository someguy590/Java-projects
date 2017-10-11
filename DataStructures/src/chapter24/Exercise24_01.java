/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter24;

import java.util.Scanner;

public class Exercise24_01 {
    
    public static void main(String[] args) {
        new Exercise24_01();
    }
    
    public Exercise24_01() {
//    String[] name1 = {"Tom", "George", "Peter", "Jean", "Jane"};
//    String[] name2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};
//    String[] name3 = {"Tom", "Peter"};
        Scanner input = new Scanner(System.in);
        
        String[] name1 = new String[5];
        String[] name2 = new String[5];
        String[] name3 = new String[2];
        
        System.out.print("Enter five strings for array name1 separated by space: ");
        for (int i = 0; i < 5; i++) {
            name1[i] = input.next();
        }
        
        System.out.print("Enter five strings for array name2 separated by space: ");
        for (int i = 0; i < 5; i++) {
            name2[i] = input.next();
        }
        
        System.out.print("Enter two strings for array name3 separated by space: ");
        for (int i = 0; i < 2; i++) {
            name3[i] = input.next();
        }
        
        MyList<String> list1 = new MyArrayList<>(name1);
        MyList<String> list2 = new MyArrayList<>(name2);
        
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        
        list1.addAll(list2);
        System.out.println("After addAll:" + list1 + "\n");
        
        list1 = new MyArrayList<>(name1);
        list2 = new MyArrayList<>(name2);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        
        list1.removeAll(list2);
        System.out.println("After removeAll:" + list1 + "\n");
        
        list1 = new MyArrayList<>(name1);
        list2 = new MyArrayList<>(name2);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        
        list1.retainAll(list2);
        System.out.println("After retainAll:" + list1 + "\n");
        
        list1 = new MyArrayList<>(name1);
        list2 = new MyArrayList<>(name2);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        
        list1.retainAll(list2);
        System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");
        
        list1 = new MyArrayList<>(name1);
        list2 = new MyArrayList<>(name3);
        
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");
        
        Object[] name4 = list1.toArray();
        
        System.out.print("name4: ");
        
        for (Object e : name4) {
            System.out.print(e + " ");
        }
        
        String[] name5 = new String[list1.size()];
        String[] name6 = list1.toArray(name5);
        
        System.out.print("\nname6: ");
        
        for (Object e : name6) {
            System.out.print(e + " ");
        }
    }
}

class MyArrayList<E> extends MyAbstractList<E> {
  public static final int INITIAL_CAPACITY = 16;
  private E[] data = (E[])new Object[INITIAL_CAPACITY];

  /** Create a default list */
  public MyArrayList() {
  }

  /** Create a list from an array of objects */
  public MyArrayList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]); // Warning: don’t use super(objects)! 
  }

  @Override /** Add a new element at the specified index */
  public void add(int index, E e) {   
    ensureCapacity();

    // Move the elements to the right after the specified index
    for (int i = size - 1; i >= index; i--)
      data[i + 1] = data[i];

    // Insert new element to data[index]
    data[index] = e;

    // Increase size by 1
    size++;
  }

  /** Create a new larger array, double the current size + 1 */
  private void ensureCapacity() {
    if (size >= data.length) {
      E[] newData = (E[])(new Object[size * 2 + 1]);
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    }
  }

  @Override /** Clear the list */
  public void clear() {
    data = (E[])new Object[INITIAL_CAPACITY];
    size = 0;
  }

  @Override /** Return true if this list contains the element */
  public boolean contains(E e) {
    for (int i = 0; i < size; i++)
      if (e.equals(data[i])) return true;

    return false;
  }

  @Override /** Return the element at the specified index */
  public E get(int index) {
    checkIndex(index);
    return data[index];
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);
  }
  
  @Override /** Return the index of the first matching element 
   *  in this list. Return -1 if no match. */
  public int indexOf(E e) {
    for (int i = 0; i < size; i++)
      if (e.equals(data[i])) return i;

    return -1;
  }

  @Override /** Return the index of the last matching element 
   *  in this list. Return -1 if no match. */
  public int lastIndexOf(E e) {
    for (int i = size - 1; i >= 0; i--)
      if (e.equals(data[i])) return i;

    return -1;
  }

  @Override /** Remove the element at the specified position 
   *  in this list. Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index) {
    checkIndex(index);
    
    E e = data[index];

    // Shift data to the left
    for (int j = index; j < size - 1; j++)
      data[j] = data[j + 1];

    data[size - 1] = null; // This element is now null

    // Decrement size
    size--;

    return e;
  }

  @Override /** Replace the element at the specified position 
   *  in this list with the specified element. */
  public E set(int index, E e) {
    checkIndex(index);
    E old = data[index];
    data[index] = e;
    return old;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    for (int i = 0; i < size; i++) {
      result.append(data[i]);
      if (i < size - 1) result.append(", ");
    }

    return result.toString() + "]";
  }

  /** Trims the capacity to current size */
  public void trimToSize() {
    if (size != data.length) { 
      E[] newData = (E[])(new Object[size]);
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    } // If size == capacity, no need to trim
  }

  @Override /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new ArrayListIterator();
  }
 
  private class ArrayListIterator 
      implements java.util.Iterator<E> {
    private int current = 0; // Current index 

    @Override
    public boolean hasNext() {
      return (current < size);
    }

    @Override
    public E next() {
      return data[current++];
    }

    @Override
    public void remove() {
      MyArrayList.this.remove(current);
    }
  }
}

abstract class MyAbstractList<E> implements MyList<E> {
  protected int size = 0; // The size of the list

  /** Create a default list */
  protected MyAbstractList() {
  }

  /** Create a list from an array of objects */
  protected MyAbstractList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  @Override /** Add a new element at the end of this list */
  public void add(E e) {
    add(size, e);
  }
  
  /** Adds the elements in otherList to this list
   * Returns true if this list changed as a result of the call */
  @Override
  public boolean addAll(MyList<E> otherList) {
      int beforeSize = this.size;
      
      for (E element : otherList)
          this.add(element);
      
      return beforeSize != this.size;
  }
  
  /** Removes all the elements in otherList from this list
   * Returns true if this list changed as a result of the call */
  @Override
  public boolean removeAll(MyList<E> otherList) {
      int beforeSize = this.size;
      
      for (E otherListElement : otherList) {
          for (E thisListElement : this) {
              if (otherListElement.equals(thisListElement))
                  this.remove(thisListElement);
          }
      }
      
      return beforeSize != this.size;
  }
  
  /** Retains the elements in this list that are also in otherList
   * Returns true if this list changed as a result of the call */
  
  @Override
   public boolean retainAll(MyList<E> otherList) {
      int beforeSize = this.size;
      MyArrayList<E> temp = new MyArrayList<>();
      
        for (E thisListElement : this) {
            for (E otherListElement : otherList) {
                if (thisListElement.equals(otherListElement)) {
                    temp.add(thisListElement);
                    break;
                }
            }
        }
        
        if (beforeSize == temp.size)
            return false;
        
        this.clear();
        for (E element : temp)
            this.add(element);
        return true;
    }

    /**
     * Returns true if all the elements in this list are also in otherList
     */
  @Override
    public boolean containsAll(MyList<E> otherList) {
        for (E otherListElement : otherList) {
            boolean isContained = false;
            for (E thisListElement : this) {
                if (otherListElement.equals(thisListElement)) {
                    isContained = true;
                    break;
                }
            }
            
            if (!isContained)
                return false;
        }
        
        return true;
    }
   
   /** Returns array version of this list */
  @Override
   public E[] toArray() {
       E[] list = (E[])new Object[this.size];
       
       for (int i = 0; i < this.size; i++)
           list[i] = this.get(i);
       
       return list;
   }
   
   /** Writes elements of this list into array parameter if there is enough room
    *  Otherwise writes elements into a new list with enough room
    *  Returns array version of this list */
  @Override
   public E[] toArray(E[] a) {
       E[] list = a;
       if (this.size > a.length)
           list = (E[])new Object[this.size];
       
       for (int i = 0; i < this.size; i++)
           list[i] = this.get(i);
       
       return list;
   }
  
  @Override /** Return true if this list contains no elements */
  public boolean isEmpty() {
    return size == 0;
  }

  @Override /** Return the number of elements in this list */
  public int size() {
    return size;
  }

  @Override /** Remove the first occurrence of the element e 
   *  from this list. Shift any subsequent elements to the left.
   *  Return true if the element is removed. */
  public boolean remove(E e) {
    if (indexOf(e) >= 0) {
      remove(indexOf(e));
      return true;
    }
    else
      return false;
  }
}

interface MyList<E> extends java.lang.Iterable<E> {
  /** Add a new element at the end of this list */
  public void add(E e);
  
  /** Adds the elements in otherList to this list
   * Returns true if this list changed as a result of the call */
  public boolean addAll(MyList<E> otherList);
  
  /** Removes all the elements in otherList from this list
   * Returns true if this list changed as a result of the call */
  public boolean removeAll(MyList<E> otherList);
  
  /** Retains the elements in this list that are also in otherList
   * Returns true if this list changed as a result of the call */
   public boolean retainAll(MyList<E> otherlist);
   
   /** Returns true if all the elements in this list are also in otherList */
   public boolean containsAll(MyList<E> otherList);
   
   /** Returns array version of this list */
   public E[] toArray();
   
   /** Writes elements of this list into array parameter if there is enough room
    *  Otherwise writes elements into a new list with enough room
    *  Returns array version of this list */
   public E[] toArray(E[] a);

  /** Add a new element at the specified index in this list */
  public void add(int index, E e);

  /** Clear the list */
  public void clear();

  /** Return true if this list contains the element */
  public boolean contains(E e);
  
  /** Return the element from this list at the specified index */
  public E get(int index);
  
  /** Return the index of the first matching element in this list.
   *  Return -1 if no match. */
  public int indexOf(E e);
  
  /** Return true if this list contains no elements */
  public boolean isEmpty();
  
  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
  public int lastIndexOf(E e);
  
  /** Remove the first occurrence of the element o from this list.
   *  Shift any subsequent elements to the left.
   *  Return true if the element is removed. */
  public boolean remove(E e);
  
  /** Remove the element at the specified position in this list
   *  Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index);
  
  /** Replace the element at the specified position in this list
   *  with the specified element and returns the new set. */
  public Object set(int index, E e);
  
  /** Return the number of elements in this list */
  public int size();
}