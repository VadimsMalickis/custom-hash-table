package rvt;

public class SimpleHashTable {

    private int capacity;
    private String[] data;
    public int loopCount; // for testing

    public SimpleHashTable(int capacity) {
        this.capacity = capacity;
        data = new String[capacity];
    }
    // h(k) + i % S
    public void insert(int key, String value) {
        int probingAttempt = 0;
        int index = (hashFunction(key) + probingAttempt) % this.capacity;
        boolean isSpotFree = data[index] == null;
        if (isSpotFree) {
            data[index] = value;
        } else {
            while (!isSpotFree) {
                loopCount++; // for testing
                probingAttempt++;
                index =(hashFunction(key) + probingAttempt) % this.capacity;
                isSpotFree = data[index] == null;
                if (isSpotFree) {
                    data[index] = value;
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
