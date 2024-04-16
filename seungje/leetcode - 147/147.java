class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode i, headPointer = new ListNode(0, head);

        while(head.next != null){
            if(head.val <= head.next.val){ head = head.next; continue; }

            for(i = headPointer; i.next.val <= head.next.val; i = i.next);
            i.next = new ListNode(head.next.val, i.next);

						head.next = head.next.next;
        }

        return headPointer.next;
    }
}