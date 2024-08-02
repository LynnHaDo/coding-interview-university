package leetcode;
import java.util.Stack;

class Node {
    int value;
    int min;

    public Node(int value, int min) {
        this.min = min;
        this.value = value;
    }

    public boolean isMin(){
        return this.min == this.value;
    }
}

public class MinStack {
    Stack<Node> s;
    Integer min;

    public MinStack() {
        this.s = new Stack<>();
    }
    
    public void push(int val) {
        if (min == null || val < min.intValue()) {
            min = val;
        }
        this.s.push(new Node(val, min));
    }
    
    public void pop() {
        Node toDelete = this.s.pop();

        if (toDelete.isMin() && !this.s.empty()) {
            min = (Integer) this.s.peek().min;
        } 
    }
    
    public int top() {
        return this.s.peek().value;
    }
    
    public int getMin() {
        return this.min.intValue();
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
