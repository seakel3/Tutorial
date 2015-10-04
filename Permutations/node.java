package binaryTree;

public class Node<T> {
	T data;
	Node<T> left;
	Node<T> right;
	Node<T> next;
	
	public Node() {
		data = null;
		left = null;
		right = null;
		next = null;
	}
	
	public Node(T data, Node<T> left, Node<T> right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public Node(T data, Node<T> next){
		this.data = data;
		this.next = next;
	}
	public Node(T data){
		this.data = data;
	}
}
