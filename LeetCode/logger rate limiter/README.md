# [Logger Rate Limiter](https://leetcode.com/problems/logger-rate-limiter/)

## Question 

Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

```java
Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
```

## Solution 

This question was kinda system design question, but it was so easy.

The idea is that we keep a hashtable/dictionary with the message as key, and its timestamp as the value. The hashtable keeps all the unique messages along with the latest timestamp that the message was printed.

```java
public class Logger {

    HashMap<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
        this.map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message)) {
            if ((timestamp - map.get(message)) >= 10) {
                map.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        } else {
            map.put(message, timestamp);
            return true;
        }
    }
}
```

### Time complexity 

O(1) since the lookup and update of the hashtable takes a constant time.

### Space complexity

O(M). M is the size of all incoming message.