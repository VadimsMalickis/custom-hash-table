package rvt;


public class SimpleHashTable {

    private final int size;
    private int elementCount;
    private String[] data;
    private int probCount;

    public SimpleHashTable(int size) {
        this.size = size;
        this.elementCount = 0;
        data = new String[size];
    }

    public int size() {
        return this.size;
    }
    public int getProbCount() {
        return this.probCount;
    }

    public void insert(int key, String value) {
        if (this.elementCount == this.size) {
            throw new IllegalStateException("Hash table is full (max "  + this.size + " elements)");
        }
        int probingAttempt = 0;
        int hashCode = (hashFunction(key) + probingAttempt) % this.size;
        boolean isSpotFree = data[hashCode] == null;
        if (isSpotFree) {
            data[hashCode] = value;
            elementCount++;
        } else {
            while (!isSpotFree) {
                probCount++;
                probingAttempt++;
                hashCode = (hashFunction(key) + probingAttempt) % this.size;
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
