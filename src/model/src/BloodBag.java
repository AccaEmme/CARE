public class BloodBag {
	public BloodBag(Blood b) {
		this.serial=new Serial(b);
		this.blood=b;
//		this.expireDate=expireDate;
	}
	
	public Serial getSerial() {
		return this.serial;
	}
	
	public Blood getBloodType() {
		return this.blood;
	}

	@Override
	public String toString() {
		return "BloodBag [serial=" + serial + ", group=" + blood /*+ ", expireDate=" + expireDate + */+"]";
	}

	private final Serial 	serial;
	private final Blood 	blood;
}
