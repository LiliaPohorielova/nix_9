package ua.com.alevel;

public class MathSet<N extends Number> {

    N[] numbers;
    int maxCapacity;
    int size;
    static final int DEFAULT_CAPACITY = 10;

    public MathSet() {
        this(DEFAULT_CAPACITY);
    }

    public MathSet(int capacity) {
        this.maxCapacity = capacity;
        numbers = (N[]) new Number[capacity];
        size = 0;
    }

    public MathSet(N[] arrayOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = getNumbersWithoutDuplicates(arrayOfNumbers);
        size = numbers.length;//getsize
    }

    public MathSet(N[]... varargsArrayOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = (N[]) new Number[maxCapacity];
        for (N number : numbers) {
            add(number);
        }
    }

    public MathSet(MathSet setOfNumbers) {
        this(DEFAULT_CAPACITY);
        for (int i = 0; i < setOfNumbers.size; i++) {
            add((N) setOfNumbers.numbers[i]);
        }
        size = setOfNumbers.size;
    }

    public MathSet(MathSet... varargsSetOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = (N[]) new Number[maxCapacity];
        for (MathSet<N> number : varargsSetOfNumbers) {
            for (int i = 0; i < number.size; i++) {
                add(number.numbers[i]);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public N getEntity(int index) {

        checkIndex(index);
        return numbers[index];
    }

    public void add(int index, N element) {
        N temp;
        checkIndex(index);
        if (size == numbers.length)
            resize();
        if (index < size) {
            for (int i = size - 1; i >= index; i--) {
                temp = numbers[i];
                numbers[i + 1] = temp;
            }
        }
        numbers[index] = element;
        size++;
    }

    public void add(N element) {
        add(size, element);
    }

    private void resize() {
        N[] resize = (N[]) new Number[numbers.length * 2];
        if (maxCapacity >= 0) System.arraycopy(numbers, 0, resize, 0, maxCapacity);
        this.numbers = resize;
        this.maxCapacity = numbers.length;
    }

    public void update(N a) {
        numbers[indexOf(a)] = a;
    }

    public N[] getEntities() {
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

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
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

    public int indexOf(N o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(N o, int start, int end) {
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
            return "Empty....";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                builder.append(numbers[i]);
            } else {
                builder.append(numbers[i]).append(", ");
            }
        }
        return builder + "";
    }

    public void clear() {
        for (int i = 0; i < getSize(); i++)
            numbers[i] = null;
        size = 0;
    }

    private N[] getNumbersWithoutDuplicates(N[] arrayOfNumbers) {
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
            N[] b = (N[]) new Number[n];
            System.arraycopy(arrayOfNumbers, 0, b, 0, n);
            arrayOfNumbers = b;
        }
        return arrayOfNumbers;
    }

}
