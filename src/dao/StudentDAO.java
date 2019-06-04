package dao;

import java.util.List;

import vo.Student;

/**
 * 
 */
public interface StudentDAO extends BaseDAO<Student> {

    public List<Student> findByName(String name);
    
    public List<Student> findByClass(String sclass);
    
    public List<Student> findByNumber(String number);
}