package org.example;


import org.example.ammunition.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AmmunitionListTest {

    private AmmunitionList ammunitionList;
    private static Sword sword;
    private static Shield shield;
    private static Armor armor;
    private static Helmet helmet;

    @BeforeAll
    public static void setUpBeforeAll() {
        try {
            sword = new Sword("Silver Sword", 5.0, 200.0, 10);
            shield = new Shield("Steel Shield", 8.0, 100.0, 8);
            armor = new Armor("Knight Armor", 15.0, 300.0, 15);
            helmet = new Helmet("Knight Helmet", 2.0, 150.0, 15);
        } catch (AmmunitionException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        ammunitionList = new AmmunitionList();
    }

    @Test
    void emptyConstructor() {
        assertTrue(ammunitionList.isEmpty());
        assertEquals(0, ammunitionList.size());
    }

    @Test
    void constructor_WithSingleAmmunition() {
        AmmunitionList ammunitionList = new AmmunitionList(armor);
        assertFalse(ammunitionList.isEmpty());
        assertEquals(1, ammunitionList.size());
        assertTrue(ammunitionList.contains(armor));
    }

    @Test
    void constructor_WithCollection() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);

        AmmunitionList ammunitionList = new AmmunitionList(ammunitionCollection);
        assertFalse(ammunitionList.isEmpty());
        assertEquals(2, ammunitionList.size());
        assertTrue(ammunitionList.containsAll(ammunitionCollection));
    }

    @Test
    void size_EmptyList() {
        assertEquals(0, ammunitionList.size());
    }

    @Test
    void size() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertEquals(2, ammunitionList.size());
    }

    @Test
    void isEmpty_WhenEmpty() {
        assertTrue(ammunitionList.isEmpty());
    }

    @Test
    void isEmpty_WhenNotEmpty() {
        ammunitionList.add(armor);

        assertFalse(ammunitionList.isEmpty());

    }

    @Test
    void contains() {
        ammunitionList.add(shield);
        assertTrue(ammunitionList.contains(shield));
    }

    @Test
    void containsInvalid() {
        ammunitionList.add(armor);
        assertFalse(ammunitionList.contains(helmet));
    }

    @Test
    void contains_WhenEmpty() {
        assertFalse(ammunitionList.contains(armor));
    }

    @Test
    void containsAll() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);

        ammunitionList.addAll(ammunitionCollection);
        assertTrue(ammunitionList.containsAll(ammunitionCollection));
    }

    @Test
    void containsAll_EmptyCollection() {
        List<Ammunition> emptyList = Collections.emptyList();
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);

        ammunitionList.addAll(ammunitionCollection);
        assertTrue(ammunitionList.containsAll(emptyList));
    }

    @Test
    void containsAll_EmptyCollectionAndEmptyList() {
        List<Ammunition> emptyList = Collections.emptyList();

        assertTrue(ammunitionList.containsAll(emptyList));
    }

    @Test
    void containsAll_NotAllElementsInList() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);
        List<Ammunition> ammunitionCollection2 = Arrays.asList(helmet, shield, armor);

        ammunitionList.addAll(ammunitionCollection);
        assertFalse(ammunitionList.containsAll(ammunitionCollection2));
    }

    @Test
    void containsAll_WhenNoElementsInList() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);
        List<Ammunition> ammunitionCollection2 = Arrays.asList(sword, armor);

        ammunitionList.addAll(ammunitionCollection);
        assertFalse(ammunitionList.containsAll(ammunitionCollection2));
    }

    @Test
    void toArray_Empty() {
        Object[] array = ammunitionList.toArray();

        assertEquals(0, array.length);
    }

    @Test
    void toArray() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);
        Object[] array = ammunitionList.toArray();

        assertArrayEquals(new Object[]{sword, shield}, array);
    }

    @Test
    void toArrayWithParameter_Empty() {
        Ammunition[] array = new Ammunition[3];
        array = ammunitionList.toArray(array);

        assertArrayEquals(new Ammunition[]{null, null, null}, array);

    }

    @Test
    void toArrayWithParameter_SameSize() {
        Ammunition[] array = new Ammunition[2];

        ammunitionList.add(sword);
        ammunitionList.add(shield);

        array = ammunitionList.toArray(array);

        assertArrayEquals(new Ammunition[]{sword, shield}, array);
    }

    @Test
    void toArrayWithParameter_ListSizeGreater() {
        Ammunition[] array = new Ammunition[2];

        ammunitionList.add(sword);
        ammunitionList.add(shield);
        ammunitionList.add(helmet);

        array = ammunitionList.toArray(array);

        assertArrayEquals(new Ammunition[]{sword, shield, helmet}, array);
    }

    @Test
    void toArrayWithParameter_ArraySizeGreater() {
        Ammunition[] array = new Ammunition[3];

        ammunitionList.add(sword);

        array = ammunitionList.toArray(array);

        assertArrayEquals(new Ammunition[]{sword, null, null}, array);
    }

    @Test
    void add() {
        assertTrue(ammunitionList.add(armor));
        assertTrue(ammunitionList.contains(armor));
    }

    @Test
    void add_Null() {
        assertThrows(IllegalArgumentException.class, () -> ammunitionList.add(null));
    }

    @Test
    void addAtIndex() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        ammunitionList.add(1, armor);

        assertEquals(3, ammunitionList.size());
        assertTrue(ammunitionList.contains(sword));
        assertTrue(ammunitionList.contains(armor));
        assertTrue(ammunitionList.contains(shield));
    }

    @Test
    void addAtIndex_EmptyList() {
        ammunitionList.add(0, armor);

        assertEquals(1, ammunitionList.size());
        assertTrue(ammunitionList.contains(armor));
    }

    @Test
    void addAtIndex_NullElement() {
        assertThrows(IllegalArgumentException.class, () -> ammunitionList.add(0, null));
    }

    @Test
    void addAtIndex_InvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.add(-1, sword));
    }

    @Test
    void add_EnsureCapacityCalledWhenNeeded() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        ammunitionList.add(1, armor);

        assertEquals(3, ammunitionList.size());
        assertTrue(ammunitionList.contains(sword));
        assertTrue(ammunitionList.contains(armor));
        assertTrue(ammunitionList.contains(shield));
    }

    @Test
    void addAll_Empty() {
        assertTrue(ammunitionList.addAll(new ArrayList<>()));
    }

    @Test
    void addAll() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);
        assertTrue(ammunitionList.addAll(ammunitionCollection));
        assertTrue(ammunitionList.containsAll(ammunitionCollection));
    }

    @Test
    void addAllAtIndex_ValidIndexAndCollection() {
        ammunitionList.addAll(Arrays.asList(sword, shield));

        assertTrue(ammunitionList.addAll(1, Arrays.asList(armor, helmet)));

        assertEquals(4, ammunitionList.size());
        assertTrue(ammunitionList.contains(sword));
        assertTrue(ammunitionList.contains(armor));
        assertTrue(ammunitionList.contains(helmet));
        assertTrue(ammunitionList.contains(shield));
    }

    @Test
    void addAllAtIndex_EmptyCollection() {
        List<Ammunition> emptyList = Collections.emptyList();
        ammunitionList.addAll(Arrays.asList(sword, shield));

        assertFalse(ammunitionList.addAll(1, emptyList));

        assertEquals(2, ammunitionList.size());
        assertTrue(ammunitionList.contains(sword));
        assertTrue(ammunitionList.contains(shield));
    }

    @Test
    void addAlAtIndex_InvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.addAll(3, Arrays.asList(sword, shield)));
    }

    @Test
    void removeByObject_NullObject() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertFalse(ammunitionList.remove(null));

        assertEquals(2, ammunitionList.size());
    }

    @Test
    void removeByObject() {
        ammunitionList.add(armor);

        assertTrue(ammunitionList.remove(armor));
        assertFalse(ammunitionList.contains(armor));
    }

    @Test
    void removeByObject_Empty() {
        assertFalse(ammunitionList.remove(armor));
    }

    @Test
    void removeByObject_NotInList() {
        ammunitionList.add(shield);

        assertFalse(ammunitionList.remove(armor));
        assertFalse(ammunitionList.isEmpty());
    }

    @Test
    void removeByIndex_EmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.remove(0));
    }

    @Test
    void removeByIndex() {
        ammunitionList.add(armor);

        assertEquals(armor, ammunitionList.remove(0));
        assertFalse(ammunitionList.contains(armor));
    }

    @Test
    void removeByIndex_OutOfBounds() {
        ammunitionList.add(armor);

        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.remove(1));
    }

    @Test
    void removeAll_EmptyList() {
        List<Ammunition> emptyList = Collections.emptyList();

        assertFalse(ammunitionList.removeAll(emptyList));
    }

    @Test
    void removeAll() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);

        ammunitionList.addAll(ammunitionCollection);

        assertTrue(ammunitionList.removeAll(ammunitionCollection));
        assertFalse(ammunitionList.containsAll(ammunitionCollection));
    }

    @Test
    void removeAll_NotAllRemoved() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield, sword);
        List<Ammunition> ammunitionCollection2 = Arrays.asList(helmet, shield);

        ammunitionList.addAll(ammunitionCollection);

        assertTrue(ammunitionList.removeAll(ammunitionCollection2));
        assertTrue(ammunitionList.contains(sword));
        assertFalse(ammunitionList.contains(helmet));
        assertFalse(ammunitionList.contains(shield));
    }

    @Test
    void removeAll_DoNotHaveSameElements() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, sword);
        List<Ammunition> ammunitionCollection2 = Arrays.asList(armor, shield);

        ammunitionList.addAll(ammunitionCollection);

        assertFalse(ammunitionList.removeAll(ammunitionCollection2));
        assertTrue(ammunitionList.contains(sword));
        assertTrue(ammunitionList.contains(helmet));
    }

    @Test
    void removeAll_RemoveEmpty() {
        List<Ammunition> emptyList = Collections.emptyList();
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);

        ammunitionList.addAll(ammunitionCollection);

        assertFalse(ammunitionList.removeAll(emptyList));
    }

    @Test
    void retainAll() {
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);
        List<Ammunition> retainedCollection = Collections.singletonList(shield);

        ammunitionList.addAll(ammunitionCollection);

        assertTrue(ammunitionList.retainAll(retainedCollection));
        assertTrue(ammunitionList.contains(shield));

        assertFalse(ammunitionList.contains(helmet));
    }

    @Test
    void retainAll_Empty() {
        List<Ammunition> emptyList = Collections.emptyList();
        List<Ammunition> ammunitionCollection = Arrays.asList(helmet, shield);
        ammunitionList.addAll(ammunitionCollection);

        assertTrue(ammunitionList.retainAll(emptyList));

        assertFalse(ammunitionList.contains(shield));
        assertFalse(ammunitionList.contains(helmet));
    }

    @Test
    void clear() {
        ammunitionList.add(armor);
        ammunitionList.clear();
        assertTrue(ammunitionList.isEmpty());
    }

    @Test
    void clear_Empty() {
        ammunitionList.clear();
        assertTrue(ammunitionList.isEmpty());
    }

    @Test
    void get() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertEquals(sword, ammunitionList.get(0));
        assertEquals(shield, ammunitionList.get(1));
    }

    @Test
    void get_OutOfBounds_Empty() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.get(0));
    }

    @Test
    void get_OutOfBounds() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.get(2));
    }

    @Test
    void set() {
        ammunitionList.add(sword);

        ammunitionList.set(0, shield);

        assertEquals(shield, ammunitionList.get(0));
        assertFalse(ammunitionList.contains(sword));
    }

    @Test
    void set_Null() {
        ammunitionList.add(sword);

        assertThrows(IllegalArgumentException.class, () -> ammunitionList.set(0, null));
    }

    @Test
    void set_OutOfBounds() {
        ammunitionList.add(sword);

        ammunitionList.set(0, shield);

        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.set(1, shield));
    }

    @Test
    void set_OutOfBounds_Empty() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.set(0, sword));
    }

    @Test
    void indexOf() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertEquals(0, ammunitionList.indexOf(sword));
        assertEquals(1, ammunitionList.indexOf(shield));
    }

    @Test
    void indexOf_NonExistent() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertEquals(-1, ammunitionList.indexOf(armor));
    }
    @Test
    void indexOf_Null() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertEquals(-1, ammunitionList.indexOf(null));
    }

    @Test
    void indexOf_EmptyList() {
        assertEquals(-1, ammunitionList.indexOf(sword));
    }

    @Test
    void lastIndexOf() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);
        ammunitionList.add(sword);

        assertEquals(2, ammunitionList.lastIndexOf(sword));
        assertEquals(1, ammunitionList.lastIndexOf(shield));
    }

    @Test
    void lastIndexOf_NonExistent() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);
        ammunitionList.add(sword);

        assertEquals(-1, ammunitionList.lastIndexOf(armor));
    }

    @Test
    void lastIndexOf_Null() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);
        ammunitionList.add(sword);

        assertEquals(-1, ammunitionList.lastIndexOf(null));
    }

    @Test
    void lastIndexOf_EmptyList() {
        assertEquals(-1, ammunitionList.lastIndexOf(sword));
    }

    @Test
    void iterator_EmptyList() {
        Iterator<Ammunition> iterator = ammunitionList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void iterator_IterateOverAllElements() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor, helmet));

        Iterator<Ammunition> iterator = ammunitionList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(sword, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(shield, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(armor, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(helmet, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void iterator_NextGoBeyondElements() {
        ammunitionList.add(sword);

        Iterator<Ammunition> iterator = ammunitionList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(sword, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void iterator_RemoveExistingElement() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor, helmet));

        Iterator<Ammunition> iterator = ammunitionList.iterator();
        while (iterator.hasNext()) {
            Ammunition ammunition = iterator.next();
            if (ammunition.equals(shield)) {
                iterator.remove();
            }
        }

        assertFalse(ammunitionList.contains(shield));
        assertTrue(ammunitionList.containsAll(Arrays.asList(sword, armor, helmet)));
    }

    @Test
    void iterator_MultipleRemoveCalls() {
        ammunitionList.add(sword);

        Iterator<Ammunition> iterator = ammunitionList.iterator();
        iterator.next();
        iterator.remove();

        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    void listIterator() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);
        ammunitionList.add(armor);

        int count = 0;
        for (Ammunition ammunition : ammunitionList) {
            assertNotNull(ammunition);
            count++;
        }

        assertEquals(3, count);
    }

    @Test
    void listIterator_EmptyList() {
        ListIterator<Ammunition> iterator = ammunitionList.listIterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
    }

    @Test
    void listIterator_NonEmptyList() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor));
        ListIterator<Ammunition> iterator = ammunitionList.listIterator(1);

        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
        assertEquals(1, iterator.nextIndex());
        assertEquals(0, iterator.previousIndex());

        assertEquals(shield, iterator.next());
        assertTrue(iterator.hasPrevious());
        assertEquals(2, iterator.nextIndex());
        assertEquals(1, iterator.previousIndex());
    }

    @Test
    void listIteratorWithIndex() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);
        ammunitionList.add(armor);

        ListIterator<Ammunition> iterator = ammunitionList.listIterator(1);
        assertTrue(iterator.hasNext());
        assertEquals(shield, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(armor, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void listIterator_RemoveElement() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor));
        ListIterator<Ammunition> iterator = ammunitionList.listIterator();

        assertTrue(iterator.hasNext());
        assertEquals(sword, iterator.next());
        iterator.remove();

        assertFalse(ammunitionList.contains(sword));
        assertEquals(2, ammunitionList.size());
    }

    @Test
    void listIterator_SetElement() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor));
        ListIterator<Ammunition> iterator = ammunitionList.listIterator(1);

        assertTrue(iterator.hasNext());
        assertEquals(shield, iterator.next());

        iterator.set(helmet);
        assertTrue(ammunitionList.contains(helmet));
    }

    @Test
    void listIterator_AddElement() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor));
        ListIterator<Ammunition> iterator = ammunitionList.listIterator(1);

        assertTrue(iterator.hasNext());
        assertEquals(shield, iterator.next());

        iterator.add(helmet);
        assertTrue(ammunitionList.contains(helmet));
        assertEquals(4, ammunitionList.size());
    }

    @Test
    void subList() {
        ammunitionList.addAll(Arrays.asList(sword, shield, armor, helmet));

        List<Ammunition> subList = ammunitionList.subList(1, 3);

        assertEquals(2, subList.size());
        assertTrue(subList.contains(shield));
        assertTrue(subList.contains(armor));
        assertFalse(subList.contains(sword));
        assertFalse(subList.contains(helmet));
    }

    @Test
    void subList_EmptyList() {
        List<Ammunition> subList = ammunitionList.subList(0, 0);

        assertTrue(subList.isEmpty());
    }

    @Test
    void subList_InvalidFromIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.subList(-1, 2));
    }

    @Test
    void subList_InvalidToIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.subList(1, 5));
    }

    @Test
    void subList_FromIndexGreaterThanToIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ammunitionList.subList(3, 2));
    }

    @Test
    void testToString() {
        ammunitionList.add(sword);
        ammunitionList.add(shield);

        assertEquals("AmmunitionList { " + sword + ", " + shield + " }", ammunitionList.toString());
    }
}