import java.util.*;

public class TestQueue {
    public static void main(String[] args) {
        LinkedList<String> stack1 = new LinkedList<>();
        stack1.offerFirst("first");
        stack1.offerFirst("second");
        stack1.offerFirst("third");
        System.out.println(stack1);
//        System.out.println(stack1.peek());
        System.out.println(stack1.pollFirst());
        System.out.println(stack1.pollFirst());

        // can implement stack using offerLast and pollLast at tail
        LinkedList<String> stack2 = new LinkedList<>();
        stack2.offerLast("first");
        stack2.offerLast("second");
        stack2.offerLast("third");
        System.out.println(stack2);
        System.out.println(stack2.pollLast());
        System.out.println(stack2.pollLast());

        // implement stack using push and pop
        LinkedList<String> stack3 = new LinkedList<>();
        stack3.push("first"); // push is wrapper of addFirst
        stack3.push("second");
        stack3.push("third");
        System.out.println(stack3);
        System.out.println(stack3.pop());
        System.out.println(stack3.pop());


        System.out.println("Test Queue");
        Queue<String> queue = new LinkedList<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");
        queue.offer("fourth");
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.contains("honda"));
        System.out.println(queue.size());
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.poll());
        }

        // Stack: Old way (Vector -> Stack)
        System.out.println("Test Stack");
        Stack<String> st = new Stack<>();
        st.push("first");
        st.push("second");
        st.push("third");
        st.push("fourth");
        System.out.println(st.peek());
        for (int i = 0; i < 4; i++) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();

        // Stack
        System.out.println("Stack with deque and arrayqueue");
        Deque<Integer> st2 = new ArrayDeque<>();
        st2.push(1);
        st2.push(2);
        st2.push(3);
        st2.push(4);
        System.out.println(st2.peekFirst());
        for (int i = 0; i < 4; i++) {
            System.out.print(st2.pop() + " ");
        }
        System.out.println();

        // Stack using deque with linkedlist
        System.out.println("Stack with deque and linkedlist");
        Deque<Integer> st3 = new LinkedList<>();
        st3.push(1);
        st3.push(2);
        st3.push(3);
        st3.push(4);
        System.out.println(st3.peekFirst());
        for (int i = 0; i < 4; i++) {
            System.out.print(st3.pop() + " ");
        }
        System.out.println();
    }
}
