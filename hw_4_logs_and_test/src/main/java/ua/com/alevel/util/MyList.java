package ua.com.alevel.util;

public class MyList<ENTITY> {

    ENTITY[] entities;
    int maxCapacity;
    int countOfEntities;
    static final int DEFAULT_CAPACITY = 100;

    public MyList() {
        this(DEFAULT_CAPACITY);
    }

    public MyList(int capacity) {
        this.maxCapacity = capacity;
        entities = (ENTITY[]) new Object[capacity];
        countOfEntities = 0;
    }

    public int getCountOfEntities() {
        return countOfEntities;
    }

    public ENTITY getEntity(int index) {

        checkIndex(index);
        return entities[index];
    }

    public void add(int index, ENTITY element) {
        ENTITY temp;
        checkIndex(index);
        if (countOfEntities == entities.length)
            resize();
        if (index < countOfEntities) {
            for (int i = countOfEntities - 1; i >= index; i--) {
                temp = entities[i];
                entities[i + 1] = temp;
            }
        }
        entities[index] = element;
        countOfEntities++;
    }

    public void add(ENTITY element) {
        add(countOfEntities, element);
    }

    private void resize() {
        ENTITY[] resize = (ENTITY[]) new Object[entities.length * 2];
        if (maxCapacity >= 0) System.arraycopy(entities, 0, resize, 0, maxCapacity);
        this.entities = resize;
        this.maxCapacity = entities.length;
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
        entities[indexOf(a)] = a;
    }

    public ENTITY[] getEntities() {
        return entities;
    }

    public void deleteLastElement() {
        if (countOfEntities == 0) {
            throw new RuntimeException("list is empty: cannot delete");
        }
        countOfEntities--;
        entities[countOfEntities] = null;
    }

    public void deleteFirstElement() {
        for (int i = 0; i < countOfEntities - 1; i++) {
            entities[i] = entities[i + 1];
        }
        entities[countOfEntities - 1] = null;
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
            entities[index] = null;
            while (i < getCountOfEntities() - 1) {
                entities[i] = entities[i + 1];
                entities[i + 1] = null;
                i++;
            }
        }
        countOfEntities--;
    }

    public int indexOf(ENTITY o) {
        return indexOfRange(o, 0, countOfEntities);
    }

    int indexOfRange(ENTITY o, int start, int end) {
        Object[] es = entities;
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
        if (entities == null)
            return "null";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < countOfEntities; i++) {
            if (i == countOfEntities - 1) builder.append(entities[i]);
            else builder.append(entities[i]).append(", ");
        }
        return "[" + builder + "]";
    }
}
