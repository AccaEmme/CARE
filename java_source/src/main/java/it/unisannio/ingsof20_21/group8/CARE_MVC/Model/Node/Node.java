package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class Node {
	public Node(String codStr, String nodeName, Location warehouse) {
		this.setCodStr(codStr);
		this.setNodeName(nodeName);
		this.setWarehouse(warehouse);
		
		this.quantities		 = new TreeMap<BloodGroup, ArrayList<Integer>>();
		this.temp_quantities = new TreeMap<BloodGroup, ArrayList<Integer>>(); 
	}
	
	public String getCodStr() {
		return codStr;
	}
	
	private void setCodStr(String codStr) {
		if( codStr.length()>3 )	throw new IllegalArgumentException("Node.java - codStr length can't be over three chars");
		this.codStr = codStr;
	}

	public String getNodeName() {
		return nodeName;
	}	

	private void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	
	public Location getWarehouse() {
		return warehouse;
	}

	private void setWarehouse(Location warehouse) {
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
	
	public void setAllowedQuantity(BloodGroup bg, int min, int max) {
		ArrayList<Integer> bloodgroupLimits = new ArrayList<Integer>();
		bloodgroupLimits.add(min);
		bloodgroupLimits.add(max);
		
		temp_quantities.put(bg, bloodgroupLimits);
		this.setAllowedQuantities(temp_quantities);
	}
	
	
	private void setAllowedQuantities(TreeMap<BloodGroup, ArrayList<Integer>> quant) {
		/* Defines the minimum and maximum bloodbags quantities for each node.
		 * Limits exists only if specified.
		 *  Apos, [5,7]
			Aneg, [3,4]
			Bpos, [2,5]
			....
		 */
		int min, max;
		for (BloodGroup bg : quant.keySet()) {
			 min = quant.get(bg).get(0).intValue();
			 max = quant.get(bg).get(1).intValue();
		     if(min >= max) throw new IllegalArgumentException("Node quantities"+bg+": min should be less or equals then max");
		     if(max<=0) 	throw new IllegalArgumentException("Node quantities"+bg+": max should be greater than 0");
		     if(min<0) 		throw new IllegalArgumentException("Node quantities"+bg+": min can't be negative");
		}	
		this.quantities=quant;
	}
	
	public void printAllowedQuantities(PrintStream output/*, TreeMap<BloodGroup, ArrayList<Integer>> quant*/) {
		int min, max, firstvalue=1;
		output.println("[");
		if(!quantities.isEmpty())
		  for (BloodGroup bg : quantities.keySet()) {
			 if(firstvalue!=1) output.println(", "); 
			 min = quantities.get(bg).get(0);
			 max = quantities.get(bg).get(1);
		     output.print(" {\"BloodGroup\": \"" + bg + "\", \"minValue\": " + min + ", \"maxValue\": " + max+"}");
		     firstvalue=0;
		  }
		output.println("]");
	}
	
	private String codStr, nodeName;
	private Location warehouse;
	private TreeMap<BloodGroup, ArrayList<Integer>> quantities;
	private TreeMap<BloodGroup, ArrayList<Integer>> temp_quantities;
}