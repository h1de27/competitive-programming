# [Find k Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/)

# Question

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

```text
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```

Example 2:

```text
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
```

Example 3:

```text
Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
```

## Solution 

To be honest, I couldn't solve this question myself. I could come up the brief process but I was not able to turn it to the code.

According to the solution made by someone, we can solve it with the run time O(K logK).

```java
public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> res = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0 || k==0) return res;
        for(int i=0; i<nums1.length && i<k; i++) que.offer(new int[]{nums1[i], nums2[0], 0});
        while(k-- > 0 && !que.isEmpty()){
            int[] cur = que.poll();
            res.add(new int[]{cur[0], cur[1]});
            if(cur[2] == nums2.length-1) continue;
            que.offer(new int[]{cur[0],nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}
```

He put the diagram, too.

![](https://cloud.githubusercontent.com/assets/8743900/17332795/0bb46cfe-589e-11e6-90b5-5d3c9696c4f0.png)

Reference: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation

Umm... Idk what's going on lol

Okay, let's generalize the question first. So what we need to do is to pick the combination of nums1[i] & nums2[j] (i and j are indices.).
Obviously, nuns1[0] & nums2[0] comes first since the arrays are sorted. Then, we will pick nums1[i + 1] & nums2[j] or nums1[i] & nums2[j + 1].

Now, we know what we have to do next. We will use min-heap to get a min candidate (it takes O(logN) to add and remove.) and then add nums1[i + 1] & nums2[j] or nums1[i] & nums2[j+1] until we get K number of elements.

Wow that's awesome. This concept comes from [Shiroyama-san's blog](http://fushiroyama.hatenablog.com/entry/2020/01/02/081322) and [@kenkoooo](https://twitter.com/kenkoooo) -san's [gist](https://gist.github.com/kenkoooo/51ff3808af7b6db668bc63ecea401f8b).

I learned that this is exactly the way to interpreter LeetCode question as a mathematical problem.

```java
public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums1.length == 0 || nums2.length == 0 || k == 0) {
        return res;
    }
    PriorityQueue<List<Integer>> pq =
            new PriorityQueue<>((o1, o2) -> 
                    nums1[o1.get(0)] + nums2[o1.get(1)] - nums1[o2.get(0)] - nums2[o2.get(1)]);
    TreeSet<List<Integer>> added = new TreeSet<>((o1, o2) -> {
        if (o1.get(0) != o2.get(0)) {
            return o1.get(0) - o2.get(0);
        }
        return o1.get(1) - o2.get(1);
    });

    pq.add(new ArrayList<>(Arrays.asList(0, 0)));
    added.add(new ArrayList<>(Arrays.asList(0, 0)));

    while (res.size() < k && !pq.isEmpty()) {
        List<Integer> ans = pq.poll();
        int i = ans.get(0);
        int j = ans.get(1);
        res.add(Arrays.asList(nums1[i], nums2[j]));
        if (i + 1 < nums1.length && !added.contains(new ArrayList<>(Arrays.asList(i + 1, j)))) {
            pq.add(new ArrayList<>(Arrays.asList(i + 1, j)));
            added.add(new ArrayList<>(Arrays.asList(i + 1, j)));
        }
        if (j + 1 < nums2.length && !added.contains(new ArrayList<>(Arrays.asList(i, j + 1)))) {
            pq.add(new ArrayList<>(Arrays.asList(i, j + 1)));
            added.add(new ArrayList<>(Arrays.asList(i, j + 1)));
        }
    }
    return res;
}
```

### Time complexity

O(K log(K)).

### Space complexity 

O(K). 
