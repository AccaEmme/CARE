package usersabstract;

import java.util.Date;

public class WarehouseWorker extends Users{

	public WarehouseWorker(String name, String surname, String location, String hiringType, String username,
			String password, Date dateOfBirth, Date assumptionDate,Qualification q) {
		super(name, surname, location, hiringType, username, password, dateOfBirth, assumptionDate);
		this.qualification=q;
	}
	public WarehouseWorker() {
		super();
		Qualification qu=Qualification.valueOf("managers");
		this.qualification=qu;
	}
	

	private Qualification qualification;
}
