package rvt;

public class SimpleHashTable {
 
    public static class Element {
        public int key;
        public String value;
        public Element(int k, String v) {
            this.key = k;
            this.value = v;
            System.out.println(
                String.format("Added -> key: %d, value: %s", k, v)
            );
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
                System.out.println(index);
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
        return data[hashCode] != null
            && data[hashCode].key == key
            && hashCode == key;
    }
}
