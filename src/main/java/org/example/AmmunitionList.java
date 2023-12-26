package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.*;
/**
 * A custom implementation of a list to manage Ammunition objects.
 * Implements the {@code List} interface.
 */
public class AmmunitionList implements List<Ammunition> {
    // Constants
    private static final int INITIAL_CAPACITY = 15;
    private static final int INCREASE_CAPACITY_BY_PERCENTAGE = 30;

    // Fields
    private Ammunition[] elements;
    private int size;

    /**
     * Constructs an empty AmmunitionList with an initial capacity of 15.
     */
    public AmmunitionList() {
        this.elements = new Ammunition[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructs an AmmunitionList containing a single ammunition.
     *
     * @param ammunition The ammunition to be added to the list.
     */
    public AmmunitionList(Ammunition ammunition) {
        this();
        add(ammunition);
    }

    /**
     * Constructs an AmmunitionList containing elements of the specified collection.
     *
     * @param equipment The collection of ammunition to be added to the list.
     */
    public AmmunitionList(Collection<Ammunition> equipment) {
        this();
        addAll(equipment);
    }

    // Methods

    /**
     * Returns the number of elements in this list.
     *
     * @return The number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return True if this list contains no elements; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o The element whose presence in this list is to be tested.
     * @return True if this list contains the specified element; otherwise, false.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Returns true if this list contains all the elements of the specified collection.
     *
     * @param c The collection to be checked for containment in this list.
     * @return True if this list contains all the elements of the specified collection; otherwise, false.
     */
    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return An iterator over the elements in this list.
     */
    @NotNull
    @Override
    public Iterator<Ammunition> iterator() {
        return new AmmunitionIterator();
    }

    /**
     * An iterator over the elements in this list.
     */
    private class AmmunitionIterator implements Iterator<Ammunition> {
        private int currentIndex = 0;

        /**
         * Returns true if the iteration has more elements.
         *
         * @return True if the iteration has more elements; otherwise, false.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return The next element in the iteration.
         * @throws NoSuchElementException If the iteration has no more elements.
         */
        @Override
        public Ammunition next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in list");
            }
            return elements[currentIndex++];
        }

        /**
         * Removes the last element returned by this iterator from the list.
         *
         * @throws IllegalStateException If the {@code next} method has not been called,
         *                               or the {@code remove} method has been called more than once for the current element.
         */
        @Override
        public void remove() {
            if (currentIndex <= 0 || currentIndex > size) {
                throw new IllegalStateException("Invalid state for remove()");
            }

            AmmunitionList.this.remove(elements[currentIndex - 1]);
            currentIndex--;
        }
    }

    /**
     * Returns an array containing all the elements in this list.
     *
     * @return An array containing all the elements in this list.
     * @throws ArrayStoreException If the runtime type of the specified array is not a supertype
     *                             of the runtime type of every element in this list.
     */
    @NotNull
    @Override
    public Object @NotNull [] toArray() {
        return Arrays.copyOf(this.elements, size);
    }

