import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class ListNode {
        int key;
        int value;
        ListNode prev;
        ListNode next;
    }

    private void addNode(ListNode node) {
        node.prev = first;
        node.next = first.next;

        first.next.prev = node;
        first.next = node;
    }

    private void removeNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(ListNode node) {
        removeNode(node);
        addNode(node);
    }

    private ListNode popLast() {
        ListNode res = last.prev;
        removeNode(res);
        return res;
    }

    private Map<Integer, ListNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private ListNode first, last;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        first = new ListNode();
        last = new ListNode();

        first.next = last;
        last.prev = first;
    }

    public int get(int key) {
        ListNode node = cache.get(key);
        if (node == null) {
            return -1;
        }

        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        ListNode node = cache.get(key);

        if (node == null) {
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            size++;

            if (size > capacity) {
                ListNode last = popLast();
                cache.remove(last.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
