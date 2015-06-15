
// Definition for singly-linked list.
// class ListNode {
//      int val;
//      ListNode next;
//      ListNode(int x) { val = x; }
// }

public class Solution {
    class ListPair {
        ListNode fst;
        ListNode snd;
        ListPair(ListNode f, ListNode s) { fst = f; snd = s; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k < 2) {
            return head;
        }
        int len = listLen(head);
        if(len < k) {
            return head;
        }
        int revCounts = len / k;
        ListNode headOfRev = head, lastOfRev = null, headOfRest = head;
        for(int i = 0; i < revCounts; i++) {
            ListPair p = revFstK(headOfRest, k);
            if(i == 0) {
                headOfRev = p.fst;
            } else {
                lastOfRev.next = p.fst;
            }
            lastOfRev = headOfRest;
            headOfRest = p.snd;
        }
        if(headOfRest != null) {
            lastOfRev.next = headOfRest;
        }
        return headOfRev;
    }

    private int listLen(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while(cur != null) {
            len += 1;
            cur = cur.next;
        }
        return len;
    }

    // waits k > 1, head != null and list length >= k
    private ListPair revFstK(ListNode head, int k) {
        ListNode fst = null, snd = head, thd;
        for(int i = 0; i < k; i++) {
            thd = snd.next;
            snd.next = fst;
            fst = snd;
            snd = thd;
        }
        return new ListPair(fst, snd);
    }
}