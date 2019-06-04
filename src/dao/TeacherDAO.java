package dao;

import java.util.List;

import vo.Student;
import vo.Teacher;

/**
 * 
 */
public interface TeacherDAO extends BaseDAO<Teacher>{

	public List<Teacher> findByName(String name);
    
    public Teacher findByNumber(String number);
    
    public List<Teacher> findTeacherPage(int pageNo,int pageSize);

}