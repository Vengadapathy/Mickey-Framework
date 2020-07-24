package objects;

import java.sql.Date;

public class Student {
	
	long studentId;
	Person person;
	Department department;
	Date doa;
	int year;
	String section;
	double feesDue;
	
	public Student(long studentId, Person person, Department department, Date doa, int year, String section,
			double feesDue) {
		this.studentId = studentId;
		this.person = person;
		this.department = department;
		this.doa = doa;
		this.year = year;
		this.section = section;
		this.feesDue = feesDue;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getDoa() {
		return doa;
	}
	public void setDoa(Date doa) {
		this.doa = doa;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public double getFeesDue() {
		return feesDue;
	}
	public void setFeesDue(double feesDue) {
		this.feesDue = feesDue;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", person=" + person + ", department=" + department + ", doa=" + doa
				+ ", year=" + year + ", section=" + section + ", feesDue=" + feesDue + "]";
	}
	
}	
