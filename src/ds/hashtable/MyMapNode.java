package ds.hashtable;

class MyMapNode<K, V> {

    K key;
    V value;
    final int hashCode;
    MyMapNode next;

    MyMapNode(K key, V value, final int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
