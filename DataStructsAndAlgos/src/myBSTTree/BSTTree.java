package myBSTTree;

import java.util.Stack;

public class BSTTree{

	private Node root;

	public Node insert(Node keyNode){

		if(keyNode==null){
			return this.root;
		}
		
		if(this.root==null){
			this.root=keyNode;
			return this.root;
		}
		
		Node parent=null;
		Node node=this.root;
		while(node!=null){
			parent=node;
			if(keyNode.compareTo(node)>0){				
				node=node.getRightChild();				
			}
			else{
				node=node.getLeftChild();
			}
		}

		if(parent!=null){
			if(keyNode.compareTo(parent)>0){	
				parent.setRightChild(keyNode);						
			}
			else{
				parent.setLeftChild(keyNode);
			}
		}
		
		return this.root;

	}
	
	public int getNearestLeafDistance(){
		
		Stack<Node> stack=new Stack<Node>();
		int minPathDistance=Integer.MAX_VALUE;
		if(this.root==null){
			return 0;
		}
		Node node=this.root;
		
		while(node!=null){	
			System.out.println("Node "+node+" "+node.getHeight());
			stack.push(node);
			int height=node.getHeight()+1;
			node=node.getLeftChild();
			if(node!=null){
				node.setHeight(height);
			}
						
		}
		
		while(!stack.isEmpty()){
			
			System.out.println(stack);
			node=stack.pop();
			
			if(node.getLeftChild()==null && node.getRightChild()==null){
				System.out.println("node "+node.getHeight());
				
				if(node.getHeight()<minPathDistance){					
					minPathDistance=node.getHeight();
				}
			}
			
			if(node.getRightChild()!=null){
				int height=node.getHeight()+1;
				node=node.getRightChild();
				node.setHeight(height);
				
				while(node!=null){
					System.out.println("Node "+node+" "+node.getHeight());
					stack.push(node);
					height=node.getHeight()+1;
					node=node.getLeftChild();
					if(node!=null){
						node.setHeight(height);
					}
					
				}
				
			}
		}
		return minPathDistance;
	}
	
	public static void main(String[] args) {
		
		BSTTree bstTree=new BSTTree();
		
		bstTree.insert(new Node(10));
		bstTree.insert(new Node(15));
		bstTree.insert(new Node(20));
		bstTree.insert(new Node(1));
		bstTree.insert(new Node(5));
		
		
		bstTree.inOrderTraversal(bstTree.root);
		
		int nearestLeafDistance=bstTree.getNearestLeafDistance();
		System.out.println("nearestLeafDistance "+nearestLeafDistance);
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
