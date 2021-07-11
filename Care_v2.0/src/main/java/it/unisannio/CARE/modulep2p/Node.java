package it.unisannio.CARE.modulep2p;


class Location{
	/*
	 * public Location(
    				Country country, 	Region region, 			Province province,
    				City city, 			String street,			String streetNumber, String ZIPCode
    		){
    	
    	setStreet(street);
    	setStreetNumber(streetNumber);
    	setZipCode(ZIPCode);
        this.city = city;
        this.province = province;
        this.region = region;
        this.country = country;
    }
	 */
	
	private String street, streetNumber, ZIPCode, city, province, region, country;

	public Location(
			String country, 	String region, 			String province,
			String city, 			String street,			String streetNumber, String ZIPCode
	){

		this.country 		= country;
		this.region 		= region;
		this.province 		= province;
		this.city 			= city;
		this.street 		= street;
		this.streetNumber 	= streetNumber;
		this.ZIPCode 		= ZIPCode;		
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the zIPCode
	 */
	public String getZIPCode() {
		return ZIPCode;
	}

	/**
	 * @param zIPCode the zIPCode to set
	 */
	public void setZIPCode(String zIPCode) {
		ZIPCode = zIPCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
		
}

/*
class NodeManager() {
	
}
*/


public class Node {

	// forse no: Singleton class, in quanto deve definire univocamente il nodo corrente.
	
	private String codStr, nodeName;
	private Location warehouse;
	//private TreeMap<BloodGroup, ArrayList<Integer>> quantities;
	//private TreeMap<BloodGroup, ArrayList<Integer>> temp_quantities;
	
	
	/**
     **************************************************************************
     * Metodo Costruttore per la creazione del Nodo
     * @param String codStr, String nodeName, Location warehouse
     **************************************************************************
     */
	
	public Node(String codStr, String nodeName, Location warehouse) {
		this.setCodStr(codStr);
		this.setNodeName(nodeName);
		this.setWarehouse(warehouse);
		
		//this.quantities		= new TreeMap<BloodGroup, ArrayList<Integer>>();
		//this.temp_quantities 	= new TreeMap<BloodGroup, ArrayList<Integer>>(); 
	}
	
	/**
     **************************************************************************
     * Metodo GET per ottenere il codice struttura
     * @return codStr
     **************************************************************************
     */
	public String getCodStr() {
		return codStr;
	}
	
	/**
     **************************************************************************
     * Metodo SET per inserire un nuovo il codice struttura
     * @param String codStr
     * @exception IllegalArgumentException
     **************************************************************************
     */
	private void setCodStr(String codStr) {
		if( codStr.length()>3 )	throw new IllegalArgumentException("Node.java - codStr length can't be over three chars");
		this.codStr = codStr;
	}

	/**
     **************************************************************************
     * Metodo GET per ottenere il nome del noto
     * @return nodeName
     **************************************************************************
     */
	public String getNodeName() {
		return nodeName;
	}	

	/**
     **************************************************************************
     * Metodo SET per inserire il nuovo nome di un nodo
     * @param String nodeName
     **************************************************************************
     */
	private void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
     **************************************************************************
     * Metodo GET per ottenere la posizione del nodo
     * @return warehouse
     **************************************************************************
     */
	public Location getWarehouse() {
		return warehouse;
	}
	
	/**
     **************************************************************************
     * Metodo GET per inserire la posizione del nodo
     * @param String warehouse
     **************************************************************************
     */
	private void setWarehouse(Location warehouse) {
		this.warehouse = warehouse;
	}
	
	/**
     **************************************************************************
     * Metodo per la verificare che due nodi siano uguali
     * @param Object obj
     * @return codStr.equals( n.getCodStr() )
     **************************************************************************
     */
	public boolean equals(Object obj) {
		if(obj instanceof Node) {
			Node n = (Node) obj;
			return codStr.equals( n.getCodStr() );
		}
		return false;
	}

	/**
     **************************************************************************
     * Metodo per il ritorno di tutti i dati del nodo
     * @return codStr, NodeName, warehouse.toString()
     **************************************************************************
     */
	public String toString() {
		return   "{\"codStr\": \"" 	  	+ this.codStr   			  + "\""
				+", \"nodeName\": \""  	+ this.nodeName 			  + "\"" 
				+", \"wareHouse\": " 	+ this.warehouse.toString()   + "}";
	}
	
	/**
     **************************************************************************
     * Metodo per i limiti di immagazzinamento sacche di ogni nodo
     * @param BloodGroup bg, int min, int max
     **************************************************************************
     */
	/*
	public void setAllowedQuantity(BloodGroup bg, int min, int max) {
		ArrayList<Integer> bloodgroupLimits = new ArrayList<Integer>();
		bloodgroupLimits.add(min);
		bloodgroupLimits.add(max);
		
		temp_quantities.put(bg, bloodgroupLimits);
		this.setAllowedQuantities(temp_quantities);
	}
	*/
	
	/**
     **************************************************************************
     * Metodo per definisce le quantità minime e massime di sacche di sangue per ogni nodo
     * @param TreeMap<BloodGroup, ArrayList<Integer>> quant
     **************************************************************************
     */
	/*
	private void setAllowedQuantities(TreeMap<BloodGroup, ArrayList<Integer>> quant) {
		// Defines the minimum and maximum bloodbags quantities for each node.
		// Limits exists only if specified.
		//  Apos, [5,7]
		//	Aneg, [3,4]
		//	Bpos, [2,5]
		//	....
	
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
	*/
	/**
     **************************************************************************
     * Metodo per visualizzare il minimo ed il massimo delle quantità di sacche di sangue 
     * @param PrintStream output
     **************************************************************************
     */
	/*
	public void printAllowedQuantities(PrintStream output
										//, TreeMap<BloodGroup, ArrayList<Integer>> quant
									 ) {
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
	*/
	
	public enum NodeIDs {
	    BN001,
	    BN002,
	    NA001,
	    Mo002
	}
}
