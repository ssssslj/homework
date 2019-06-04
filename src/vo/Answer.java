package vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="answer")
public class Answer {

	/**
	 * Default constructor
	 */
	public Answer() {
	}


	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; // 自增id
	
	private int eid; // 考试id

	private String fileName; // 文件名称

	private long fileSize; // 文件大小

	private Date submitTime; // 提交时间
	

	@ManyToOne(targetEntity=Student.class) 
	@JoinColumn(name="sid",referencedColumnName="id",nullable=false)
	private Student student; // 
	
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getEid() {
		return eid;
	}



	public void setEid(int eid) {
		this.eid = eid;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public long getFileSize() {
		return fileSize;
	}



	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}



	public Date getSubmitTime() {
		return submitTime;
	}



	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}



}