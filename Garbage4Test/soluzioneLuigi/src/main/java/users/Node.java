package users;

import java.text.SimpleDateFormat;
import java.util.LinkedList;



public class Node {
	public Node(String codStr, String nodeName, Location warehouse) {
		this.codStr 	= codStr;
		this.nodeName 	= nodeName;
		this.warehouse 	= warehouse;
	}
	
	public String getCodStr() {
		return codStr;
	}
	
	public void setCodStr(String codStr) {
		this.codStr = codStr;
	}

	public String getNodeName() {
		return nodeName;
	}	

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	
	public Location getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Location warehouse) {
		this.warehouse = warehouse;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BloodBag) {
			Node n = (Node) obj;
			return codStr.equals( n.getCodStr() );
		}
		return false;
	}

	public String toString() {
		return   "{\"codStr\": \"" 	  	+ this.codStr   			  + "\""
				+", \"nodeName\": \""  	+ this.nodeName 			  + "\"" 
				+", \"wareHouse\": " 	+ this.warehouse.toString()   + "}";
	}
	
	private String codStr, nodeName;
	private Location warehouse;
	
	
		
}