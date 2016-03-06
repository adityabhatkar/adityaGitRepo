package myAVLTree;

import search.Searcher;


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

			//set parent
			treeNode.getLeftChild().setParent(treeNode);
		}
		else{
			//newNode>treeNode
			treeNode.setRightChild(insert(newNode, treeNode.getRightChild()));
			//set parent
			treeNode.getRightChild().setParent(treeNode);
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
		
		//updating parents
		x.setParent(treeNode.getParent());
		treeNode.setParent(x);
		if(subTree!=null){
			subTree.setParent(treeNode);
		}
		
		
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
		
		//updating parents
		x.setParent(treeNode.getParent());
		treeNode.setParent(x);
		if(subTree!=null){
			subTree.setParent(treeNode);
		}
		
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
	
	public Node findTreeMinimum(Node node){
		
		while(node.getLeftChild()!=null){
			node=node.getLeftChild();
		}
		return node;
		
	}
	
	public Node findTreeMaximum(Node node){
		
		while(node.getRightChild()!=null){
			node=node.getRightChild();
		}
		return node;
		
	}
	
	public Node getSuccessor(Node node){
		
		if(node.getRightChild()!=null){
			return findTreeMinimum(node.getRightChild());
		}
		
		Node parent=node.getParent();
		while(parent!=null && node.equals(parent.getRightChild())){
			node=parent;
			parent=parent.getParent();
		}
		
		return parent;
		
	}
	
	public Node getPredecessor(Node node){
		
		if(node.getLeftChild()!=null){
			return findTreeMaximum(node.getLeftChild());
		}
		
		Node parent=node.getParent();
		while(parent!=null && node.equals(parent.getLeftChild())){
			node=parent;
			parent=parent.getParent();
		}
		return parent;
	}

	public static void main(String[] args){

		//AVLTree avlTree=new AVLTree(new Node(10, null, null, null,0));
		AVLTree avlTree=new AVLTree();
	
		Node newRoot=null;
		int[] keyArr={10,20,15,7,6,2,4,1};
		for(int i=0; i<keyArr.length; i++){
			System.out.println("insert "+keyArr[i]);
			newRoot=avlTree.insert(new Node(keyArr[i], null, null, null,0));
			avlTree.setRoot(newRoot);
			avlTree.inOrderTraversal(avlTree.getRoot());
		}
		
		//insert operation in theta(Lg(n)) time
		//sorting n elements in theta(nLg(n)) time
		
		//find minimum
		Node minNode=avlTree.findTreeMinimum(avlTree.getRoot());		
		System.out.println("Printing Min "+minNode);
		
		//find maximum
		Node maxNode=avlTree.findTreeMaximum(avlTree.getRoot());
		System.out.println("Printing Max "+maxNode);
		
		//search node with key 
		Node keyNode=Searcher.bstSearcher(avlTree.getRoot(), new Node(10));
		System.out.println("found "+keyNode);
		//get Successor
		Node successor=avlTree.getSuccessor(keyNode);
		System.out.println("Get Successor of "+keyNode+" "+successor);
		
		Node predecessor=avlTree.getPredecessor(keyNode);
		System.out.println("Get predecessor of "+keyNode+" "+predecessor);
		
		
		/*
		 * to find second smallest/largest element
		 * pass min/max node as arg to get successor/predecessor
		 * 
		 * */
		
		successor=avlTree.getSuccessor(minNode);
		System.out.println("second smallest node "+successor);
		
		predecessor=avlTree.getPredecessor(maxNode);
		System.out.println("second largest node "+predecessor);
		
		
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
