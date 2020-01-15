# [LRU Cache](https://leetcode.com/problems/lru-cache/)

## Question 

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

```text
LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

## What I though & Solution

According to the question, we need to have a hash-map to keep track of the keys and its values. However, at the same time, we need to cache these data.
In that case, managing a hash-map by using a double linked list is one of the good strategies since we can easily change the order to represent caching system and have many data such as keys and values.

![](https://leetcode.com/problems/lru-cache/Figures/146/structure.png)

The photo above shows how we should hold data to implement LRU cache. By using this structure, we can realize O(1) time for put and get operations.
It also allows to remove the first added node in O(1) time as well.

One advantage of double linked list is that the node can remove itself without other reference. In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list implemented here is that there are pseudo head and pseudo tail to mark the boundary, so that we don't need to check the null node during the update.

![](https://leetcode.com/problems/lru-cache/Figures/146/new_node.png)


```java
class LRUCache {
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

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

## Time complexity

O(1) for both put and get.

## Space complexity

O(capacity) since the space is used only for a hashmap and double linked list which at most capacity + 1 elements.