// Time Complexity : O(N) where N = nk, n is the average length of all lists
// Space Complexity : O(1)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No

// Your code here along with comments explaining your approach -
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        ListNode cur = dummy;

        ListNode l1 = lists[0];
        ListNode l2;

        for (int i = 1; i < lists.length; i++) {
            l2 = lists[i];

            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    cur.next = l1;
                    cur = cur.next;
                    l1 = l1.next;
                } else if (l2.val < l1.val) {
                    cur.next = l2;
                    cur = cur.next;
                    l2 = l2.next;
                }
            }

            if (l1 != null) {
                cur.next = l1;
            }
            if (l2 != null) {
                cur.next = l2;
            }
            cur = dummy;
            l1 = cur.next;
        }
        return cur.next;
    }
}

// Second solution with greater time complexity, involving adding all elements
// of each
// list in min heap of size k.

// Time Complexity : O(Nlogk) where N = nk, n is the average length of all lists
// Space Complexity : O(k)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No

// Your code here along with comments explaining your approach

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode list : lists) {
            if (list != null)
                pq.add(list);
        }

        while (pq.size() > 0) {
            ListNode list = pq.poll();
            cur.next = list;
            cur = cur.next;
            list = list.next;
            if (list != null)
                pq.add(list);

        }

        return dummy.next;
    }
}