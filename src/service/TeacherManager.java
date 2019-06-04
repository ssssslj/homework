package service;

import java.util.List;

import bean.PageBean;
import vo.Teacher;

/**
 * 
 */
public interface TeacherManager {

    /**
     * 
     */
    public int checkTeacher(Teacher teacher) ;
    
    public int checkAdmin(Teacher admin);

    /**
     * 
     */
    public int addTeacher(Teacher teacher) ;

    /**
     * 
     */
    public void deleteTeacher(Teacher teacher) ;

    /**
     * 
     */
    public int updateTeacher(Teacher teacher) ;

    /**
     * 
     */
    public List<Teacher> getTeachers();
    
    public Teacher getTeacher(Integer id);
    
    public PageBean<Teacher> getTeacherPage(int page);

}