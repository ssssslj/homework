package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import util.ComparatorUtil;
import vo.Binding;
import vo.Exam;
import vo.Student;

public class TeacherManageAction extends BaseAction {

	private String snumber;
	private String sname;
	private String sclass;
	private String ip;
	private List<Student> studentList;
	private Exam exam;

	private List<Student> student;
	private List<Student> studentLogined;
	private List<Student> studentCommited;
	private List<Student> studentUnlogin;
	private List<Student> studentUnCommited;

	private int nid;
	private String notice;
	private List<String> nlist;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Student> getStudentLogined() {
		return studentLogined;
	}

	public void setStudentLogined(List<Student> studentLogined) {
		this.studentLogined = studentLogined;
	}

	public List<Student> getStudentCommited() {
		return studentCommited;
	}

	public void setStudentCommited(List<Student> studentCommited) {
		this.studentCommited = studentCommited;
	}

	public List<Student> getStudentUnlogin() {
		return studentUnlogin;
	}

	public void setStudentUnlogin(List<Student> studentUnlogin) {
		this.studentUnlogin = studentUnlogin;
	}

	public List<Student> getStudentUnCommited() {
		return studentUnCommited;
	}

	public void setStudentUnCommited(List<Student> studentUnCommited) {
		this.studentUnCommited = studentUnCommited;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public List<String> getNlist() {
		return nlist;
	}

	public void setNlist(List<String> nlist) {
		this.nlist = nlist;
	}

	public String teacherManageStudentPage() {

		return SUCCESS;
	}

	public String teacherManageUnlockPage() {
		return SUCCESS;
	}


	public String getStudentByStudent() {

		Integer examId = (Integer) getApplication().get("examId");
		List<Student> students = new ArrayList<>();
		if (this.snumber != null && !this.snumber.equals("")) {
			students = sManager.getStudentByNumber(this.snumber);
		} else if (this.sname != null && !this.sname.equals("")) {
			students = sManager.getStudentByName(this.sname);
		} else if (this.sclass != null && !this.sclass.equals("")) {
			students = sManager.getStudentsByClass(this.sclass);
		} else {
			students = sManager.getStudentsByExamId(examId);

		}
		// studentList.sort(new ComparatorUtil("id",true));
		if (students == null) { // 未查询到学生
			return SUCCESS;
		}
		this.studentList = new ArrayList<>();
		for (Student student : students) {
			if (student.getExam().getId() == examId) {
				this.studentList.add(student);
			}
		}
		Collections.sort(this.studentList, new ComparatorUtil("number", false));
		// studentList.sort(c);
		// this.studentList = students;
		System.out.println(this.studentList);
		if (studentList == null || studentList.size() < 1) {
			return SUCCESS; // 查询失败
		}
		return SUCCESS;
	}

	public String teacherManageNotifyPage() {

		if (getApplication().get("notify") != null) {
		      this.nlist = ((List)getApplication().get("notify"));
		    } else {
		      this.nlist = new ArrayList();
		    }
		
		return SUCCESS;
	}

	
	public String teacherManageNotifyAddPage() {
		if (getApplication().get("notify") != null) {
			this.nlist = ((List) this.getApplication().get("notify"));
		} else {
			this.nlist = new ArrayList<String>();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (this.notice == null && this.notice.trim().equals("")) {
			return SUCCESS;
		}
		System.out.println("notice:" + this.notice);
		this.nlist.add("[ " + dateFormat.format(new Date()) + " ]    " + this.notice);
		this.getApplication().put("notify", this.nlist);
		//this.getApplication().put("try", "try");
		return SUCCESS;
	}

	public String teacherManageNotifyDeletePage() {
		
		if (getApplication().get("notify") != null) {
			this.nlist = ((List) this.getApplication().get("notify"));
			this.nlist.remove(this.nid);
			this.getApplication().put("notify", this.nlist);
		} else {
			this.nlist = new ArrayList<String>();
		}
		return SUCCESS;
	}

	public String getStudentByStudent2() {
		Integer examId = (Integer) getApplication().get("examId");

		if (exam != null) {
			examId = exam.getId();
		}

		List<Student> students = new ArrayList<>();
		if (this.snumber != null && !this.snumber.equals("")) {
			students = sManager.getStudentByNumber(this.snumber);
		} else if (this.sname != null && !this.sname.equals("")) {
			students = sManager.getStudentByName(this.sname);
		} else if (this.sclass != null && !this.sclass.equals("")) {
			students = sManager.getStudentsByClass(this.sclass);
		} else {
			students = sManager.getStudentsByExamId(examId);

		}
		// studentList.sort(new ComparatorUtil("id",true));

		this.studentList = new ArrayList<>();
		if (students == null) { // 未查询到学生
			return SUCCESS;
		}
		for (Student student : students) {
			if (student.getExam().getId() == examId) {
				this.studentList.add(student);
			}
		}
		Collections.sort(this.studentList, new ComparatorUtil("number", false));
		// studentList.sort(c);
		// this.studentList = students;
		System.out.println(this.studentList);
		if (studentList == null || studentList.size() < 1) {
			return SUCCESS; // 查询失败
		}
		return SUCCESS;
	}

	public String addStudent() {

		Student student = new Student();
		System.out.println("snumber: " + this.snumber);
		student.setNumber(this.snumber);
		student.setName(this.sname);
		student.setSclass(this.sclass);

		Integer examId = (Integer) getApplication().get("examId");

		if (exam != null) {
			examId = exam.getId();
		}

		int result = sManager.addStudent(student, eManager.getExam(examId));

		if (result < 0) {
			addActionMessage("此学生已存在");
		}

		return SUCCESS;
	}

	public String getStudentByip() {
		if (this.ip != null && !this.ip.equals("")) {
			Binding binding = bManager.getBindingByIp(this.ip);
			if (binding != null) {
				this.studentList = new ArrayList<>();
				this.studentList.add(binding.getStudent());
				return SUCCESS;
			}

		}

		// Student student = sManager.getStudentByIP(this.ip);
		// this.studentList = new ArrayList<>();
		// this.studentList.add(student);
		return SUCCESS;
	}

	public String deleteBinding() {
		Binding binding = bManager.getBindingByIp(this.ip);
		bManager.deleteBinding(binding);
		return SUCCESS;
	}

	public List<Student> getallstudent() {
		return sManager.getStudentsByExamId((Integer) getApplication().get("examId"));
	}

	public List<Student> getBindStudent() {
		return studentLogined = sManager.getStudentsLoginedByExamId((Integer) getApplication().get("examId"));
	}

	public List<Student> getUnbindStudent() {
		List<Student> allstu = sManager.getStudentsByExamId((Integer) getApplication().get("examId"));
		List<Student> logstu = sManager.getStudentsLoginedByExamId((Integer) getApplication().get("examId"));
		List<Student> tstudnetunlogin = new ArrayList<Student>();
		for (Student ss : allstu) {
			if (!logstu.contains(ss)) {
				tstudnetunlogin.add(ss);
			}
		}
		return tstudnetunlogin;
	}

	public List<Student> getSubmitStudent() {
		return studentCommited = sManager.getStudentsSummitedByExamId((Integer) getApplication().get("examId"));
	}

	public List<Student> getUnSubmitStudent() {
		List<Student> loginstu = sManager.getStudentsLoginedByExamId((Integer) getApplication().get("examId"));
		List<Student> summitstu = sManager.getStudentsSummitedByExamId((Integer) getApplication().get("examId"));
		List<Student> tstudnetunsummit = new ArrayList<Student>();
		for (Student ss : loginstu) {
			if (!summitstu.contains(ss)) {
				tstudnetunsummit.add(ss);
			}

		}
		return tstudnetunsummit;
	}

	public String teacherManageSummaryPage() {
		this.student = getallstudent();
		this.studentLogined = getBindStudent();
		this.studentUnlogin = getUnbindStudent();
		this.studentCommited = getSubmitStudent();
		this.studentUnCommited = getUnSubmitStudent();
		return SUCCESS;
	}

	public String teacherManageShowbindPage() {
		this.studentLogined = getBindStudent();
		Collections.sort(studentLogined, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return SUCCESS;
	}

	public String teacherManageShowUnbindPage() {

		this.studentUnlogin = getUnbindStudent();
		Collections.sort(studentUnlogin, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return SUCCESS;
	}

	public String teacherManageShowSubmitPage() {
		this.studentCommited = getSubmitStudent();
		Collections.sort(studentCommited, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return SUCCESS;
	}

	public String teacherManageShowUnsubmitPage() {
		this.studentUnCommited = getUnSubmitStudent();
		Collections.sort(studentUnCommited, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return SUCCESS;
	}

}
