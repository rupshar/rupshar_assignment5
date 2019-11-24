package DiGraph_A5;

public class Edge {
	
	//long idNum, String sLabel, String dLabel, long weight, String eLabel
	private long idNum;
	private String sLabel;
	private String dLabel;
	private long weight;
	private String eLabel;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
	
	public long getID() {
		return idNum;
	}
	
	public String getSLabel() {
		return sLabel;
	}
	
	public String getDLabel() {
		return dLabel;
	}
	
	public long getWeight() {
		return weight;
	}
	
	public String getELabel() {
		return eLabel;
	}

}
