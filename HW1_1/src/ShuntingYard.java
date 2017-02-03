import java.awt.geom.Arc2D;
import java.io.*;

/**
 * Created by Anton Skudarnov on 02.02.2017.
 */
public class ShuntingYard {

    private static Reader reader;
    private static  LinkedStack stack;
    private static LinkedQueue queue;
    private static void putOperator(char op){
        switch (op){
            case '*': stack.push(op); break;
            case '/': stack.push(op); break;
            case '(': stack.push(op); break;
            case ')': putRightParenthesis(); break;
            default: putLeftOperator(op);break;
        }
    }

    private static void putLeftOperator(char op) {
        if (stack.header != null) {
            while (stack.header!=null &&(stack.peek().equals('*') || stack.peek().equals('/'))) {
                queue.enqueue(stack.peek());
                stack.pop();
            }
        }
            stack.push(op);

    }
    private static void putRightParenthesis(){
      while(!stack.peek().equals('(')){
          queue.enqueue(stack.peek());
          stack.pop();
      }
      stack.pop();
    }


    ShuntingYard(Reader r){
        reader = r;
        stack = new LinkedStack();
        queue = new LinkedQueue();
    }

    public static void parseExpression() throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        int token;
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        token = tokenizer.nextToken();
        while (tokenizer.ttype!=StreamTokenizer.TT_EOF &&
                tokenizer.ttype!=StreamTokenizer.TT_EOL) {
            if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                queue.enqueue(tokenizer.nval);
            }
            else {
                putOperator((char)token);
            }
            token = tokenizer.nextToken();
        }
        while (stack.header!=null){
            queue.enqueue(stack.peek());
            stack.pop();
        }

    }
    public static void printExpression(){
        queue.display();
    }

    public static void calculateExpression() throws IOException {
        double a,b,c;
        while (queue.header!=null){
             if (queue.peek() instanceof Double){
                stack.push(queue.peek());
                queue.dequeue();
             }
             else
             {

                 a = (double) stack.peek();
                 stack.pop();
                 b = (double) stack.peek();
                 stack.pop();
                 switch ((char)queue.peek()){
                     case '+': c = a + b; break;
                     case '-': c = b - a; break;
                     case '*': c = a * b; break;
                     case '/': c = b / a; break;
                     default: c = a + b; break;
                 }
                 stack.push(c);
                 queue.dequeue();
             }
        }
        FileWriter writer = new FileWriter("output.txt");
        Double d;
        String output;

        output = String.format("%.2f",stack.peek());


        output = output.replace(',','.');

        writer.write(output);
        writer.close();



    }
}
