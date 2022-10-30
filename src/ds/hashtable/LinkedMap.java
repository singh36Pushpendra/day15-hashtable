package ds.hashtable;

import java.util.ArrayList;
import java.util.Objects;

public class LinkedMap<K, V> {

    private final ArrayList<MyMapNode<K, V>> buckets;
    private final int noOfBuckets;

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

    // Method to remove a given key
    public void remove(K key) {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of chain
        MyMapNode<K, V> head = buckets.get(bucketIndex);

        // If key was not there
        if (head == null)
            return;
        else if (head.next == null && head.key.equals(key) && hashCode == head.hashCode) {
            head = null;
            buckets.set(bucketIndex, head); // ******** Added Newly
            return;
        }

        MyMapNode<K, V> temp = head;
        MyMapNode<K, V> left = temp;
        MyMapNode<K, V> right = left.next;

        // Delete First
        if (temp.key.equals(key) && hashCode == temp.hashCode) {
            temp = temp.next;
        }

        while (right != null) {

            if (right.key.equals(key) && hashCode == right.hashCode) {
                left.next = right.next;
                break;
            }
            left = left.next;
            right = right.next;
        }

        buckets.set(bucketIndex, temp);

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

}
