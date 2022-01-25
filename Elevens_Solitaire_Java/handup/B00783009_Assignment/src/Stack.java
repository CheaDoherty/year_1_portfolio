import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T>{

    private MyNode<T> topNode;

    public Stack(){
        topNode = null;
    }

    public void push(T newEntry) {
        MyNode<T> newNode = new MyNode<>(newEntry);
        newNode.setNext(topNode);
        topNode = newNode;
    }


    public T pop() {
        T dataToReturn = peek();
        topNode = topNode.getNext();
        return dataToReturn;
    }


    public T peek() {
        if (topNode == null) throw new EmptyStackException();
        else return topNode.getData();
    }


    public boolean isEmpty() {
        return (topNode == null);
    }


    public void clear() {
        topNode = null;
    }

    public static void main(String[] args){
        Stack<Integer> stackTest = new Stack<>();

        // Adding Entries
        stackTest.push(1);
        stackTest.push(2);
        stackTest.push(3);

        try {
            for (int i = 0; i < 4; i++){
                System.out.println("Peek: " + stackTest.peek());
                System.out.println("Pop: " + stackTest.pop());
                System.out.println("Pop: " + stackTest.pop());
                System.out.println("Pop: " + stackTest.pop());
                System.out.println("Pop: " + stackTest.pop());

            }
        }catch (EmptyStackException e){
            System.out.println("Stack is empty");
        }

        stackTest.push(4);
        stackTest.push(5);
        stackTest.push(6);

        System.out.println("Empty: " + stackTest.isEmpty());
        System.out.println("Clearing stack...");
        stackTest.clear();
        System.out.println("Empty: " + stackTest.isEmpty());

    }
}
