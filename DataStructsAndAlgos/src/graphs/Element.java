package graphs;

public class Element implements Comparable <Element>{

	private int key;
	
	private String color;
	
	private float distance;
	
	private Element parent;
	
	public Element() {
		// TODO Auto-generated constructor stub
	}
	
	public Element(int key){
		this.key=key;
	}
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		return key;
	}

	public boolean equals(Object o){
		
		if(o==null || this.getClass() != o.getClass()){
			return false;
		}
		return this.key==((Element)o).key;		
	}

	@Override
	public int compareTo(Element element) {
		return this.key-element.key;
	}
	
	public String toString(){
		return ""+key;
	}

}
