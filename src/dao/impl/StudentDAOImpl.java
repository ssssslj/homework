package dao.impl;


import java.util.ArrayList;
import java.util.List;

import dao.StudentDAO;
import vo.Exam;
import vo.Student;

/**
 * 
 */
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO {

	/**
	 * Default constructor
	 */
	public StudentDAOImpl() {
	}

	@Override
	public List<Student> findByName(String name) {
		List<Student> stus = find("select s from Student s where s.name = ?0", name);
		if (stus != null && stus.size() >= 1) {
			return stus;
		}
		return null;
	}

	@Override
	public List<Student> findByNumber(String number) {
		List<Student> stus = find("select s from Student s where s.number = ?0", number);
		return stus;
	}

	@Override
	public List<Student> findByClass(String sclass) {
		List<Student> stus = find("select s from Student s where s.sclass = ?0", sclass);
		if (stus != null && stus.size() >= 1) {
			return stus;
		}
		return null;
	}
	
	

}