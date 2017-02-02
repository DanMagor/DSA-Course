/**
 * Created by Anton Skudarnov on 01.02.2017.
 */
public class Main {
    public static void main(String[] args) {

        LinkedQueue queue;
        LinkedStack stack;
        queue = new LinkedQueue();
        stack = new LinkedStack();
        for (int i = 1;i<11;i++){
            queue.enqueue(i);
            stack.push(i);
        }
        queue.display();
        stack.display();
        queue.dequeue();
        stack.pop();
        queue.display();
        stack.display();
        System.out.println(queue.peek());
        System.out.println(stack.peek());

    }

}
