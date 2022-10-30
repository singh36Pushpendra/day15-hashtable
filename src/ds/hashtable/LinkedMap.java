package ds.hashtable;

import java.util.ArrayList;
import java.util.Objects;

public class LinkedMap<K, V> {

    private ArrayList<MyMapNode<K, V>> buckets;
    private int noOfBuckets;

    LinkedMap() {
        buckets = new ArrayList<>();
        noOfBuckets = 10;

        for (int i = 0; i < noOfBuckets; i++) {
            buckets.add(null);
        }
    }

    final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    int getBucketIndex(K key) {
        int index = hashCode(key) % noOfBuckets;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    public void add(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MyMapNode<K, V> head = buckets.get(bucketIndex);

        int hashCode = hashCode(key);
        MyMapNode<K, V> newNode = new MyMapNode(key, value, hashCode);

        if (head == null) {
            head = newNode;
            buckets.set(bucketIndex, head);
        } else {
            while (head.next != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            if (head.key.equals(key)) {
                head.value = value;
                return;
            }

            head.next = newNode;
        }
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);

        MyMapNode<K, V> head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < noOfBuckets; i++) {

            MyMapNode<K, V> head = buckets.get(i);
            while (head != null) {
                System.out.println("'" + head.key + "' present " + head.value + " time!");
                head = head.next;
            }
        }
    }

    public static void main(String[] args) {
        String paragraph = "Paranoids are not"
                + " paranoid because they are paranoid but"
                + " because they keep putting themselves"
                + " deliberately into paranoid avoidable"
                + " situations";
        
        String[] words = paragraph.split(" ");

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

        System.out.println("In sentence \"" + paragraph + "\":");
        wordCount.display();
    }

}
