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
	private int id;  // ����id

	private String name; //��������
	
	private String paper; // �����Ծ�

	private Date startTime; // ���Կ�ʼʱ��

	private Date endTime; // ���Խ���ʱ��	
	
	@Column(columnDefinition="default true") 
	private Boolean autoStart; // �Ƿ��Զ���ʼ

	private Boolean started; // �Ƿ��Ѿ���ʼ

	private Boolean finished; // �Ƿ��Ѿ�����

	private Boolean archived; // �Ƿ��Ѿ��鵵

	private Boolean cleaned; // �Ƿ��Ѿ�����
	
	//�����Exam������Teacherʵ��
	@ManyToOne(targetEntity=Teacher.class)
	//������Ϊcreater������У������������teacher���id��
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