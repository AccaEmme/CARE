package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User;

/*
 * https://github.com/AccaEmme/CARE/wiki/5.3-User-authentication-privileges-and-accesses-to-resources
 */

import java.util.Comparator;

public enum Role implements Comparator {
	Administrator, StoreManager, Officer;

	@Override
	public int compare(Object o1, Object o2) {
		return o1.toString().compareTo(o2.toString());

	}
}
