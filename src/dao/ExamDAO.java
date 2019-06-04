package dao;

import java.util.List;

import vo.Exam;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public interface ExamDAO extends BaseDAO<Exam>{

	public List<Student> findStudentsByExamId(Integer examId);
	
	public List<Student> findStudentsLoginedByExamId(Integer examId);
	
	public List<Student> findStudentsSummitedByExamId(Integer examId);
	
	public List<Exam> findExamsByTeacher(Teacher teacher);
	
	public List<Exam> findAllExams();
}