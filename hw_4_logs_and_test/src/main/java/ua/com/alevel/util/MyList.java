package ua.com.alevel.util;

import java.util.ArrayList;

public class MyList<ENTITY> {

    ENTITY[] users;
    int maxCapacity;
    int countOfEntities;
    static final int DEFAULT_CAPACITY = 100;

    public MyList() {
        this(DEFAULT_CAPACITY);
    }

    public MyList(int capacity) {
        this.maxCapacity = capacity;
        users = (ENTITY[]) new Object[capacity];
        countOfEntities = 0;
    }

    public int getCountOfEntities() {
        return countOfEntities;
    }

    public ENTITY getUser(int index) {

        checkIndex(index);
        return users[index];
    }

    public void add(int index, ENTITY element) {
        ENTITY temp;
        checkIndex(index);
        if (countOfEntities == users.length)
            resize();
        if (index < countOfEntities) {
            for (int i = countOfEntities - 1; i >= index; i--) {
                temp = users[i];
                users[i + 1] = temp;
            }
        }
        users[index] = element;
        countOfEntities++;
    }

    public void add(ENTITY element) {
        add(countOfEntities, element);
    }

    private void resize() {
        ENTITY[] resize = (ENTITY[]) new Object[users.length * 2];
        if (maxCapacity >= 0) System.arraycopy(users, 0, resize, 0, maxCapacity);
        this.users = resize;
        this.maxCapacity = users.length;
    }

   /* public void add(ENTITY a) {
        if (countOfEntities == maxCapacity) {
            ENTITY[] dataNew = (ENTITY[]) new Object[maxCapacity * 2];
            maxCapacity = maxCapacity * 2;
            for (int i = 0; i < countOfEntities; i++) {
                dataNew[i] = users[i];
            }
            users = dataNew;
        }
        users[countOfEntities] = a;
        countOfEntities++;
    }*/

    public void update(ENTITY a) {
        users[indexOf(a)] = a;
    }

    public ENTITY[] getUsers() {
        return users;
    }

    public void deleteLastElement() {
        if (countOfEntities == 0) {
            throw new RuntimeException("list is empty: cannot delete");
        }
        countOfEntities--;
        users[countOfEntities] = null;
    }

    public void deleteFirstElement() {
        for (int i = 0; i < countOfEntities - 1; i++) {
            users[i] = users[i + 1];
        }
        users[countOfEntities - 1] = null;
        countOfEntities--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > countOfEntities)
            throw new IndexOutOfBoundsException();
    }

    public void delete(int index) {
        checkIndex(index);
        if (index < getCountOfEntities()) {
            int i = index;
            users[index] = null;
            while (i < getCountOfEntities() - 1) {
                users[i] = users[i + 1];
                users[i + 1] = null;
                i++;
            }
        }
        countOfEntities--;
    }

    public int indexOf(ENTITY o) {
        return indexOfRange(o, 0, countOfEntities);
    }

    int indexOfRange(ENTITY o, int start, int end) {
        Object[] es = users;
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


//    public MyList<ENTITY> show() {
//        MyList<ENTITY> current = new MyList<>();
//        for (int i = 0; i < countOfEntities; i++) {
//            current.users = users;
//        }
//        return current;
//    }
@Override
public String toString() {
    if (users == null)
        return "null";

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < countOfEntities; i++) {
        if (i == countOfEntities - 1) builder.append(users[i]);
        else builder.append(users[i]).append(", ");
    }
    return "[" + builder + "]";
}
}
