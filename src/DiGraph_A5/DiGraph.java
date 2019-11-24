package DiGraph_A5;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class DiGraph implements DiGraphInterface {

	private long num_nodes;
	private long num_edges;
	private HashMap<String, Node> nodes_label;
	private HashMap<String, Node> nodes_num;
	private HashMap<String, Edge> edges_label;
	private HashMap<String, Edge> edges_num;
	private HashMap<String, Integer> shortest_path;

	// in here go all your data and methods for the graph

	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
		this.num_nodes = 0;
		this.num_edges = 0;
		nodes_label = new HashMap<String, Node>();
		nodes_num = new HashMap<String, Node>();
		edges_label = new HashMap<String, Edge>();
		edges_num = new HashMap<String, Edge>();
		shortest_path = new HashMap<String, Integer>();
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(idNum < 0) {
			return false;
		} else if(nodes_label.containsKey(label)) {
			return false;
		} else if(nodes_num.containsKey(Long.toString(idNum))) {
			return false;
		}
		
		Node new_node = new Node(idNum, label);
		nodes_label.put(new_node.getLabel(), new_node);
		nodes_num.put(Long.toString(new_node.getNum()), new_node);
		num_nodes++;
		return true;
	}
	
	public boolean addEdge(long idNum, String sLabel, String dLabel) {
		boolean edgeAdded = addEdge(idNum, sLabel, dLabel, 0, null);
		if(edgeAdded) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if(idNum < 0) {
			return false;
		} else if(!this.nodes_label.containsKey(sLabel) || !this.nodes_label.containsKey(dLabel)) {
			return false;
		} else if(this.edges_num.containsKey(Long.toString(idNum))) {
			return false;
		} else if(this.nodes_label.get(sLabel).out_edges.containsKey(dLabel) && this.nodes_label.get(sLabel).out_edges.get(dLabel) != null) {
			return false;
		}
		
		Edge new_edge;
		
		if(weight == 0) {
			new_edge = new Edge(idNum, sLabel, dLabel, 1, eLabel);
		} else {
			new_edge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		}
		
		edges_label.put(eLabel, new_edge);
		edges_num.put(Long.toString(idNum), new_edge);
		
		Node sNode = nodes_label.get(sLabel);
		Node dNode = nodes_label.get(dLabel);
		
		sNode.out_edges.put(dLabel, new_edge);
		dNode.in_edges.put(sLabel, new_edge);
		
		num_edges++;
		
		return true;
	}

	@Override
	public boolean delNode(String label) {
		if(!nodes_label.containsKey(label)) {
			return false;
		}
		
		Node node_to_delete = nodes_label.get(label);
		List<String> in_edge_labels = new ArrayList<String>();
		List<String> out_edge_labels = new ArrayList<String>();
		
		for(Edge in_edge : node_to_delete.in_edges.values()) {
			Node sNode = nodes_label.get(in_edge.getSLabel());
			sNode.out_edges.remove(node_to_delete.getLabel());
			in_edge_labels.add(sNode.getLabel());
			num_edges--;
		}
		
		for(int i = 0; i < in_edge_labels.size(); i++) {
			node_to_delete.in_edges.remove(in_edge_labels.get(i));
			
		}
		
		for(Edge out_edge : node_to_delete.out_edges.values()) {
			Node dNode = nodes_label.get(out_edge.getDLabel());
			dNode.in_edges.remove(node_to_delete.getLabel());
			in_edge_labels.add(dNode.getLabel());
			num_edges--;
		}
		
		for(int i = 0; i < out_edge_labels.size(); i++) {
			node_to_delete.out_edges.remove(out_edge_labels.get(i));
			
		}
		
		nodes_label.remove(label);
		nodes_num.remove(Long.toString(node_to_delete.getNum()));
		
		num_nodes--;
		
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if(!nodes_label.containsKey(sLabel) || !nodes_label.containsKey(dLabel)) {
			return false;
		} else if(!nodes_label.get(sLabel).out_edges.containsKey(dLabel) || nodes_label.get(sLabel).out_edges.get(dLabel) == null) {
			return false;
		}
		
		Node sNode = nodes_label.get(sLabel);
		Node dNode = nodes_label.get(dLabel);
		
		Edge out_edge = sNode.out_edges.get(dLabel);
		
		sNode.out_edges.remove(dLabel);
		dNode.in_edges.remove(sLabel);
		
		edges_label.remove(out_edge.getELabel());
		edges_num.remove(Long.toString(out_edge.getID()));
		
		num_edges--;
		
		return true;
	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return num_nodes;
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return num_edges;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		for(Node n : nodes_label.values()) {
			n.distance = 0;
			n.known = false;
		}
		
		shortest_path.clear();
		
		Node origin = nodes_label.get(label);
		
		ShortestPathInfo[] ret_arr = new ShortestPathInfo[(int)num_nodes];
		
		PriorityQueue<Node> adjacent_nodes = new PriorityQueue<Node>();
		
		if(!nodes_label.containsKey(label) || label == null) {
			int i = 0;
			for(String missing_label : nodes_label.keySet()) {
				ret_arr[i] = new ShortestPathInfo(missing_label, -1);
				i++;
			}
			return ret_arr;
		}
		
		origin.distance = 0;
		origin.known = true;
		
		adjacent_nodes.add(origin);
		
		shortest_path.put(origin.getLabel(), 0);
		
		for(Edge out_edge : origin.out_edges.values()) {
			Node adj_node = nodes_label.get(out_edge.getDLabel());
			
			adj_node.distance = out_edge.getWeight();
			adjacent_nodes.add(adj_node);
		}
	
		while(adjacent_nodes.size() > 0) {
			Node new_node = adjacent_nodes.remove();
			
			if(new_node.known) {
				continue;
			} else {
				new_node.known = true;
			}
			
			shortest_path.put(new_node.getLabel(), (int)new_node.distance);
			
			for(Edge adj_out_edge : new_node.out_edges.values()) {
				Node adj_node = nodes_label.get(adj_out_edge.getDLabel());
				
				if(adjacent_nodes.contains(adj_node)) {
					if(adj_node.distance > adj_out_edge.getWeight() + new_node.distance) {
						adj_node.distance = adj_out_edge.getWeight() + new_node.distance;
					}
				} else {
					adj_node.distance = adj_out_edge.getWeight() + new_node.distance;
				}	
				
				adjacent_nodes.add(adj_node);
				
			}
			
		}
		
		for(Node node : nodes_label.values()) {
			if(!node.known) {
				shortest_path.put(node.getLabel(), -1);
			}
		}
		
		int i = 0;
		
		for(String labels : shortest_path.keySet()) {
			int dist = shortest_path.get(labels);	
			ret_arr[i] = new ShortestPathInfo(labels, dist);
			i++;
		}
		
		return ret_arr;
	}

	// rest of your code to implement the various operations
}