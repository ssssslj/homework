package vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "binding")
public class Binding {

	/**
	 * Default constructor
	 */
	public Binding() {
	}

	public Binding(String ip, int eid, Student student) {
		super();
		this.ip = ip;
		this.eid = eid;
		this.student = student;
	}

	/**
	 * 
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // ×ÔÔöid
	private String ip; // ipµØÖ·
	private int eid; // ¿¼ÊÔid

	@OneToOne(targetEntity = Student.class)
	@JoinColumn(name = "sid", referencedColumnName = "id", unique = true)
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

}