package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import vo.Exam;
import vo.Student;

/**
 * 
 */
public class FileUtil {

    /**
     * Default constructor
     */
    public FileUtil() {
    }


    public static String getExamZipName(Exam exam)            //
    {                                                         //
    	return exam.getName() + "_" + exam.getId()+".zip";    //
    }                                                         //
    
    public static String getxlsName(Exam exam)                      //
    {
    	return exam.getName()+"_" + exam.getId()+".xls";                //
    }                                                                  //
    
    
    public void downLoadPager(String examName, String studentInfo) {
        // TODO implement here
    }
    
    public static String getFilePath() {
    	return ServletActionContext.getServletContext().getRealPath("upload");
    }
    
    public static String getExamPath(Exam exam) {
    	return ServletActionContext.getServletContext().getRealPath("upload")+ "\\" + exam.getName() + "_" + exam.getId();
    }
    
    public static void makeExamPath(Exam exam) {
    	String directory = getExamPath(exam);
		File file = new File(directory);
		if(!file.exists()){
			file.mkdirs();
		}
    }
    
    public static String getExamPaperPath(Exam exam) {
    	return getExamPath(exam) + "\\" + "paper";
    }
    
    public static String getExamPaperFilePath(Exam exam) {
    	return getExamPaperPath(exam) + "\\" + exam.getPaper();
    }
    
    public static String getExamAnswerPath(Exam exam) {
    	return getExamPath(exam) + "\\" + "answer";
    }
    
    public static String getExamAnswerPathByStudent(Exam exam,Student student) {
    	return getExamAnswerPath(exam) + "\\" + student.getNumber() + "_" + student.getName();
    }
    
    // 如果目录存在，先删除文件，否则创建目录
    public static void makeExamPaperPath(Exam exam) {
    	String directory = getExamPaperPath(exam);
		File file = new File(directory);
		if(!file.exists()){
			file.mkdirs();
		}
		
    }
    
    public static void makeExamAnswer(Exam exam) {
    	String directory = getExamAnswerPath(exam);
    	File file = new File(directory);
		if(!file.exists()){
			file.mkdirs();
		}	
    }
    
    public static void deleteExamDirectory(Exam exam) {
    	deleteDirectory(getExamPath(exam));
    }
    

	public static void deleteDirectory(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
			return;
		}
		
		if(file.isFile()){
			file.delete();
		}else if(file.isDirectory()){
			File[] files = file.listFiles();
			for (File myfile : files) {
				deleteDirectory(filePath + "/" + myfile.getName());
			}
			
			file.delete();
		}
	}
	
	public static String getFileType(File file) {
		return file.getName().substring(file.getName().lastIndexOf(".")+1);
	}
    
    public static InputStream getPaperInputStreamByExam(Exam exam) {
    	System.out.println("paperPath: " + getExamPaperFilePath(exam));
    	String targetPath = getExamPaperFilePath(exam);
    	File file = new File(targetPath);  //创建文件  
        FileInputStream fis;
        BufferedInputStream bis;
		try {
			fis = new FileInputStream(file);
			bis=new BufferedInputStream(fis); //创建文件缓冲输入流  
			System.out.println("bis: " + bis);
			return bis;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //创建文件字节输入流  
       
    	return null;
    }
    
    public static String getRightCode(String str) {
    	try {
			return new String(str.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return str;
    }
    

}