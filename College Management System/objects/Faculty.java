package objects;

import java.sql.Date;

public class Faculty {
	
	long facultyId;
	Person person;
	Department department;
	String designation;
	double salary;
	Date doj;
	
	public Faculty(long facultyId, Person person, Department department,Date doj, String designation, double salary) {
		this.facultyId = facultyId;
		this.person = person;
		this.department = department;
		this.designation = designation;
		this.salary = salary;
		this.doj = doj;
	}

	public long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(long facultyId) {
		this.facultyId = facultyId;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", person=" + person + ", department=" + department
				+ ", designation=" + designation + ", salary=" + salary + ", doj=" + doj + "]";
	}

}
