package ua.com.alevel;

public class MathSet {

    Number[] numbers;
    int maxCapacity;
    int size;
    static final int DEFAULT_CAPACITY = 10;
    boolean capacityFromUser = false;

    public MathSet() {
        maxCapacity = DEFAULT_CAPACITY;
        numbers = new Number[DEFAULT_CAPACITY];
        size = 0;
    }

    public MathSet(int capacity) {
        maxCapacity = capacity;
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

    public MathSet(Number[]... arraysOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = new Number[maxCapacity];
        for (Number[] number : arraysOfNumbers) {
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

    public MathSet(MathSet... setsOfNumbers) {
        this.maxCapacity = DEFAULT_CAPACITY;
        numbers = new Number[maxCapacity];
        for (MathSet number : setsOfNumbers) {
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
                throw new IndexOutOfBoundsException("You can't add elements, maxCapacity = " + maxCapacity);
            }
            if (size == numbers.length) resize();
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
        for (int i = 0; i < size; i++) {
            if (numbers[i].equals(element)) {
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
        sortAsc(0, size-1);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            if ((lastIndex - firstIndex) > 0) {
                for (int i = firstIndex; i < lastIndex; i++) {
                    if (numbers[i].doubleValue() > numbers[i + 1].doubleValue()) {
                        isSorted = false;
                        Number temp = numbers[i];
                        numbers[i] = numbers[i + 1];
                        numbers[i + 1] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(Number number) {
        int indexOfNumber = getIndex(number);
        sortAsc(indexOfNumber, size);
    }

    private int getIndex(Number number) {
        for (int i = 0; i < size; i++) {
            if (numbers[i] != null && numbers[i].equals(number)) {
                return i;
            }
        }
        return size;
    }

    public void sortDesc() {
        sortDesc(0, size-1);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            if ((lastIndex - firstIndex) > 0) {
                for (int i = firstIndex; i < lastIndex; i++) {
                    if (numbers[i].doubleValue() < numbers[i + 1].doubleValue()) {
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

    public int indexOf(Number number) {
        return indexOfRange(number, 0, size);
    }

    int indexOfRange(Number number, int start, int end) {
        Number[] arrayOfNumbers = numbers;
        if (number == null) {
            for (int i = start; i < end; i++) {
                if (arrayOfNumbers[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (number.equals(arrayOfNumbers[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    public Number getMax() {
        Number max;
        if (numbers.length == 0) return 0;
        else max = numbers[0].doubleValue();
        for (int i = 1; i < numbers.length; i++)
            if (numbers[i].doubleValue() > max.doubleValue())
                max = numbers[i].doubleValue();
        return max;
    }

    public Number getMin() {
        Number min;
        if (numbers.length == 0) return 0;
        else min = numbers[0].doubleValue();
        for (int i = 1; i < numbers.length; i++)
            if (numbers[i].doubleValue() < min.doubleValue())
                min = numbers[i].doubleValue();
        return min;
    }

    public Number getAverage() {
        double arithmeticalMean = 0d;
        for (int i = 0; i < numbers.length; i++)
            arithmeticalMean += numbers[i].doubleValue();
        return arithmeticalMean / numbers.length;
    }

    public Number getMedian() {
        MathSet temp = new MathSet(this);
        temp.sortAsc();
        int medianIndex = size / 2;
        Number medianNumber;
        if (medianIndex % 2 == 1) {
            medianNumber = (temp.getNumber(medianIndex).doubleValue() + temp.getNumber(medianIndex - 1).doubleValue()) / 2;
        } else {
            medianNumber = temp.getNumber(medianIndex);
        }
        return medianNumber;
    }

    public void join(MathSet mathSet) {
        for (int i = 0; i < mathSet.size; i++) {
            add((mathSet.numbers[i]));
        }
    }

    public void join(MathSet... setsOfNumbers) {
        for (int i = 0; i < setsOfNumbers.length; i++) {
            Number[] arrayOfNumbers = setsOfNumbers[i].toArray();
            for (int j = 0; j < arrayOfNumbers.length; j++) {
                add(arrayOfNumbers[j]);
            }
        }
    }

    public void intersection(MathSet mathSet) {
        Number[] arrayOfNumbers = new Number[Math.max(numbers.length, mathSet.size)];
        Number[] intersectionArray = mathSet.toArray();
        int mathSetNewIndex = 0;
        for (Number number : numbers) {
            for (Number value : intersectionArray) {
                if (number.equals(value)) {
                    arrayOfNumbers[mathSetNewIndex] = number;
                }
            }
        }
        numbers = arrayOfNumbers;
    }

    public void intersection(MathSet... setsOfNumbers) {
        for (MathSet mathSet : setsOfNumbers) {
            intersection(mathSet);
        }
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

    private Number[] getNumbersWithoutDuplicates(Number[] duplicateNumbers) {
        int n = duplicateNumbers.length;
        for (int i = 0, m = 0; i != n; i++, n = m) {
            for (int j = m = i + 1; j != n; j++) {
                if (duplicateNumbers[j] != duplicateNumbers[i]) {
                    if (m != j) duplicateNumbers[m] = duplicateNumbers[j];
                    m++;
                }
            }
        }
        if (n != duplicateNumbers.length) {
            Number[] b = new Number[n];
            System.arraycopy(duplicateNumbers, 0, b, 0, n);
            duplicateNumbers = b;
        }
        return duplicateNumbers;
    }


    public Number[] toArray() {
        return toArray(0, numbers.length);
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] arrayOfNumbers = new Number[numbers.length];
        for (int i = firstIndex; i < lastIndex; i++) {
            arrayOfNumbers[i] = numbers[i];
        }
        return arrayOfNumbers;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        checkIndex(lastIndex);
        Number[] cutNumbers = new Number[lastIndex + 1 - firstIndex];
        int cutElements = 0;
        for (int i = firstIndex; i < lastIndex + 1 && i < numbers.length; i++) {
            numbers[i] = cutNumbers[cutElements];
            cutElements++;
        }
        return new MathSet(cutNumbers);
    }

    public void clear() {
        for (int i = 0; i < getSize(); i++)
            numbers[i] = null;
        size = 0;
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            for (int i = 0; i < size; i++) {
                if (number.equals(numbers[i])) {
                    for (int j = i; j < size; j++) {
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = null;
                    }
                    size--;
                }
            }
        }
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
        return builder + " ";
    }

}
