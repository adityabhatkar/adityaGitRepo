package myBSTTree;

public class Node implements Comparable<Node>{

	private int key;
	private Node rightChild;
	private Node leftChild;
	private Node parent;
	private int height;
	
	public Node(int key, Node rightChild, Node leftChild, Node parent, int height) {
		super();
		this.key = key;
		this.rightChild = rightChild;
		this.leftChild = leftChild;
		this.parent = parent;
		this.height=height;
	}

	public Node(int key){
		super();
		this.key = key;
		
	}
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (key != other.key)
			return false;
		return true;
	}

	@Override
	public int compareTo(Node o) {
		return this.key-o.getKey();
	}

	@Override
	public String toString() {
		return "Key:"+this.key;
	}

}
