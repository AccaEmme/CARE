package datamanagers;

import java.text.ParseException;


import users.User;

public interface DataManager {
	void createDB();
	void dropDB();
	/*void writeLog(Logger logger);
	void writeLog(Date currentDate, users.User currentUser, String currentClass, String currentMethod, String currentResult);*/
	void addUser(User u0) throws ParseException;

}
