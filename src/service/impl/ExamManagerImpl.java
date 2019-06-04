package service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileUpload;
import org.apache.struts2.ServletActionContext;

import dao.AnswerDAO;
import dao.BindingDAO;
import dao.ExamDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import service.ExamManager;
import util.FileDownloadUtil;
import util.FileUploadUtil;
import util.FileUtil;
import vo.Answer;
import vo.Binding;
import vo.Exam;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public class ExamManagerImpl implements ExamManager{

	private ExamDAO examDAO;
	private TeacherDAO teacherDAO;
	private AnswerDAO answerDAO;
	private StudentDAO studentDAO;
	private BindingDAO bindingDAO;
	
    /**
     * Default constructor
     */
    public ExamManagerImpl() {
    	
    }
    
    

	public ExamDAO getExamDAO() {
		return examDAO;
	}



	public void setExamDAO(ExamDAO examDAO) {
		this.examDAO = examDAO;
	}



	public BindingDAO getBindingDAO() {
		return bindingDAO;
	}



	public void setBindingDAO(BindingDAO bindingDAO) {
		this.bindingDAO = bindingDAO;
	}



	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}



	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}



	public StudentDAO getStudentDAO() {
		return studentDAO;
	}



	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}



	public AnswerDAO getAnswerDAO() {
		return answerDAO;
	}



	public void setAnswerDAO(AnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}



	@Override
	public void getPaper(String examName) {
		// TODO Auto-generated method stub
		
	}


	

	@Override
	public void setConfigure() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Exam getExam(int id) {
		return examDAO.get(Exam.class, id);
	}

	@Override
	public void addExam(Exam exam) throws Exception {
		examDAO.save(exam);
	}

	

	@Override
	public void updateExam(Exam exam, Integer teacherId) {
		Teacher teacher = teacherDAO.get(Teacher.class, teacherId);
		if (teacher==null) {
			//throw new Exception();
		}
		exam.setTeacher(teacher);
		examDAO.update(exam);
		
	}
	
	public int updateExam(Exam exam) {
		examDAO.update(exam);
		return 1;
	}
	
	@Override
	public void uploadExamPaper(File paper, String paperName) {
		String realPath = FileUtil.getFilePath();
		
		FileUploadUtil.fileUpload(paper, paperName, realPath);
		
	}
	

	@Override
	public int uploadExamPaper(Exam exam, File file) {
		FileUtil.makeExamPaperPath(exam);
		String realPath = FileUtil.getExamPaperPath(exam);
		
		FileUploadUtil.fileUpload(file, exam.getPaper(), realPath);
		
		this.examDAO.update(exam);
		return 1;
	}
	
	@Override
	public int downloadExamPaper(Exam exam) {
		String realPath = FileUtil.getExamPaperPath(exam);
		try {
			FileDownloadUtil.downloadFile(ServletActionContext.getResponse(), exam.getPaper(), realPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	public int uploadExamAnswer(Exam exam, Student student,File file,String fileName) {
		FileUtil.makeExamPaperPath(exam);
		String realPath = FileUtil.getExamAnswerPathByStudent(exam, student);
		System.out.println("fileName: " + file.getName());
		Answer answer = new Answer();
		answer.setStudent(student);
		
		answer.setFileName(fileName);
		answer.setFileSize(file.length());
		answer.setSubmitTime(new Date());
		System.out.println("file: " + file + " fileName: " + fileName + " " + realPath);
		FileUploadUtil.fileUpload(file,fileName, realPath);
		answerDAO.save(answer);
		
		return 1;
	}

	@Override
	public List<Exam> getExamsByTeacherId(Integer teacherId) {

		return examDAO.findExamsByTeacher(teacherDAO.get(Teacher.class, teacherId));
	}


	@Override
	public int startExam(Integer examId) {
		Exam exam = examDAO.get(Exam.class, examId);
		exam.setStarted(true);
		exam.setFinished(false);
		examDAO.update(exam); // 更新考试状态，需要启动定时器
		return 1;
	}

	@Override
	public int stopExam(Integer examId) {
		Exam exam = examDAO.get(Exam.class, examId);
		exam.setStarted(false);
		exam.setFinished(true);
		
		List<Student> students = exam.getStudents();
		
		for(Student student : students) {
			Binding binding = student.getBinding();
			if (binding!=null) {
				bindingDAO.delete(binding);
			}
		}
		
		examDAO.update(exam); // 更新考试状态，需要启动定时器
		return 1;
		
	}
	

	@Override
	public int cleanExam(Integer examId) {
		Exam exam = examDAO.get(Exam.class, examId);
		exam.setCleaned(true);
		examDAO.update(exam); 
		FileUtil.deleteExamDirectory(exam);
		return 1;
	}
	
	
	@Override
	public int deleteExam(Integer examId) {
		Exam exam = examDAO.get(Exam.class, examId);
		List<Student> students = exam.getStudents();
		
		for(Student student : students) {
			List<Answer> answers = student.getAnswers();
			for(Answer answer : answers) {
				answerDAO.delete(answer);
			}
			studentDAO.delete(student);
		}
		
		examDAO.delete(exam);
		FileUtil.deleteExamDirectory(exam);
		return 1;
	}

	@Override
	public List<Exam> getAllExams() {
		
		return examDAO.findAllExams();
	}



	@Override
	public void sendMessage() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void getAnswers() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void getSubmitInfo() {
		// TODO Auto-generated method stub
		
	}



	



	






	

}