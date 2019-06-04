package vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * 
 */
@Entity
@Table(name = "teacher")

public class Teacher {

	/**
	 * Default constructor
	 */
	public Teacher() {
	}
	
	

	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; // ����id,����

	private String number; // ��ʦ����

	private String name; // ��ʦ����

	private String password; // ����

	private boolean admin; // �Ƿ�Ϊ����Ա
	
	//�����Teacherʵ�����й�����Examʵ��
	// ָ��mappedBy���Ա�����Teacherʵ�岻���ƹ�����ϵ��˫��1-N��ϵ��
	@OneToMany(targetEntity=Exam.class,mappedBy="teacher")
	private Set<Exam> exams = new HashSet<>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}



	

}