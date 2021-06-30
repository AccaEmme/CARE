/**
 * 
 */
package it.unisannio.CARE.spring.z.testers;

import java.util.Date;

import it.unisannio.CARE.spring.UserBean;

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
