package vo;

import java.util.ArrayList;
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
@Table(name="student")
public class Student {

	/**
	 * Default constructor
	 */
	public Student() {
	}
	
	

	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; // 自增id

	private String number;  // 学号

	private String name;  // 姓名

	private String sclass; // 学生所在班级
	
	@OneToOne(targetEntity=Binding.class,mappedBy="student")
	private Binding binding ;
	
	@OneToMany(targetEntity=Answer.class,mappedBy="student")
	private Set<Answer> answers = new HashSet<>();
	
	
	@ManyToOne(targetEntity=Exam.class)
	@JoinColumn(name="eid",referencedColumnName="id",nullable=false)
	private Exam exam; // 
	
	
	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Binding getBinding() {
		return binding;
	}

	public void setBinding(Binding binding) {
		this.binding = binding;
	}

	public List<Answer> getAnswers() {
		
		if (this.answers == null) {
			return null;
		}
		
		List<Answer> list = new ArrayList<>();
		for(Answer answer : this.answers) {
			list.add(answer);
		}
		return list;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	

}