package dao.impl;


import java.util.List;

import dao.TeacherDAO;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public class TeacherDAOImpl extends BaseDAOImpl<Teacher> implements TeacherDAO {

    /**
     * Default constructor
     */
    public TeacherDAOImpl() {
    }

	@Override
	public List<Teacher> findByName(String name) {
		List<Teacher> teachers = find("select t from Teacher t where t.name = ?0", name);
		if (teachers != null && teachers.size() >= 1) {
			return teachers;
		}
		return null;
	}

	@Override
	public Teacher findByNumber(String number) {
		List<Teacher> teachers = find("select t from Teacher t where t.number = ?0", number);
		if (teachers != null && teachers.size() >= 1) {
			return teachers.get(0);
		}
		return null;
	}

	@Override
	public List<Teacher> findTeacherPage(int pageNo, int pageSize) {
		
		return findEntrysByPage(Teacher.class, pageNo, pageSize);
	}
	
	

}