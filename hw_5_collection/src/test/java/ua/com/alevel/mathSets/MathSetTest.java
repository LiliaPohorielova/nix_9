package ua.com.alevel.mathSets;

import org.junit.jupiter.api.*;
import ua.com.alevel.MathSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathSetTest {

    private static MathSet mathSet;
    private static MathSet mathSetSecond;
    private static MathSet mathSetThird;

    @Test
    @Order(1)
    public void createMathSetWithDifferentNumbersByDefaultConstructor() {
        mathSet = new MathSet();
        mathSet.add(5);
        mathSet.add(34.5f);
        mathSet.add(2147483649L);
        mathSet.add(29.323242);
        mathSet.add(19);
        mathSet.add(3745.5f);
        mathSet.add(1321);
        mathSet.add(10.545f);
        mathSet.add(34.5f);
        Assertions.assertEquals(mathSet.getSize(), 8);
        mathSet.clear();
    }

    @Test
    @Order(2)
    public void sortingMathSetWithIntegersByDefaultConstructor() {
        mathSet.add(5, 34, 21, 29, 95, 15, 143);
        mathSet.sortAsc(1, 3);
        Assertions.assertEquals(mathSet.getNumber(1), 21);
        mathSet.sortAsc(95);
        Assertions.assertEquals(mathSet.getNumber(4), 15);
        mathSet.sortAsc();
        Assertions.assertEquals(mathSet.getNumber(1), 15);

        mathSet.sortDesc(0, 2);
        Assertions.assertEquals(mathSet.getNumber(0), 21);
        mathSet.sortDesc(34);
        Assertions.assertEquals(mathSet.getNumber(4), 143);
        mathSet.sortDesc();
        Assertions.assertEquals(mathSet.getNumber(0), 143);
        mathSet.clear();
    }

    @Test
    @Order(3)
    public void joinTest() {
        mathSet = new MathSet();
        mathSet.add(5, 34, 21, 29, 95, 15, 143);
        mathSetSecond = new MathSet();
        mathSetSecond.add(4, 14, 321, 9, 95, 15, 143);
        mathSet.join(mathSetSecond);
        Assertions.assertEquals(11, mathSet.getSize());
        mathSetThird = new MathSet();
        mathSetThird.add(7, 42, 1, 0, 95, 15, 13, 14, 21, 143);
        mathSet.join(mathSetSecond, mathSetThird);
        Assertions.assertEquals(16, mathSet.getSize());
        mathSet.clear();
        mathSetSecond.clear();
        mathSetThird.clear();
    }

    @Test
    @Order(4)
    public void intersectionTest() {
        mathSet = new MathSet();
        mathSet.add(5, 34, 21, 29, 95, 15, 143, 123, 171, 23);
        mathSetSecond = new MathSet();
        mathSetSecond.add(4, 14, 321, 9, 95, 15, 143, 113, 111, 13);
        mathSet.intersection(mathSetSecond);
        Assertions.assertEquals(3, mathSet.getSize());
        mathSetThird = new MathSet();
        mathSetThird.add(7, 42, 1, 0, 95, 15, 13, 14, 21, 143);
        mathSet.intersection(mathSetSecond, mathSetThird);
        Assertions.assertEquals(2, mathSet.getSize());
        mathSet.clear();
        mathSetSecond.clear();
        mathSetThird.clear();
    }

    @Test
    @Order(5)
    public void getActionsTest() {
        mathSet = new MathSet();
        mathSet.add(5, 34, 21, 29, 95, 15, 143, 123, 171, 23);
        Assertions.assertEquals(29, mathSet.getNumber(3));
        Assertions.assertEquals(171, mathSet.getMax());
        Assertions.assertEquals(5, mathSet.getMin());
        Assertions.assertEquals(65.9, mathSet.getAverage());
        Assertions.assertEquals(31.5, mathSet.getMedian());
        mathSet.clear();
    }

    @Test
    @Order(6)
    public void cutArrayAndClearTest() {
        mathSet = new MathSet();
        mathSet.add(5, 34, 21, 29, 95, 15, 143, 123, 171, 23);
        mathSetSecond = new MathSet();
        mathSetSecond = mathSet.cut(1,3);
        System.out.println(mathSetSecond);
        Assertions.assertEquals(21, mathSetSecond.getNumber(1));
        mathSet.clear();
        Assertions.assertEquals(0, mathSet.getSize());
        mathSetSecond.clear(new Number[]{34, 21, 29});
        Assertions.assertEquals(0, mathSetSecond.getSize());
    }

    @Test
    @Order(7)
    public void createMathSetWithAnyConstructorTest() {
        mathSet = new MathSet();
        Assertions.assertEquals(10, mathSet.getCapacity());
        mathSet = new MathSet(5);
        Assertions.assertEquals(5, mathSet.getCapacity());
        mathSet = new MathSet(new Number[]{1,2,3,4,5,6,7});
        Assertions.assertEquals(7, mathSet.getCapacity());
        mathSet = new MathSet(new Number[]{1,2,3},new Number[]{1,2,3,4,5,6,7});
        Assertions.assertEquals(10, mathSet.getCapacity());
        mathSet = new MathSet(mathSetSecond = new MathSet());
        Assertions.assertEquals(10, mathSet.getCapacity());
        mathSet = new MathSet(mathSetSecond = new MathSet(5),mathSetThird = new MathSet());
        Assertions.assertEquals(10, mathSet.getCapacity());
    }
}
