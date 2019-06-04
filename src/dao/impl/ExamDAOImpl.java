package dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dao.ExamDAO;
import vo.Exam;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public class ExamDAOImpl extends BaseDAOImpl<Exam> implements ExamDAO {

    /**
     * Default constructor
     */
    public ExamDAOImpl() {
    }

    
    public List<Student> findStudentsByExamId(Integer examId){
    	List<Student> list = new ArrayList<>();
    	List<Student> set = get(Exam.class, examId).getStudents();
    	if (set == null) {
			return null;
		}
    	for(Student student : set) {
    		list.add(student);
    	}
    	return list;
    }
    
    public List<Student> findStudentsLoginedByExamId(Integer examId){
    	List<Student> list = findStudentsByExamId(examId);
    	if (list == null) {
			return null;
		}
    	List<Student> loginedStudents = new ArrayList<>();
    	for(Student student : list) {
    		if (student.getBinding() != null) {
				loginedStudents.add(student);
			}
    	}
    	
    	return loginedStudents;
    }
    
    public List<Student> findStudentsSummitedByExamId(Integer examId){
    	List<Student> list = findStudentsLoginedByExamId(examId);
    	if (list == null) {
			return null;
		}
    	List<Student> SummitedStudents = new ArrayList<>();
    	for(Student student : list) {
    		System.out.println("student: " + student.getId() + " "
    				+ student.getNumber() + " " + "answers: " + 
    				student.getAnswers() + " ");
    		if (student.getAnswers() != null && student.getAnswers().size() > 0) {
    			
    			SummitedStudents.add(student);
			}
    	}
    	return SummitedStudents;
    }


	@Override
	public List<Exam> findExamsByTeacher(Teacher teacher) {
		List<Exam> list = find("select e from Exam e where e.teacher = ?0", teacher);
		return list;
	}


	@Override
	public List<Exam> findAllExams() {
		return findAll(Exam.class);
	}

}