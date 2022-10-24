package ds.hashtable;

public class LinkedMap<K, V> {

    MyMapNode head;

    class MyMapNode {

        K key;
        V value;
        MyMapNode next;

        MyMapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void add(K key, V value) {

        MyMapNode newNode = new MyMapNode(key, value);
        if (head == null) {
            head = newNode;
        } else {
            MyMapNode temp = head;
            while (temp.next != null) {
                if (temp.key.equals(key)) {
                    temp.value = value;
                    return;
                }
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public V get(K key) {
        MyMapNode temp = head;
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    public void display() {
        MyMapNode temp = head;
        while (temp != null) {
            System.out.println("'" + temp.key + "' present " + temp.value + " time!");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        String sentence = "To be or not to be";
        String[] words = sentence.split(" ");

        LinkedMap<String, Integer> wordCount = new LinkedMap<>();

        for (String word : words) {
            int countValue;
            try {
                countValue = wordCount.get(word);
            } catch (NullPointerException npe) {
                countValue = 0;
            }
            countValue++;
            wordCount.add(word, countValue);
        }

        wordCount.display();
    }

}
