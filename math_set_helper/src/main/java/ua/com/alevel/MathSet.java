package ua.com.alevel;

public class MathSet {

    Number[] numbers;
    int maxCapacity;
    int size;
    static final int DEFAULT_CAPACITY = 10;

    public MathSet() {
        this(DEFAULT_CAPACITY);
    }

    public MathSet(int capacity) {
        this.maxCapacity = capacity;
        numbers = new Number[capacity];
        size = 0;
    }

    public MathSet(Number[] arrayOfNumbers) {
        maxCapacity = arrayOfNumbers.length;
        numbers = new Number[maxCapacity];
        for (Number n : arrayOfNumbers) {
            add(n);
        }
    }

    public MathSet(Number[]... varargsArrayOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = new Number[maxCapacity];
        for (Number[] number : varargsArrayOfNumbers) {
            for (Number n : number) {
                add(n);
            }
        }
    }

    public MathSet(MathSet setOfNumbers) {
        this(DEFAULT_CAPACITY);
        for (int i = 0; i < setOfNumbers.size; i++) {
            add(setOfNumbers.numbers[i]);
        }
        size = setOfNumbers.size;
    }

    public MathSet(MathSet... varargsSetOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = new Number[maxCapacity];
        for (MathSet number : varargsSetOfNumbers) {
            for (int i = 0; i < number.size; i++) {
                add(number.numbers[i]);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Number getNumber(int index) {
        checkIndex(index);
        return numbers[index];
    }

    public void add(Number element) {
        if (!alreadyInArray(element)) {
            if (maxCapacity == size) {
                throw new RuntimeException("You can't add elements, maxCapacity = " + maxCapacity);
            }
            if (size == numbers.length)
                resize();
            numbers[size] = element;
            size++;
        }
    }

    public void add(Number... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    private boolean alreadyInArray(Number element) {
        Number[] temp = numbers;
        for (int i = 0; i < size; i++) {
            if (temp[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        Number[] resize = new Number[numbers.length * 2];
        if (maxCapacity >= 0) System.arraycopy(numbers, 0, resize, 0, maxCapacity);
        this.numbers = resize;
        this.maxCapacity = numbers.length;
    }

    public void sortAsc() {
        sortAsc(0, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            if ((lastIndex - firstIndex) > 0) {
                for (int i = firstIndex; i < lastIndex-1; i++) {
                    if (numbers[i].longValue() > numbers[i + 1].longValue()) {
                        isSorted = false;
                        Number temp = numbers[i];
                        numbers[i] = numbers[i + 1];
                        numbers[i + 1] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc() {
        sortAsc(0, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            if ((lastIndex - firstIndex) > 0) {
                for (int i = firstIndex; i < lastIndex-1; i++) {
                    if (numbers[i].longValue() < numbers[i + 1].longValue()) {
                        isSorted = false;
                        Number temp = numbers[i];
                        numbers[i] = numbers[i + 1];
                        numbers[i + 1] = temp;
                    }
                }
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    public int indexOf(Number o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Number o, int start, int end) {
        Number[] es = numbers;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (numbers == null) {
            return "MathSet is empty....";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (numbers[i] != null) {
                if (i == size - 1) {
                    builder.append(numbers[i]);
                } else {
                    builder.append(numbers[i]).append(", ");
                }
            }
        }
        return builder + "";
    }

    public void clear() {
        for (int i = 0; i < getSize(); i++)
            numbers[i] = null;
        size = 0;
    }

    public void delete(int index) {
        checkIndex(index);
        if (index < getSize()) {
            int i = index;
            numbers[index] = null;
            while (i < getSize() - 1) {
                numbers[i] = numbers[i + 1];
                numbers[i + 1] = null;
                i++;
            }
        }
        size--;
    }

    private Number[] getNumbersWithoutDuplicates(Number[] arrayOfNumbers) {
        int n = arrayOfNumbers.length;
        for (int i = 0, m = 0; i != n; i++, n = m) {
            for (int j = m = i + 1; j != n; j++) {
                if (arrayOfNumbers[j] != arrayOfNumbers[i]) {
                    if (m != j) arrayOfNumbers[m] = arrayOfNumbers[j];
                    m++;
                }
            }
        }
        if (n != arrayOfNumbers.length) {
            Number[] b = new Number[n];
            System.arraycopy(arrayOfNumbers, 0, b, 0, n);
            arrayOfNumbers = b;
        }
        return arrayOfNumbers;

       /* Number[] temp = new Number[];
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            if (!alreadyInArray(arrayOfNumbers[i])) {
                add(arrayOfNumbers[i]);
            }
        }*/
    }

    public void update(Number a) {
        numbers[indexOf(a)] = a;
    }

    public Number[] getEntities() {
        return numbers;
    }

    public void deleteLastElement() {
        if (size == 0) {
            throw new RuntimeException("list is empty: cannot delete");
        }
        size--;
        numbers[size] = null;
    }

    public void deleteFirstElement() {
        for (int i = 0; i < size - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        numbers[size - 1] = null;
        size--;
    }

}
