package rvt;

public class SimpleHashTable {
    public static void main(String[] args) {
        SimpleHashTable table = new SimpleHashTable(1000);
        for (int i = 1; i <= table.getCapacity(); i++) {
            table.insert(i, "Student Nr. " + i);
        }
        System.out.println(table.find(9));
    }
    public class Element {
        public int key;
        public String value;
        public Element(int k, String v) {
            this.key = k;
            this.value = v;
        }
    }

    private final int capacity;
    private int elementCount;
    private Element[] data;

    // For hash table filling testing purposes
    private int probCount;

    public SimpleHashTable(int c) {
        this.capacity = c;
        this.elementCount = 0;
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
        boolean isSpotFree = data[hashCode] == null;
        if (isSpotFree) {
            data[hashCode] = new Element(hashCode, value);
            elementCount++;
        } else {
            int probingAttempt = 0;
            while (!isSpotFree) {
                this.probCount++; // testing
                probingAttempt++;
                hashCode = hashFunction(key) + probingAttempt;
                isSpotFree = data[hashCode] == null;
                if (isSpotFree) {
                    data[hashCode] = new Element(hashCode, value);
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
        boolean isElementFound = isElementFound(hashCode, key);
        if (isElementFound) {
            data[hashCode] = null;
            elementCount--;
        } else {
            int probingAttempt = 0;
            while (!isElementFound) {
                this.probCount++;
                probingAttempt++;
                hashCode = hashFunction(key) + probingAttempt;
                isElementFound = isElementFound(hashCode, key);
                if (isElementFound) {
                    data[hashCode] = null;
                    elementCount--;
                    break;
                }
            }
        }
    }

    public String find(int key) {
        if (this.elementCount == 0) {
            throw new IllegalStateException("Hash table is empty.");
        }
        int hashCode = hashFunction(key);
        boolean isElementFound = isElementFound(hashCode, key);
        if (isElementFound) {
            return data[hashCode].value;
        } else {
            int probingAttempt = 0;
            while (!isElementFound) {
                this.probCount++;
                probingAttempt++;
                hashCode = hashFunction(key) + probingAttempt;
                isElementFound = isElementFound(hashCode, key);
                if (isElementFound) {
                    return data[hashCode].value;
                }
            }
        }
        return null;
    }

    private int hashFunction(int key) {
        return key % (7 + 3);
    }

    private boolean isElementFound(int hashCode, int key) {
        return data[hashCode] != null && hashCode == key;
    }
}
