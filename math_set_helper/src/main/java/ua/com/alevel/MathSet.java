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
        capacityFromUser = true;
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
            if (capacityFromUser && maxCapacity == size) {
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

    public Number[] getNumbers() {
        return numbers;
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
        sortAsc(0, size - 1);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            if ((lastIndex - firstIndex) > 0) {
                for (int i = firstIndex; i < lastIndex; i++) {
                    if (compareNumber(numbers[i], numbers[i + 1]) > 0) {
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
        sortAsc(indexOfNumber, size - 1);
    }

    private int getIndex(Number number) {
        for (int i = 0; i < size; i++) {
            if (compareNumber(numbers[i], number) == 0) {
                return i;
            }
        }
        throw new RuntimeException("No such index for element");
    }

    public void sortDesc() {
        sortDesc(0, size - 1);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            if ((lastIndex - firstIndex) > 0) {
                for (int i = firstIndex; i < lastIndex; i++) {
                    if (compareNumber(numbers[i], numbers[i + 1]) < 0) {
                        isSorted = false;
                        Number temp = numbers[i];
                        numbers[i] = numbers[i + 1];
                        numbers[i + 1] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc(Number number) {
        int indexOfNumber = getIndex(number);
        sortDesc(indexOfNumber, size - 1);
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
        else max = numbers[0];
        for (int i = 1; i < size; i++)
            if (compareNumber(numbers[i], max) > 0)
                max = numbers[i];
        return max;
    }

    public Number getMin() {
        Number min;
        if (numbers.length == 0) return 0;
        else min = numbers[0];
        for (int i = 1; i < size; i++)
            if (compareNumber(numbers[i], min) < 0)
                min = numbers[i];
        return min;
    }

    public Number getAverage() {
        Number arithmeticalMean = 0;
        for (int i = 0; i < size; i++)
            arithmeticalMean = sumOfNumbers(arithmeticalMean, numbers[i]);
        return divOfNumbers(arithmeticalMean, size);
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
            add(mathSet.numbers[i]);
        }
    }

    public void join(MathSet... setsOfNumbers) {
        for (MathSet number : setsOfNumbers) {
            for (int i = 0; i < number.size; i++) {
                add(number.numbers[i]);
            }
        }
    }

    public void intersection(MathSet mathSet) {
        int newSize = Math.max(numbers.length, mathSet.getSize());
        Number[] result = new Number[newSize];
        Number[] givenMathSetToArray = mathSet.toArray();
        size = 0;
        for (Number number : numbers) {
            for (Number value : givenMathSetToArray) {
                if (number == null || value == null)
                    continue;
                if (compareNumber(number, value) == 0) {
                    result[size] = number;
                    size++;
                }
            }
        }
        numbers = result;
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
        return toArray(0, numbers.length - 1);
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] arrayOfNumbers = new Number[numbers.length];
        for (int i = firstIndex; i < lastIndex; i++) {
            if (numbers[i] != null) arrayOfNumbers[i] = numbers[i];
        }
        return arrayOfNumbers;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        //checkIndex(lastIndex);
        MathSet resCutting = new MathSet();
        for (int i = firstIndex; i < lastIndex + 1 && i < size; i++) {
            resCutting.add(numbers[i]);
        }
        return resCutting;
    }

    public void clear() {
        for (int i = 0; i < getSize(); i++)
            numbers[i] = null;
        size = 0;
    }

    public void clear(Number[] arrayOfNumbers) {
        for (Number number : arrayOfNumbers) {
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

    private int compareNumber(Number n1, Number n2) {
        String classOfNum1 = n1.getClass().getSimpleName();
        String classOfNum2 = n2.getClass().getSimpleName();
        if (classOfNum1.equals(classOfNum2))
            switch (classOfNum1) {
                case "Byte":
                    if (n1.byteValue() > n2.byteValue()) return 1;
                    if (n1.byteValue() < n2.byteValue()) return -1;
                    if (n1.byteValue() == n2.byteValue()) return 0;
                case "Short":
                    if (n1.shortValue() > n2.shortValue()) return 1;
                    if (n1.shortValue() < n2.shortValue()) return -1;
                    if (n1.shortValue() == n2.shortValue()) return 0;
                case "Integer":
                    if (n1.intValue() > n2.intValue()) return 1;
                    if (n1.intValue() < n2.intValue()) return -1;
                    if (n1.intValue() == n2.intValue()) return 0;
                case "Long":
                    if (n1.longValue() > n2.longValue()) return 1;
                    if (n1.longValue() < n2.longValue()) return -1;
                    if (n1.longValue() == n2.longValue()) return 0;
                case "Float":
                    if (n1.floatValue() > n2.floatValue()) return 1;
                    if (n1.floatValue() < n2.floatValue()) return -1;
                    if (n1.floatValue() == n2.floatValue()) return 0;
                case "Double":
                    if (n1.doubleValue() > n2.doubleValue()) return 1;
                    if (n1.doubleValue() < n2.doubleValue()) return -1;
                    if (n1.doubleValue() == n2.doubleValue()) return 0;
            }
        else {
            System.out.println("You can't compare apples and oranges");
            System.out.print("You tried to compare " + n1.getClass().getSimpleName());
            System.out.print(" with " + n2.getClass().getSimpleName());
            throw new RuntimeException("You can't compare apples and oranges");
        }
        return -2;
    }

    private Number sumOfNumbers(Number n1, Number n2) {
        String classOfNum1 = n1.getClass().getSimpleName();
        String classOfNum2 = n2.getClass().getSimpleName();
        if (classOfNum1.equals(classOfNum2))
            switch (classOfNum1) {
                case "Byte":
                    return (n1.byteValue() + n2.byteValue());
                case "Short":
                    return (n1.shortValue() + n2.shortValue());
                case "Integer":
                    return (n1.intValue() + n2.intValue());
                case "Long":
                    return (n1.longValue() + n2.longValue());
                case "Float":
                    return (n1.floatValue() + n2.floatValue());
                case "Double":
                    return (n1.doubleValue() + n2.doubleValue());
            }
        else {
            System.out.println("You can't add apples and oranges");
            System.out.print("You tried to add " + n1.getClass().getSimpleName());
            System.out.print(" with " + n2.getClass().getSimpleName());
            throw new RuntimeException("You can't add apples and oranges");
        }
        return -2;
    }

    private Number divOfNumbers(Number n1, Number n2) {
        String classOfNum1 = n1.getClass().getSimpleName();
        String classOfNum2 = n2.getClass().getSimpleName();
        if (classOfNum1.equals(classOfNum2))
            switch (classOfNum1) {
                case "Byte", "Short", "Integer","Double":
                    return (n1.doubleValue() / n2.doubleValue());
                case "Long":
                    return (n1.longValue() / n2.longValue());
                case "Float":
                    return (n1.floatValue() / n2.floatValue());
            }
        else {
            System.out.println("You can't add apples and oranges");
            System.out.print("You tried to add " + n1.getClass().getSimpleName());
            System.out.print(" with " + n2.getClass().getSimpleName());
            throw new RuntimeException("You can't add apples and oranges");
        }
        return -2;
    }
}
