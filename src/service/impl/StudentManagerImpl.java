package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ExamDAO;
import dao.StudentDAO;
import service.StudentManager;
import util.ReadExcelUtils;
import vo.Exam;
import vo.Student;

/**
 * 
 */

public class StudentManagerImpl implements StudentManager {

	
	private StudentDAO studentDAO;
	private ExamDAO examDAO;
	
    /**
     * Default constructor
     */
    public StudentManagerImpl() {
    }

    
    
	public ExamDAO getExamDAO() {
		return examDAO;
	}



	public void setExamDAO(ExamDAO examDAO) {
		this.examDAO = examDAO;
	}



	public StudentDAO getStudentDAO() {
		return studentDAO;
	}



	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	
	
	@Override
	public int checkStudent(Student student,Integer examId) {
		List<Student> students = getStudentByNumber(student.getNumber());
		if (students == null) {
			return -1;
		}
		Student rStudent = null;
		for(Student student2 : students) { // 此学生存在与本场考试相关的账号
			if (student2.getExam().getId() == examId) {
				rStudent = student2;
				break;
			}
		}
		if (rStudent == null) {
			return -1;
		}
		// 验证学号与密码是否匹配
		if (!rStudent.getName().equals(student.getName())) {
			return -1;
		}

		return rStudent.getId();
		
	}
	
	 
	@Override
	public List<Student> getStudentByName(String name) {
		return studentDAO.findByName(name);
	}

	public List<Student> getStudentsByClass(String sclass){
		return studentDAO.findByClass(sclass);
	}

	@Override
	public List<Student> getStudentByNumber(String number) {
		// TODO Auto-generated method stub
		return studentDAO.findByNumber(number);
	}
	
	@Override
	public Student getStudentById(Integer id) {
		// TODO Auto-generated method stub
		return studentDAO.get(Student.class, id);
	}
	
	

	

	@Override
	public int updateStudent(Student student) {
		studentDAO.update(student);
		return 1;
	}

	@Override
	public void deleteStudent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllStudents() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int addStudent(Student student, Exam exam) {
		for(Student student2 : exam.getStudents()) {
			if (student2.getNumber().equals(student.getNumber())) {
				return -1;
			}
		}
		student.setExam(exam);
		studentDAO.save(student);
		return 1;
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}



	@Override
	public Student getStudentByIP(String ip) {
		
		return null;
	}

	@Override
	public int getStudentsCount() {
		return 1;
		
	}

	@Override
	public void bindIP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbindIP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importStudents(File file, String fileType) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int checkStudentIp(Student student, String ip) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Student> getStudentsByExamId(Integer examId) {
		
		return examDAO.findStudentsByExamId(examId);
	}



	@Override
	public List<Student> getStudentsLoginedByExamId(Integer examId) {
		List<Student> list = new ArrayList<>();
		list = examDAO.findStudentsLoginedByExamId(examId);
		return list;
	}



	@Override
	public List<Student> getStudentsSummitedByExamId(Integer examId) {
		List<Student> list = new ArrayList<>();
		list = examDAO.findStudentsSummitedByExamId(examId);
		return list;
	}


	@Override
	public int importStudents(Exam exam, File file) {
		
		List<Student> students;
		try {
			students = ReadExcelUtils.readExcel(file);
//			System.out.println("students: " + students);
			Exam exam2 = examDAO.get(Exam.class, exam.getId());
			List<Student> oldStudents = exam2.getStudents();
			System.out.println("oldStudents: " + oldStudents);
			for(Student student : oldStudents) {
				studentDAO.delete(student);
			}
			Set<Student> set = new HashSet<>();
			for(Student student : students) {
				set.add(student);
				student.setExam(exam);
				studentDAO.save(student);
			}
			System.out.println("newStudents: " + students);
			System.out.println("exam: " + exam);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}



	@Override
	public Student getStudentsById(Integer id) {
		
		return null;
	}



	@Override
	public Student getStudentByNumberAndEId(String number, Integer eid) {
		
		return null;
	}



	




}