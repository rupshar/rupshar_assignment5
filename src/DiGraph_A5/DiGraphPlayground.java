package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      millionTest();
    }
  
  	public static void millionTest() {
  		String[] labels = new String[2000000];
  		DiGraph d = new DiGraph();
  		labels[0] = "a";
  		d.addNode(0, labels[0]);
  		for(int i = 1; i < 2000000; i++) {
  			String nLabel = getAlphaNumericString(14);
  			labels[i] = nLabel;
  			d.addNode(i, nLabel);
  		}
  		for(int i = 1; i < 2000000; i++) {
  			d.addEdge(i, labels[i-1], labels[i]);
  		}
  		
  		ShortestPathInfo[] info2 = d.shortestPath("a");
        
        for(int i = 0; i < info2.length; i++) {
      	  System.out.println("(Node: " + info2[i].getDest() + ", Distance: " + info2[i].getTotalWeight() + ")");
        }
  	}
  	
  	public static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
  
  	public static void mapTest() {
  		DiGraph d = new DiGraph();
  		d.addNode(1, "Raleigh");
  		d.addNode(2, "Durham");
  		d.addNode(3,  "Chapel Hill");
  		d.addNode(4, "Pittsboro");
  		d.addNode(5, "Los Angeles");
  		d.addNode(6,  "Graham");
  		d.addNode(7,  "Cary");
  		d.addNode(8, "Hillsborough");
  		d.addNode(9,  "Carrboro");
  		d.addNode(10, "Sanford");
  		
  		d.addEdge(1, "Raleigh", "Durham", 14, null);
  		d.addEdge(2, "Durham", "Hillsborough", 9, null);
  		d.addEdge(3, "Chapel Hill", "Graham", 25, null);
  		d.addEdge(4, "Chapel Hill", "Carrboro", 1, null);
  		d.addEdge(9, "Carrboro", "Cary", 32, null);
  		d.addEdge(5, "Cary", "Raleigh", 3, null);
  		d.addEdge(6, "Pittsboro", "Cary", 17, null);
  		d.addEdge(7, "Pittsboro", "Sanford", 15, null);
  		d.addEdge(8, "Sanford", "Los Angeles", 3012, null);
  		
  		ShortestPathInfo[] info = d.shortestPath("Pittsboro");
        
        for(int i = 0; i < info.length; i++) {
      	  System.out.println("(Node: " + info[i].getDest() + ", Distance: " + info[i].getTotalWeight() + ")");
        }
  		
  		
  	}
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(0, "a");
      d.addNode(1, "b");
      d.addNode(2, "c");
      d.addNode(3, "d");
      d.addNode(4, "e");
      d.addNode(5, "f");
      d.addNode(6, "g");
      d.addNode(7, "h");
      d.addNode(8, "i");
      d.addNode(9, "j");
      d.addNode(10, "k");
      
      d.addEdge(3, "a", "b", 3, null);
      d.addEdge(4, "a", "c", 5, null);
      d.addEdge(5, "b", "c", 4, null);
      
      ShortestPathInfo[] info = d.shortestPath("a");
      
      for(int i = 0; i < info.length; i++) {
    	  System.out.println("(Node: " + info[i].getDest() + ", Distance: " + info[i].getTotalWeight() + ")");
      }
      
      System.out.println();
      
      d.delNode("e");
      d.delNode("f");
      d.delNode("g");
      d.delNode("h");
      d.delNode("i");
      d.delNode("j");
      d.delNode("k");
      
      d.delEdge("a", "c");
      
      ShortestPathInfo[] info2 = d.shortestPath("a");
      
      for(int i = 0; i < info2.length; i++) {
    	  System.out.println("(Node: " + info2[i].getDest() + ", Distance: " + info2[i].getTotalWeight() + ")");
      }
      
    }
}