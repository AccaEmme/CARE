/**
 * 
 */
package it.unisannio.CARE.model.Testers;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.Care.Spring.UserBean;

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
		UserBean ub = new UserBean();
		ub.setUsername(null);
		ub.setCreationDate(new Date());
		ub.setLastAccess(new Date());
	}

}
