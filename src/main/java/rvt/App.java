package rvt;

public class App {
    public static void main(String[] args) {
         final int capacity = 1000;
        SimpleHashTable table = new SimpleHashTable(capacity);

        for (int i = 0; i < capacity; i++) {
            table.insert(i, "HotDog " + i);
        }
        System.out.println(table.loopCount);
    }
}
