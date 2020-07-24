package objects;

import java.sql.Date;

public class Person {
	
		long personId ;
		String personName;
		Date dob;
		String gender;
		String bloodGrp;	
		long phoneNo;
		String mailId;
		String address;
		String city;
		public Person(long personId, String personName, Date dob, String gender, String bloodGrp, long phoneNo,
				String mailId, String address,String city) {
			this.personId = personId;
			this.personName = personName;
			this.dob = dob;
			this.gender = gender;
			this.bloodGrp = bloodGrp;
			this.phoneNo = phoneNo;
			this.mailId = mailId;
			this.address = address;
			this.city = city;
		}
		public long getPersonId() {
			return personId;
		}
		public void setPersonId(long personId) {
			this.personId = personId;
		}
		public String getPersonName() {
			return personName;
		}
		public void setPersonName(String personName) {
			this.personName = personName;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getBloodGrp() {
			return bloodGrp;
		}
		public void setBloodGrp(String bloodGrp) {
			this.bloodGrp = bloodGrp;
		}
		public long getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(long phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getMailId() {
			return mailId;
		}
		public void setMailId(String mailId) {
			this.mailId = mailId;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		@Override
		public String toString() {
			return "Person [personId=" + personId + ", personName=" + personName + ", dob=" + dob + ", gender=" + gender
					+ ", bloodGrp=" + bloodGrp + ", phoneNo=" + phoneNo + ", mailId=" + mailId + ", address=" + address
					+ ", city=" + city + "]";
		}
		
}
