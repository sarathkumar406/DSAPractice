package linkedListExamples;

public class ReverseList {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ReverseList reverseList = new ReverseList();
        ListNode node = reverseList.reveseList(head);

        while(node!=null){
            System.out.print(node.val+ " ");
            node = node.next;
        }
    }

    private ListNode reveseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while(curr!=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