    /**
     * Returns an array containing all the elements in this list; the runtime type
     * of the returned array is that of the specified array. If the list fits in the specified
     * array, it is returned therein. Otherwise, a new array is allocated with the runtime type
     * of the specified array and the size of this list.
     *
     * @param a The array into which the elements of this list are to be stored,
     *          if it is big enough; otherwise, a new array of the same runtime type is allocated
     *          for this purpose.
     * @return An array containing all the elements in this list.
     * @throws ArrayStoreException If the runtime type of the specified array is not a supertype
     *                             of the runtime type of every element in this list.
     */
    @Override
    public <T> T @NotNull [] toArray(@NotNull T @NotNull [] a) {
        if (a.length < size) {
            return Arrays.copyOf(elements, size, (Class<? extends T[]>) a.getClass());
        } else {
            System.arraycopy(elements, 0, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }
    }

    /**
     * Adds the specified ammunition to the end of this list, if it is not already present.
     *
     * @param ammunition The ammunition to be added to the list.
     * @return True if the ammunition was added to the list; false if it was already present.
     * @throws IllegalArgumentException If the specified ammunition is null.
     */
    @Override
    public boolean add(Ammunition ammunition) {
        if (ammunition == null) {
            throw new IllegalArgumentException("Cannot add null ammunition");
        }

        ensureCapacity(size + 1);

        this.elements[size] = ammunition;
        size++;
        return true;
    }

    /**
     * Increases the capacity of this list if necessary to ensure that it can
     * accommodate at least one more element.
     */
    private void increaseCapacity() {
        int newCapacity = size + size * INCREASE_CAPACITY_BY_PERCENTAGE / 100;
        this.elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   The index at which the specified element is to be inserted.
     * @param element The element to be inserted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     * @throws IllegalArgumentException  If the specified element is null.
     */
    @Override
    public void add(int index, Ammunition element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (element == null) {
            throw new IllegalArgumentException("Cannot add null ammunition");
        }

        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Adds all the elements in the specified collection to this list.
     *
     * @param c The collection containing elements to be added to this list.
     * @return True if this list changed as a result of the call.
     */
    @Override
    public boolean addAll(@NotNull Collection<? extends Ammunition> c) {
        ensureCapacity(size + c.size());

        for (Ammunition ammunition : c) {
            add(ammunition);
        }
        return true;
    }

    /**
     * Inserts all the elements in the specified collection into this list at the specified position.
     *
     * @param index The index at which to insert the first element from the specified collection.
     * @param c     The collection containing elements to be added to this list.
     * @return True if this list changed as a result of the call.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Ammunition> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());

        System.arraycopy(elements, index, elements, index + c.size(), size - index);

        int i = index;
        for (Ammunition ammunition : c) {
            elements[i++] = ammunition;
        }

        size += c.size();
        return true;
    }

    /**
     * Ensures that the capacity of this list is at least the specified minimum capacity.
     *
     * @param minCapacity The desired minimum capacity.
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            increaseCapacity();
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param o The element to be removed from this list, if present.
     * @return True if an element was removed as a result of this call; false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeElementAtIndex(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {

                    removeElementAtIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index The index of the element to be removed.
     * @return The element that was removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    @Override
    public Ammunition remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Ammunition oldValue = elements[index];
        removeElementAtIndex(index);
        return oldValue;
    }

    private void removeElementAtIndex(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size] = null;
        size--;
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     *
     * @param c The collection containing elements to be removed from this list.
     * @return True if this list changed as a result of the call.
     */
    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean modified = false;
        Iterator<Ammunition> iterator = iterator();
        while (iterator.hasNext()) {
            Ammunition element = iterator.next();
            if (c.contains(element)) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     *
     * @param c The collection containing elements to be retained in this list.
     * @return True if this list changed as a result of the call.
     */
    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        Objects.requireNonNull(c);

        boolean modified = false;
        Iterator<Ammunition> iterator = iterator();

        while (iterator.hasNext()) {
            Ammunition element = iterator.next();
            if (!c.contains(element)) {
                iterator.remove();
                modified = true;
            }
        }

        return modified;
    }

    /**
     * Removes all the elements from this list.
     */
    @Override
    public void clear() {
        this.elements = new Ammunition[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index The index of the element to be returned.
     * @return The element at the specified position in this list.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    @Override
    public Ammunition get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   The index of the element to be replaced.
     * @param element The element to be stored at the specified position.
     * @return The element previously at the specified position.
     * @throws IndexOutOfBoundsException If the index is out of range.
     * @throws IllegalArgumentException  If the specified element is null.
     */
    @Override
    public Ammunition set(int index, Ammunition element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (element == null) {
            throw new IllegalArgumentException("Cannot set null ammunition");
        }

        Ammunition oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param o The element to search for.
     * @return The index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list,
     * or -1 if the list does not contain the element.
     *
     * @param o the element to search for
     * @return the index of the last occurrence of the element in the list,
     *         or -1 if the list does not contain the element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     *
     * @return a list iterator over the elements in this list (in proper sequence)
     */
    @NotNull
    @Override
    public ListIterator<Ammunition> listIterator() {
        return new AmmunitionListIterator();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence),
     * starting at the specified position in the list.
     *
     * @param index the starting position of the list iterator
     * @return a list iterator over the elements in this list (in proper sequence),
     *         starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @NotNull
    @Override
    public ListIterator<Ammunition> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return new AmmunitionListIterator(index);
    }


    /**
     * An iterator over the elements in this list, supporting both forward and
     * backward traversal, as well as modifications to the list.
     */
    private class AmmunitionListIterator implements ListIterator<Ammunition> {
        private int currentIndex = 0;
        private boolean canRemoveOrSet = false;

        /**
         * Constructs a new iterator, starting at the beginning of the list.
         */
        public AmmunitionListIterator() {
            this.currentIndex = 0;
        }

        /**
         * Constructs a new iterator, starting at the specified position in the list.
         *
         * @param index the starting position of the iterator
         * @throws IndexOutOfBoundsException if the index is out of range
         *         (index < 0 || index > size)
         */
        public AmmunitionListIterator(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            this.currentIndex = index;
        }

        /**
         * Removes the last element returned by this iterator from the list.
         * This method can only be called once after a call to next() or previous().
         *
         * @throws IllegalStateException if neither next() nor previous() has been called,
         *         or if remove() or set() has been called after the last call to next() or previous()
         */
        @Override
        public void remove() {
            if (!canRemoveOrSet) {
                throw new IllegalStateException("remove() cannot be called at this time.");
            }
            AmmunitionList.this.remove(elements[currentIndex - 1]);
            currentIndex--;
            canRemoveOrSet = false;
        }

        /**
         * Replaces the last element returned by this iterator with the specified element.
         * This method can only be called once after a call to next() or previous().
         *
         * @param ammunition the element to set
         * @throws IllegalStateException if neither next() nor previous() has been called,
         *         or if remove() or set() has been called after the last call to next() or previous()
         */
        @Override
        public void set(Ammunition ammunition) {
            if (!canRemoveOrSet) {
                throw new IllegalStateException("set() cannot be called at this time.");
            }
            elements[currentIndex - 1] = ammunition;
            canRemoveOrSet = false;
        }

        /**
         * Inserts the specified element into the list immediately before the
         * element that would be returned by the next call to next().
         *
         * @param ammunition the element to add
         */
        @Override
        public void add(Ammunition ammunition) {
            AmmunitionList.this.add(currentIndex, ammunition);
            currentIndex++;
            canRemoveOrSet = false;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         *
         * @return the next element in the list
         * @throws NoSuchElementException if there are no more elements in the list
         */
        @Override
        public Ammunition next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in list");
            }
            canRemoveOrSet = true;
            return elements[currentIndex++];
        }

        /**
         * Returns the previous element in the list and moves the cursor position backwards.
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if there are no more elements in the list
         */
        @Override
        public Ammunition previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("No more elements in list");
            }
            canRemoveOrSet = true;
            return elements[--currentIndex];
        }

        /**
         * Checks if there is a next element in the list.
         *
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Checks if there is a previous element in the list.
         *
         * @return true if there is a previous element, false otherwise
         */
        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        /**
         * Returns the index of the next element.
         *
         * @return the index of the next element
         */
        @Override
        public int nextIndex() {
            return currentIndex;
        }

        /**
         * Returns the index of the previous element.
         *
         * @return the index of the previous element
         */
        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     *
     * @param fromIndex the starting index of the sublist (inclusive)
     * @param toIndex the ending index of the sublist (exclusive)
     * @return a sublist view of this list
     * @throws IndexOutOfBoundsException if the specified indices are out of range
     */
    @NotNull
    @Override
    public List<Ammunition> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Invalid subList range");
        }
        AmmunitionList subList = new AmmunitionList();
        Arrays.stream(elements, fromIndex, toIndex).forEach(subList::add);
        return subList;
    }

    /**
     * Returns a string representation of this list.
     *
     * @return A string representation of this list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("AmmunitionList { ");
        for (int i = 0; i < size; i++) {
            result.append(elements[i]);
            if (i != size - 1) {
                result.append(", ");
            }
        }
        result.append(" }");
        return result.toString();
    }
}