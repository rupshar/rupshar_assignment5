package DiGraph_A5;

import java.util.HashMap;

public class Node implements Comparable<Node> {
	
	private long idNum;
	private String label;
	public boolean known;
	public long distance;
	public HashMap<String, Edge> in_edges;
	public HashMap<String, Edge> out_edges;
	
	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		this.known = false;
		this.distance = 0;
		in_edges = new HashMap<String, Edge>();
		out_edges = new HashMap<String, Edge>();
		
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public long getNum() {
		return this.idNum;
	}

	@Override
	public int compareTo(Node o) {
		return (int) this.distance - (int) o.distance;
	}

}
