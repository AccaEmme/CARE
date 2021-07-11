package it.unisannio.CARE.modulep2p;

import java.util.EnumMap;

import it.unisannio.CARE.modulep2p.Node.NodeIDs;


/**
 * this class is used to specify a routing table
 * not used anymore in favour of a dynamic routing table
 */
@Deprecated
public class RoutingTable {
	private String id, codStr, city, address, endpoint;

	public RoutingTable(String id, String codStr, String city, String address) {
		super();
		this.id 	 = id;
		this.codStr  = codStr;
		this.city 	 = city;
		this.address = address;
		this.endpoint = "";
		
	}

	void setAll() {
		EnumMap<NodeIDs, String> routingTable = new EnumMap<NodeIDs, String>(NodeIDs.class);
		routingTable.put(NodeIDs.BN001, "http://192.168.150.63:8088");
		routingTable.put(NodeIDs.BN002, "http://192.168.150.64:8088");
		routingTable.put(NodeIDs.NA001, "");
		routingTable.put(NodeIDs.Mo002, "");
	}
	
}
