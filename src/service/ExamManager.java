package service;

import java.io.File;
import java.util.List;

import vo.Exam;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public interface ExamManager {


	public Exam getExam(int id);

    public void getPaper(String examName);

    public void setConfigure();

    public void addExam(Exam exam) throws Exception;

    public void updateExam(Exam exam,Integer teacherId);
    
    public int updateExam(Exam exam);

    public List<Exam> getAllExams();
    
    public List<Exam> getExamsByTeacherId(Integer teacherId);

    public void uploadExamPaper(File paper,String paperName);
    
    public int uploadExamPaper(Exam exam,File file);
    
    public int downloadExamPaper(Exam exam);
    
    public int uploadExamAnswer(Exam exam,Student student,File file,String fileName);

    public int startExam(Integer examId);

    public int stopExam(Integer examId);

    public int cleanExam(Integer examId);

    public int deleteExam(Integer examId);

    public void sendMessage() ;

    public void getAnswers();

    public void getSubmitInfo();

}