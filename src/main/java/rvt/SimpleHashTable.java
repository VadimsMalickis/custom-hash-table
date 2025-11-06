package rvt;

public class SimpleHashTable {
    public static void main(String[] args) {
        SimpleHashTable table = new SimpleHashTable(1000);
        for (int i = 1; i <= table.getCapacity(); i++) {
            table.insert(i, "Student Nr. " + i);
        }
        table.remove(589);
        System.out.println(table.find(589));
    }

    public static class Element {
        public int key;
        public String value;

        public Element(int k, String v) {
            this.key = k;
            this.value = v;
            System.out.println(
                String.format("Added -> key: %d, value: %s", k, v));
        }
    }

    private final int capacity;
    private int elementCount;
    private int lastIndex;
    private Element[] data;

    // For hash table filling testing purposes
    private int probCount;

    public SimpleHashTable(int c) {
        this.capacity = c;
        this.elementCount = 0;
        this.lastIndex = this.capacity - 1;
        data = new Element[c];
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void insert(int key, String value) {
        if (this.elementCount == this.capacity) {
            throw new IllegalStateException("Hash table is full (max " + this.capacity + " elements)");
        }
        int hashCode = hashFunction(key);
        int index = hashCode % lastIndex;
        boolean isSpotFree = data[index] == null;
        if (isSpotFree) {
            data[index] = new Element(key, value);
            elementCount++;
        } else {
            while (index <= lastIndex) {
                this.probCount++; // testing
                index++;
                isSpotFree = data[index] == null;
                if (isSpotFree) {
                    data[index] = new Element(key, value);
                    elementCount++;
                    break;
                }
            }
        }

    }

    public void remove(int key) {
        if (this.elementCount == 0) {
            throw new IllegalStateException("Hash table is empty.");
        }
        int hashCode = hashFunction(key);
        int index = hashCode % lastIndex;
        boolean elementFound = isElementFound(index, key);
        if (elementFound) {
            data[index] = null;
            elementCount--;
        } else {
            while (index <= lastIndex) {
                this.probCount++; // testing
                index++;
                elementFound = isElementFound(index, key);
                if (elementFound) {
                    data[index] = null;
                    elementCount--;
                    break;
                }
            }
        }
    }

    public String find(int key) {
        if (this.elementCount == 0) {
            return null;
        }
        int hashCode = hashFunction(key);
        int index = hashCode % lastIndex;
        boolean elementFound = isElementFound(index, key);
        if (elementFound) {
            return data[index].value;
        } else {
            while (index < lastIndex) {
                index++;
                elementFound = isElementFound(index, key);
                if (elementFound) {
                    return data[index].value;
                }
            }
        }
        return null;
    }

    private int hashFunction(int key) {
        return key % (7 + 3);
    }

    private boolean isElementFound(int index, int key) {
        return data[index] != null
                && data[index].key == key;
    }
}
