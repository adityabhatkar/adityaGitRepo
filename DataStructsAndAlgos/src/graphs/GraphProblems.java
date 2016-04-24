package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class GraphProblems {


	public static void main(String[] args) {

		Map<Element, Set<Element>> unWeightedDirectedGraph=new HashMap<Element, Set<Element>>();

		Element one=new Element(1);
		Element two=new Element(2);
		Element three=new Element(3);
		Element four=new Element(4);
		Element five=new Element(5);
		Element six=new Element(6);
		Element seven=new Element(7);
		Element eight=new Element(8);
		Element ten=new Element(10);
		Element nine=new Element(9);

		insertEdge(unWeightedDirectedGraph, one, two);
		insertEdge(unWeightedDirectedGraph, one, five);
		insertEdge(unWeightedDirectedGraph, two, six);
		insertEdge(unWeightedDirectedGraph, six, three);
		insertEdge(unWeightedDirectedGraph, six, seven);
		insertEdge(unWeightedDirectedGraph, three, seven);
		insertEdge(unWeightedDirectedGraph, three, four);
		insertEdge(unWeightedDirectedGraph, four, seven);
		insertEdge(unWeightedDirectedGraph, four, eight);
		insertEdge(unWeightedDirectedGraph, seven, eight);
		insertEdge(unWeightedDirectedGraph, five, eight);
		insertEdge(unWeightedDirectedGraph, ten, nine);


		printGraph(unWeightedDirectedGraph);

		int inDegree=getInDegreeOfVertex( unWeightedDirectedGraph, two);
		System.out.println("in degree of 2 "+inDegree);
		int outDegree=getOutDegreeOfVertex( unWeightedDirectedGraph, two);
		System.out.println("out degree of 2 "+outDegree);

		bfs(unWeightedDirectedGraph, one);
		System.out.println("Printing path from 1 to 8");
		printPath(unWeightedDirectedGraph, four, two);
		
		List<Element> sequence=dfsTopologicalSort(unWeightedDirectedGraph);
		System.out.println("Printing Topological sort ");
		System.out.println(sequence);

		//unWeightedDirectedGraph=transposeGraph(unWeightedDirectedGraph);
		//printGraph(unWeightedDirectedGraph);
		//unWeightedDirectedGraph=transposeGraph(unWeightedDirectedGraph);
		//printGraph(unWeightedDirectedGraph);
	}

	//maximum number of edges for n vertices is n(n-1)/2
	//this happens in complete graph
	//every vertex is added through insertEdge operation
	//in case only one vertex has to be added, pass it as from vertex and keep the to vertex as null 
	//insertion operation takes O(1) time
	public static Map<Element, Set<Element>> insertEdge(Map<Element, Set<Element>>  unWeightedDirectedGraph, Element from, Element to){

		//Assuming graph can not contain null vertices
		if(from==null){
			return  unWeightedDirectedGraph;
		}

		//get adjacency set of the element:: O(1)
		Set<Element> toElements= unWeightedDirectedGraph.get(from);

		//if no adjacent elements create new adjacency set
		if(toElements==null){			
			toElements=new LinkedHashSet<Element>();
		}		

		//add element to adjacency set:: O(1)
		if(to!=null){
			toElements.add(to);
		}

		//add element with updated adjacency set
		//hashMap.put() updates the value of existing key
		//O(1)
		unWeightedDirectedGraph.put(from, toElements);

		//Handling the case of other vertex in the edge
		if(to!=null){
			if( unWeightedDirectedGraph.get(to)==null){
				unWeightedDirectedGraph.put(to, new LinkedHashSet<Element>());
			}
			else{
				unWeightedDirectedGraph.put(to,  unWeightedDirectedGraph.get(to));
			}		
		}

		return  unWeightedDirectedGraph;	

	}
	
	public static boolean isCyclic(Map<Element, Set<Element>>  graph){
		
		
		return false;
	}
	
	public static List<Element> dfsTopologicalSort(Map<Element, Set<Element>>  graph){
		
		List<Element> sequence=new LinkedList<Element>();
		for(Element vertex: graph.keySet()){
			vertex.setColor("White");
			vertex.setParent(null);			
		}
		
		for(Element vertex: graph.keySet()){
			if(vertex.getColor().equals("White")){
				sequence=dfsVisit(graph, vertex, sequence);
				System.out.println("sequence size "+sequence.size());
			}
		}
		
		return sequence;
	}
	
	public static List<Element> dfsVisit(Map<Element, Set<Element>>  graph, Element vertex, List<Element> sequence){
		
		vertex.setColor("Gray");
		
		Set<Element> adjacentVertices=graph.get(vertex);
		
		for(Element u: adjacentVertices){
			
			if(u.getColor().equals("White")){
				u.setParent(vertex);
				u.setColor("Gray");
				dfsVisit(graph, u, sequence);
			}
		}
		vertex.setColor("Black");
		sequence.add(vertex);
		return sequence;
	}

	//prints path from source to destination
	public static boolean printPath(Map<Element, Set<Element>>  graph, Element source, Element destination){

		if(source.equals(destination)){
			System.out.println(source);
			return true;
		}
		else if(destination.getParent()==null){
			System.out.println("Path does not exist");
			return false;
		}
		else{
			//if at any point path is broken then function propagates false 
			//and does not print a single vertex
			boolean isPath=printPath(graph, source, destination.getParent());
			if(isPath){
				System.out.println(destination+" ");
			}
			return isPath;
		}
	} 

	//takes O(E) time
	//for doing BFS from multiple sources, add all the sources to the queue initially
	public static void bfs(Map<Element, Set<Element>>  graph, Element source){

		Set<Element> vertices=graph.keySet();

		for(Element vertex : vertices){

			vertex.setColor("White");
			vertex.setDistance(Float.MAX_VALUE);
			vertex.setParent(null);			
		}

		source.setColor("Gray");
		source.setDistance(0);

		Queue<Element> queue=new LinkedList<Element>();
		queue.offer(source);

		while(!queue.isEmpty()){
			Element v=queue.poll();

			Set<Element> adjacentVertices=graph.get(v);
			for(Element u: adjacentVertices){
				if(u.getColor().equals("White")){
					u.setColor("Gray");
					u.setParent(v);
					u.setDistance(v.getDistance()+1);
					queue.offer(u);
				}
			}
			v.setColor("Black");

		}		

	}

	//not working
	//applies transpose on the given directed graph	
	//total time taken is O(E)
	//it can be in order of V^2
	public static Map<Element, Set<Element>> transposeGraph(Map<Element, Set<Element>>  graph){

		if(graph==null){
			return null;
		}

		Set<Element> visited=new LinkedHashSet<Element>();
		//get all vertices O(V)
		for(Element vertex: graph.keySet()){


			visited.add(vertex);
			//get to vertices for each vertex
			Set<Element> toVertices=graph.get(vertex);
			//System.out.println("Before: Vertex "+vertex+" adjSet "+toVertices);

			Iterator<Element> adjIterator=toVertices.iterator();
			while(adjIterator.hasNext()){

				Element toVertex=adjIterator.next();				

				//maps work like pass by reference
				//no need to put again to modify value
				graph.get(toVertex).add(vertex);

				if(!visited.contains(toVertex)){
					System.out.println("removing "+toVertex);
					adjIterator.remove();
				}

			}
			System.out.println("graph "+graph);
		}	
		return graph;

	}



	//returns in degree of a given vertex
	//takes O(V) time
	public static int getInDegreeOfVertex(Map<Element, Set<Element>>  graph, Element vertex){
		int inDegree=0;

		//get all vertices in the graph: O(V)
		for(Element key :  graph.keySet()){

			//for each vertex get the outgoing edges
			Set<Element> outEdges= graph.get(key);

			if(outEdges!=null){
				//if given vertex is present in the outgoing edges then 
				//increment inDegree count
				//O(1)
				if(outEdges.contains(vertex)){
					inDegree++;
				}
			}
		}

		return inDegree;
	}

	//returns out degree of a vertex
	//takes O(1) time
	public static int getOutDegreeOfVertex(Map<Element, Set<Element>>  graph, Element vertex){

		//get adjacency set
		Set<Element> outVertices= graph.get(vertex);

		//if no adjacent edge return 0
		if(outVertices==null){
			return 0;
		}

		//return size of adjacency set: O(1)
		return outVertices.size();
	}

	public static void printGraph(Map<Element, Set<Element>>  unWeightedDirectedGraph){

		System.out.println("Printing graph");
		if( unWeightedDirectedGraph==null){
			return;
		}

		for( Element key :  unWeightedDirectedGraph.keySet()){
			System.out.print(key+":");
			Set<Element> values= unWeightedDirectedGraph.get(key);			

			for(Element value:  values){				
				System.out.print(" "+value);				
			}
			System.out.println();
		}

	}


}


