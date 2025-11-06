package rvt;

public class App {
    public static void main(String[] args) {
        SimpleHashTable table = new SimpleHashTable(1000);
        for (int i = 1; i <= table.getCapacity(); i++) {
            table.insert(i, "Student Nr. " + i);
        }
        table.remove(589);
        System.out.println(
            table.find(589)
        );
    }   

    public static int hash(int k) {
        return k % (7 + 3);
    }
}
