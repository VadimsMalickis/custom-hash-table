package rvt;

public class App {
    public static void main(String[] args) {
        SimpleHashTable table = new SimpleHashTable(1000);

        for (int i = 1; i <= table.size(); i++) {
            table.insert(i, "Student Nr. " + i);
        }
        System.out.println(table.getProbCount());
    }   
    public static int hash(int k) {
        return k % (7 + 3);
    }
}
