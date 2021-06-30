package src.test.java.it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

	import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
	//import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
	//import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

	public class NodeTest2 {
		
		private Node node;

		@Test
		public void  ValidityTest_Constructor() {
			String codStr = "123";
			String nodeName = "Moscati";
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node(codStr, nodeName, location);
			assertNotNull(node);
		}
		
		@Test
		public void Validity_TestGetCodStr() {
			
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node("345", "Moscati", location);
			node.getCodStr();
			assertNotNull(node);
		} 
		
		@Test
		public void Validity_TestSetCodStr(){
			String codStr= "234";
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node(codStr, "Moscati", location);
			assertNotNull(node);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void Invalidity_TestSetCodStr(){
			String codStr= "2345";
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node(codStr, "Moscati", location);
			assertNotNull(node);
		}
		
		@Test
		public void Validity_TestGetNodeName(){
			
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node("345", "Moscati", location);
			node.getNodeName();
			assertNotNull(node);
		} 
		@Test
		public void Validity_TestSetNodeName() {
			String nodeName= "Moscati";
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node("234", nodeName, location);
			assertNotNull(node);
			
		}
		
		@Test
		public void Validity_TestGetWarehouse() {
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node("345", "Moscati", location);
			node.getWarehouse();
			assertNotNull(node);
		}
		
		@Test
		public void Validity_TestSetWarehouse() {
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node("234", "Moscati", location);
			assertNotNull(node);
			
		}
		
		@Test //(expected = AssertionError.class)
		public void ValidityTest_equals_GivingValidDifferentsObjects() {
			Location location =new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			Node n1 = new Node ("234", "Moscati",location);
			Node n2 = new Node ("234", "Moscati", location);
			assertFalse ( n1.equals(n2));
		}
		
		@Test
			public void InvalidityTest_equals_GivingValidDifferentsObjects(){
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			  Node n1 = new Node("234", "Moscati", location);
			 Node n2 = new Node("125","Rummo",location);
			 assertFalse( n1.equals(n2) );
			}
		
		@Test 
			public void Validity_TesttoString_() {
			String codStr = "234";
			Location location = new Location (Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 4 Novembre", "7", "82020");
			node = new Node(codStr, "Moscati", location);
			String stringa  = node.toString();
			assertTrue( !stringa.equals(null) || !stringa.equals("") );
		}
		
		@Test
		public void Validity_TestSetAllowedQuantity() {
			ArrayList <Integer> Apos = new ArrayList<Integer>();
			ArrayList <Integer> Aneg = new ArrayList<Integer>();
			ArrayList <Integer> Bpos = new ArrayList<Integer>();
			Apos.add(5);
			Apos.add(7);
		
}

	}
