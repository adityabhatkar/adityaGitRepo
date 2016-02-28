package myAVLTree;

public class AVLTree {

	private Node root;
	
	public AVLTree(){
		
	}
	public AVLTree(Node root) {
		this.root=root;
	}
	public Node getRoot(){
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public Node insert(Node newNode){
		return insert(newNode, getRoot());
	}

	private Node insert(Node newNode, Node treeNode){

		if(treeNode==null){
			return new Node(newNode.getKey(), null, null, null,0);
		}

		if(newNode.compareTo(treeNode)<=0){			
			//newNode<=treeNode
			treeNode.setLeftChild(insert(newNode, treeNode.getLeftChild()));			
		}
		else{
			//newNode>treeNode
			treeNode.setRightChild(insert(newNode, treeNode.getRightChild()));
		}
		
		//update height
		treeNode.setHeight(getNodeHeight(treeNode));
		
		//check balance
		int balance=getBalance(treeNode);
		
		//System.out.println(treeNode.toString()+" balance:"+balance);
		//if balance = +-1 then fine 
		
		//if balance >1 leftHeavy hence right rotation
		if(balance>1){
			//if new Node inserted to left of left child of treeNode
			//newNode<=leftChild of treeNode
			if(newNode.compareTo(treeNode.getLeftChild())<=0){
				//right right on treeNode
				return rightRotation(treeNode);
			}
			else{
				//if new Node inserted to right of left child of treeNode
				//newNode>leftChild of treeNode
				
				//left on left child of tree node and right on tree node
				treeNode.setLeftChild(leftRotation(treeNode.getLeftChild()));
				return rightRotation(treeNode);				
				
			}
		}
		
		//if balance <-1 rightHeavy hence left rotation
		if(balance<-1){
			
			//if new node inserted to right of right child of tree node
			//newNode>right child of tree node
			if(newNode.compareTo(treeNode.getRightChild())>0){
				//left left on tree node
				return leftRotation(treeNode);
			}
			else{
				//if new node inserted to left of right child of tree node
				//new node <=right child of tree node
				
				//right rotation on right child of tree node
				treeNode.setRightChild(rightRotation(treeNode.getRightChild()));
				//left rotation on tree node
				return leftRotation(treeNode);
			}
		}
		
		//if no change
		return treeNode;

	}
	
	private Node leftRotation(Node treeNode) {
		
		Node x= treeNode.getRightChild();
		Node subTree=x.getLeftChild();
		
		x.setLeftChild(treeNode);
		treeNode.setRightChild(subTree);
		
		return x;
	}
	private Node rightRotation(Node treeNode) {
		
		Node x=treeNode.getLeftChild();
		Node subTree=x.getRightChild();
		
		x.setRightChild(treeNode);
		treeNode.setLeftChild(subTree);
		
		//update heights
		treeNode.setHeight(getNodeHeight(treeNode));
		x.setHeight(getNodeHeight(x));
		
		return x;
			
	}
	public int getBalance(Node treeNode) {

		int leftChildHeight=-1;
		int rightChildHeight=-1;
		
		if(treeNode.getLeftChild()!=null){
			leftChildHeight=treeNode.getLeftChild().getHeight();
		}
		
		if(treeNode.getRightChild()!=null){
			rightChildHeight=treeNode.getRightChild().getHeight();
		}
		
		return leftChildHeight-rightChildHeight;
	}
	
	public int getMax(int a, int b){
		if(a>=b){
			return a;
		}
		return b;
	}
	
	public int getNodeHeight(Node treeNode){
		
		int leftChildHeight=-1;
		int rightChildHeight=-1;
		
		if(treeNode.getLeftChild()!=null){
			leftChildHeight=treeNode.getLeftChild().getHeight();
		}
		
		if(treeNode.getRightChild()!=null){
			rightChildHeight=treeNode.getRightChild().getHeight();
		}
		
		return getMax(leftChildHeight, rightChildHeight)+1;
		
	}

	public static void main(String[] args){

		//AVLTree avlTree=new AVLTree(new Node(10, null, null, null,0));
		AVLTree avlTree=new AVLTree();
	
		Node newRoot=null;
		int[] keyArr={10,20,15,7,6,2,4,1,10};
		for(int i=0; i<keyArr.length; i++){
			System.out.println("insert "+keyArr[i]);
			newRoot=avlTree.insert(new Node(keyArr[i], null, null, null,0));
			avlTree.setRoot(newRoot);
			avlTree.inOrderTraversal(avlTree.getRoot());
		}
		
		//insert operation in theta(Lg(n)) time
		//sorting n elements in theta(nLg(n)) time
	}

	public void inOrderTraversal(Node node){
		if(node!=null){
			//left
			inOrderTraversal(node.getLeftChild());
			//root
			System.out.println(node.getKey());
			//right
			inOrderTraversal(node.getRightChild());
		}
	}
}
