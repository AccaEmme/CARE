/**
 * 
 */
package it.unisannio.CARE.model.testers;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;

/**
 * @author Acca
 *
 */
public class UserBeanTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO ub = new UserDAO();
		ub.setUsername(null);
		ub.setCreationDate(new Date());
		ub.setLastAccess(new Date());
	}

}
