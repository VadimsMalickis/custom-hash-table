package rvt;


public class SimpleHashTable {

    private final int capacity;
    private int elementCount;
    private String[] data;
    private int probCount;

    public SimpleHashTable(int c) {
        this.capacity = c;
        this.elementCount = 0;
        data = new String[c];
    }

    public int size() {
        return this.capacity;
    }
    public int getProbCount() {
        return this.probCount;
    }

    public void insert(int key, String value) {
        if (this.elementCount == this.capacity) {
            throw new IllegalStateException("Hash table is full (max "  + this.capacity + " elements)");
        }
        int probingAttempt = 0;
        int hashCode = (hashFunction(key) + probingAttempt) % this.capacity;
        boolean isSpotFree = data[hashCode] == null;
        if (isSpotFree) {
            data[hashCode] = value;
            elementCount++;
        } else {
            while (!isSpotFree) {
                this.probCount++;
                probingAttempt++;
                hashCode = (hashFunction(key) + probingAttempt) % this.capacity;
                isSpotFree = data[hashCode] == null;
                if (isSpotFree) {
                    data[hashCode] = value;
                    elementCount++;
                    break;
                }
            }
        }

    }

    public void remove(int key) {
        data[hashFunction(key)] = null;
    }

    public String find(int key) {
        return data[hashFunction(key)];
    }

    private int hashFunction(int key) {
        return key % (7 + 3);
    }

}
