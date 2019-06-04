package vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="exam")
public class Exam {

	/**
	 * Default constructor
	 */
	public Exam() {
	}
	
	

	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  // 自增id

	private String name; //考试名称
	
	private String paper; // 考试试卷

	private Date startTime; // 考试开始时间

	private Date endTime; // 考试结束时间	
	
	@Column(columnDefinition="default true") 
	private Boolean autoStart; // 是否自动开始

	private Boolean started; // 是否已经开始

	private Boolean finished; // 是否已经结束

	private Boolean archived; // 是否已经归档

	private Boolean cleaned; // 是否已经清理
	
	//定义该Exam关联的Teacher实体
	@ManyToOne(targetEntity=Teacher.class)
	//定义名为creater的外键列，该外键列引用teacher表的id列
	@JoinColumn(name="creater",referencedColumnName="id",nullable=false)
	private Teacher teacher; // 
	
	@OneToMany(targetEntity=Student.class,mappedBy="exam")
	private Set<Student> students = new HashSet<>();
	
//	@OneToMany(targetEntity=Binding.class)
//	private Set<Binding> bindings = new HashSet<>();
//	
//	@OneToMany(targetEntity=Answer.class)
//	private Set<Answer> answers = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getAutoStart() {
		return autoStart;
	}

	public void setAutoStart(Boolean autoStart) {
		this.autoStart = autoStart;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Boolean getCleaned() {
		return cleaned;
	}

	public void setCleaned(Boolean cleaned) {
		this.cleaned = cleaned;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		
		if (this.students == null) {
			return null;
		}
		
		List<Student> list = new ArrayList<>();
		for(Student direction : this.students) {
			list.add(direction);
		}
		return list;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

//	public Set<Binding> getBindings() {
//		return bindings;
//	}
//
//	public void setBindings(Set<Binding> bindings) {
//		this.bindings = bindings;
//	}
//
//	public Set<Answer> getAnswers() {
//		return answers;
//	}
//
//	public void setAnswers(Set<Answer> answers) {
//		this.answers = answers;
//	}


	



	

	

	

}