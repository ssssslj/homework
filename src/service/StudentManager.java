package service;

import java.io.File;
import java.util.List;

import vo.Exam;
import vo.Student;

/**
 * 
 */
public interface StudentManager {




    /**
     * 
     * @param student
     * @return studentµÄid
     */
    public int checkStudent(Student student,Integer examId);
    
    public int checkStudentIp(Student student,String ip);

    /**
     * 
     */
    public void importStudents(File file,String fileType);
    
    public int importStudents(Exam exam,File file);

    public int addStudent(Student student,Exam exam);

    public int updateStudent(Student student) ;

    /**
     * 
     */
    public void deleteStudent();

    /**
     * 
     */
    public void getAllStudents();

    /**
     * 
     */
    public List<Student> getStudentByName(String name);

    
    public List<Student> getStudentsByExamId(Integer examId);
	
	public List<Student> getStudentsLoginedByExamId(Integer examId);
	
	public List<Student> getStudentsSummitedByExamId(Integer examId);
	
   
    public List<Student> getStudentByNumber(String number);
    
    public Student getStudentByNumberAndEId(String number,Integer eid);

    public Student getStudentByIP(String ip) ;
    
    public Student getStudentById(Integer id);
    public List<Student> getStudentsByClass(String sclass);
    
    public Student getStudentsById(Integer id);

    /**
     * 
     */
    public int getStudentsCount() ;


    /**
     * 
     */
    public void bindIP() ;

    /**
     * 
     */
    public void unbindIP();

}