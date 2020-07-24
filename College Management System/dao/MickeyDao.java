package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.DeleteQueryImpl;
import com.adventnet.ds.query.Join;
import com.adventnet.ds.query.Operation;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.ds.query.SelectQueryImpl;
import com.adventnet.ds.query.Table;
import com.adventnet.ds.query.UpdateQueryImpl;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;

import objects.Department;
import objects.Faculty;
import objects.Person;
import objects.Student;

public class MickeyDao {
		//Creates a New Person Entry with specified Person Object
		public Person createPerson(Person person) {
			Row row = new Row("Person");
			row.set("PERSON_NAME", person.getPersonName());
			row.set("DOB", person.getDob());
			row.set("GENDER",person.getGender());
			row.set("BLOOD_GRP", person.getBloodGrp());
			row.set("PHONE_NO", person.getPhoneNo());
			row.set("MAILID",person.getMailId());
			row.set("ADDRESS", person.getAddress());
			row.set("CITY", person.getCity());
			DataObject dataObj = new WritableDataObject();
			try {
				dataObj.addRow(row);
				DataAccess.add(dataObj);
				person.setPersonId((long) row.get("PERSON_ID"));
				System.out.println("Person ID = "+row.get("PERSON_ID"));
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return person;
		}
		
		//Creates a New Student Entry with specified Student Object
		public Student createStudent(Student student) {
			createPerson(student.getPerson());
			Row row = new Row("Student");
			row.set("PERSON_ID", student.getPerson().getPersonId());
			row.set("DEPT_ID",student.getDepartment().getDeptCode());
			row.set("DOA", student.getDoa());
			row.set("YEAR",student.getYear());
			row.set("SECTION", student.getSection());
			row.set("FEES_DUE", student.getFeesDue());
			DataObject dataObj = new WritableDataObject();
			try {
				dataObj.addRow(row);
				DataAccess.add(dataObj);
				student.setStudentId((long) row.get("STUDENT_ID"));
				System.out.println("Student ID = "+student.getStudentId());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return student;
		}

		//Creates a New Faculty Entry with specified Faculty Object
		public Faculty createFaculty(Faculty faculty) {
			createPerson(faculty.getPerson());
			Row row = new Row("Faculty");
			row.set("PERSON_ID", faculty.getPerson().getPersonId());
			row.set("DEPT_ID",faculty.getDepartment().getDeptCode());
			row.set("DOJ", faculty.getDoj());
			row.set("DESIGNATION",faculty.getDesignation());
			row.set("SALARY", faculty.getSalary());
			DataObject dataObj = new WritableDataObject();
			try {
				dataObj.addRow(row);
				DataAccess.add(dataObj);
				faculty.setFacultyId((long) row.get("FACULTY_ID"));
				System.out.println("Faculty ID = "+faculty.getFacultyId());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return faculty;
		}
		
		//Updates the Changeable columns values for a Student with the student Object
		public boolean updateStudent(Student student) {
			UpdateQueryImpl query = new UpdateQueryImpl("Student");
			Criteria joinCriteria = new Criteria(Column.getColumn("Student", "PERSON_ID"),Column.getColumn("Person", "PERSON_ID"),QueryConstants.EQUAL);
			Join join1 = new Join("Student","Person",joinCriteria,Join.INNER_JOIN);
			Join join2 = new Join("Student","Department",new String[] {"DEPT_ID"},new String[] {"DEPT_ID"},Join.INNER_JOIN);
			Criteria updateCriteria = new Criteria(Column.getColumn("Student", "STUDENT_ID"),student.getStudentId(),QueryConstants.EQUAL);
			query.setUpdateColumn("DEPT_ID",student.getDepartment().getDeptCode());
			query.setUpdateColumn("PERSON_NAME", student.getPerson().getPersonName());
			query.setUpdateColumn("PHONE_NO",  student.getPerson().getPhoneNo());
			query.setUpdateColumn("MAILID", student.getPerson().getMailId());
			query.setUpdateColumn("ADDRESS",  student.getPerson().getAddress());
			query.setUpdateColumn("CITY",  student.getPerson().getCity());
			query.setUpdateColumn("YEAR", student.getYear());
			query.setUpdateColumn("SECTION", student.getSection());
			query.setUpdateColumn("FEES_DUE", student.getFeesDue());
			query.setCriteria(updateCriteria);
			query.addJoin(join1);
			query.addJoin(join2);
			try {
				DataAccess.update(query);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Updates the Changeable columns values for a Faculty with the Faculty Object
		public boolean updateFaculty(Faculty faculty) {
			UpdateQueryImpl query = new UpdateQueryImpl("Faculty");
			Criteria joinCriteria = new Criteria(Column.getColumn("Faculty", "PERSON_ID"),Column.getColumn("Person", "PERSON_ID"),QueryConstants.EQUAL);
			Join join1 = new Join("Faculty","Person",joinCriteria,Join.INNER_JOIN);
			Join join2 = new Join("Faculty","Department",new String[] {"DEPT_ID"},new String[] {"DEPT_ID"},Join.INNER_JOIN);
			Criteria updateCriteria = new Criteria(Column.getColumn("Faculty", "FACULTY_ID"),faculty.getFacultyId(),QueryConstants.EQUAL);
			query.setUpdateColumn("DEPT_ID",faculty.getDepartment().getDeptCode());
			query.setUpdateColumn("PERSON_NAME", faculty.getPerson().getPersonName());
			query.setUpdateColumn("PHONE_NO",  faculty.getPerson().getPhoneNo());
			query.setUpdateColumn("MAILID", faculty.getPerson().getMailId());
			query.setUpdateColumn("ADDRESS",  faculty.getPerson().getAddress());
			query.setUpdateColumn("CITY",  faculty.getPerson().getCity());
			query.setUpdateColumn("DESIGNATION", faculty.getDepartment());
			query.setUpdateColumn("SALARY", faculty.getSalary());
			query.setCriteria(updateCriteria);
			query.addJoin(join1);
			query.addJoin(join2);
			try {
				DataAccess.update(query);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Updates Fees Due for particular student when studentId is given or else Updates for all students
		//Based on Operation type ADD or SUBTRACT operations is carried out specified in function call
		public boolean updateFeesDue(Long studentId,double feesDueUpdate,Operation.operationType operation) {
			UpdateQueryImpl query = new UpdateQueryImpl("Student");
			if(studentId != null) {
					Criteria updateCriteria = new Criteria(Column.getColumn("Student", "STUDENT_ID"),studentId,QueryConstants.EQUAL);
					query.setCriteria(updateCriteria);
			}
			Column updateFees = Column.createOperation(operation, Column.getColumn("Student", "FEES_DUE"), feesDueUpdate);
			updateFees.setDataType("DOUBLE");
			query.setUpdateColumn("FEES_DUE", updateFees);
			try {
				DataAccess.update(query);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Updates Fees Due for particular faculty when facultyid is given or else Updates for all faculties
		//Based on Operation type ADD or SUBTRACT operations is carried out specified in function call
		public boolean updateSalary(Long facultyId,double salaryUpdate,Operation.operationType operation) {
			UpdateQueryImpl query = new UpdateQueryImpl("Faculty");
			if(facultyId != null) {
					Criteria updateCriteria = new Criteria(Column.getColumn("Faculty", "FACULTY_ID"),facultyId,QueryConstants.EQUAL);
					query.setCriteria(updateCriteria);
			}
			Column updateSalary = Column.createOperation(operation,Column.getColumn("Faculty", "SALARY"), salaryUpdate);
			updateSalary.setDataType("DOUBLE");
			query.setUpdateColumn("SALARY", updateSalary);
			try {
				DataAccess.update(query);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Deleted the Specified Student datas present in related tables
		public boolean deleteStudent(long studentId) {
			DeleteQueryImpl query = new DeleteQueryImpl("Student");
			Criteria cr = new Criteria(Column.getColumn("Student", "Student_ID"),new Long(studentId),QueryConstants.EQUAL);
			query.setCriteria(cr);
			try {
				DataAccess.delete(query);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//deleted the specified Faculty data present in all related tables
		public boolean deleteFaculty(long facultyId) {
			DeleteQueryImpl query = new DeleteQueryImpl("Faculty");
			Criteria cr = new Criteria(Column.getColumn("Faculty", "FACULTY_ID"),new Long(facultyId),QueryConstants.EQUAL);
			query.setCriteria(cr);
			try {
				DataAccess.delete(query);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Gets the Details of a particular student when studentId is specified or returns all Student Data as a List
		public ArrayList<Student> getStudentList(Long studentId){
			ArrayList<Student> list = new ArrayList<>();
			SelectQueryImpl query = new SelectQueryImpl(new Table("Student"));
			Criteria cr = new Criteria(Column.getColumn("Student", "PERSON_ID"),Column.getColumn("Person", "PERSON_ID"),QueryConstants.EQUAL);
			Join join1 = new Join("Student","Person",cr,Join.INNER_JOIN);
			Join join2 = new Join("Student","Department",new String[] {"DEPT_ID"},new String[] {"DEPT_ID"},Join.INNER_JOIN);
			query.addSelectColumn(Column.getColumn("Student", "*"));
			query.addSelectColumn(Column.getColumn("Person", "*"));
			query.addSelectColumn(Column.getColumn("Department", "*"));
			query.addJoin(join1);
			query.addJoin(join2);
			if(! ("null").equals(studentId)) {
				System.out.println("In criteria");
				Criteria idCriteria = new Criteria(Column.getColumn("Student", "STUDENT_ID"),new Long(studentId),QueryConstants.EQUAL);
				query.setCriteria(idCriteria);
			}
			System.out.println("after criteria");
			DataObject obj;
			try {
				obj = DataAccess.get(query);
				Iterator<Row> iter = obj.getRows("Student");
				while(iter.hasNext()) {
					System.out.println("In iterator");
				Row studentRow = iter.next();
				Row deptRow = obj.getRow("Department", studentRow);
				Row personRow = obj.getRow("Person", studentRow);
				Department dept = new Department((long)deptRow.get("DEPT_ID"),deptRow.get("DEPT_NAME").toString());
				Person person = new Person((long)personRow.get("PERSON_ID"),personRow.get("PERSON_NAME").toString(), (Date) personRow.get("DOB"), personRow.get("GENDER").toString(), personRow.get("BLOOD_GRP").toString(), (long) personRow.get("PHONE_NO"), personRow.get("MAILID").toString(),personRow.get("ADDRESS").toString(),personRow.get("CITY").toString());
				Student student = new Student((long)studentRow.get("STUDENT_ID"), person, dept,   (Date) studentRow.get("DOA"),(int) studentRow.get("YEAR"), studentRow.get("SECTION").toString(),(double) studentRow.get("FEES_DUE"));
				list.add(student);
				}
			} catch ( DataAccessException e) {
				e.printStackTrace();
				return null;
			}
			return list;
		}

		//Gets the Details of a particular faculty when facultyid is specified or returns all Faculty Data as a List
		public ArrayList<Faculty> getFacultyList(Long facultyId){
			ArrayList<Faculty> list = new ArrayList<>();
			SelectQueryImpl query = new SelectQueryImpl(new Table("Faculty"));
			Criteria cr = new Criteria(Column.getColumn("Faculty", "PERSON_ID"),Column.getColumn("Person", "PERSON_ID"),QueryConstants.EQUAL);
			Join join1 = new Join("Faculty","Person",cr,Join.INNER_JOIN);
			Join join2 = new Join("Faculty","Department",new String[] {"DEPT_ID"},new String[] {"DEPT_ID"},Join.INNER_JOIN);
			query.addSelectColumn(Column.getColumn("Person", "*"));
			query.addSelectColumn(Column.getColumn("Faculty", "*"));
			query.addSelectColumn(Column.getColumn("Department", "*"));
			query.addJoin(join1);
			query.addJoin(join2);
			if(! ("null").equals(facultyId)) {
				Criteria idCriteria = new Criteria(Column.getColumn("Faculty", "FACULTY_ID"),facultyId,QueryConstants.EQUAL);
				query.setCriteria(idCriteria);
			}
			DataObject obj;
			try {
				obj = DataAccess.get(query);
				Iterator<Row> iter = obj.getRows("Faculty");
				while(iter.hasNext()) {
				Row facultyRow = iter.next();
				Row deptRow = obj.getRow("Department", facultyRow);
				Row personRow = obj.getRow("Person", facultyRow);
				Department dept = new Department((long)deptRow.get("DEPT_ID"),deptRow.get("DEPT_NAME").toString());
				Person person = new Person((long)personRow.get("PERSON_ID"),personRow.get("PERSON_NAME").toString(), (Date) personRow.get("DOB"), personRow.get("GENDER").toString(), personRow.get("BLOOD_GRP").toString(), (long) personRow.get("PHONE_NO"), personRow.get("MAILID").toString(),personRow.get("ADDRESS").toString(),personRow.get("CITY").toString());
				Faculty faculty = new Faculty((long)facultyRow.get("FACULTY_ID"), person, dept,   (Date) facultyRow.get("DOJ"), facultyRow.get("DESIGNATION").toString(),(double) facultyRow.get("SALARY"));
				System.out.println(faculty);
				list.add(faculty);
				}
			} catch ( DataAccessException e) {
				e.printStackTrace();
				return null;
			}
			return list;
			}
		
		//Returns the List of students Having fees dues above a specified limit of feesdue 
		public ArrayList<Student> getPendingFeesDueList(double feesDue){
			ArrayList<Student> list = new ArrayList<>();
			SelectQueryImpl query = new SelectQueryImpl(new Table("Student"));
			Criteria cr = new Criteria(Column.getColumn("Student", "PERSON_ID"),Column.getColumn("Person", "PERSON_ID"),QueryConstants.EQUAL);
			Join join1 = new Join("Student","Person",cr,Join.INNER_JOIN);
			Join join2 = new Join("Student","Department",new String[] {"DEPT_ID"},new String[] {"DEPT_ID"},Join.INNER_JOIN);
			query.addSelectColumn(Column.getColumn("Student", "*"));
			query.addSelectColumn(Column.getColumn("Person", "*"));
			query.addSelectColumn(Column.getColumn("Department", "*"));
			query.addJoin(join1);
			query.addJoin(join2);
			if(!("null").equals(feesDue)) {
					Criteria feesCriteria = new Criteria(Column.getColumn("Student", "FEES_DUE"),new Double(feesDue),QueryConstants.GREATER_THAN);
					query.setCriteria(feesCriteria);
			}
		
			DataObject obj;
			try {
				obj = DataAccess.get(query);
				Iterator<Row> iter = obj.getRows("Student");
				while(iter.hasNext()) {
				Row studentRow = iter.next();
				Row deptRow = obj.getRow("Department", studentRow);
				Row personRow = obj.getRow("Person", studentRow);
				Department dept = new Department((long)deptRow.get("DEPT_ID"),deptRow.get("DEPT_NAME").toString());
				Person person = new Person((long)personRow.get("PERSON_ID"),personRow.get("PERSON_NAME").toString(), (Date) personRow.get("DOB"), personRow.get("GENDER").toString(), personRow.get("BLOOD_GRP").toString(), (long) personRow.get("PHONE_NO"), personRow.get("MAILID").toString(),personRow.get("ADDRESS").toString(),personRow.get("CITY").toString());
				Student student = new Student((long)studentRow.get("STUDENT_ID"), person, dept,   (Date) studentRow.get("DOA"),(int) studentRow.get("YEAR"), studentRow.get("SECTION").toString(),(double) studentRow.get("FEES_DUE"));
				list.add(student);
				System.out.println(student);
				}
			} catch ( DataAccessException e) {
				e.printStackTrace();
				return null;
			}
			return list;
		}	
}
