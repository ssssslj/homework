package service.impl;

import java.util.List;

import bean.PageBean;
import dao.TeacherDAO;
import service.TeacherManager;
import util.EncryptUtil;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public class TeacherManagerImpl implements TeacherManager{

	private TeacherDAO teacherDAO;
    
    public TeacherManagerImpl() {
    }
    
    

    public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}



	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}



	@Override
	public int checkTeacher(Teacher teacher) {
		
		Teacher teacher2 = this.teacherDAO.findByNumber(teacher.getNumber());
		
		if (teacher2!=null && EncryptUtil.checkPassword(teacher.getPassword(), teacher2.getPassword())) {
			return teacher2.getId();
		}
		return -1;
	}
	
	@Override
	public int checkAdmin(Teacher admin) {
		Teacher admin2 = this.teacherDAO.findByNumber(admin.getNumber());
		if (admin2!=null && admin2.isAdmin() &&
				EncryptUtil.checkPassword(admin.getPassword(), admin2.getPassword())) {
			return admin2.getId();
		}
		return -1;
	}

	
	@Override
	public int addTeacher(Teacher teacher) {
//		try {
//			teacherDAO.save(teacher);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("教师添加失败");
//		}
		teacherDAO.save(teacher);
		return 1;
	}

	@Override
	public void deleteTeacher(Teacher teacher) {
		teacherDAO.delete(teacher);
		
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		teacherDAO.update(teacher);
		return 1;
	}

	@Override
	public List<Teacher> getTeachers() {
		
		return teacherDAO.findAll(Teacher.class);

	}

	@Override
	public Teacher getTeacher(Integer id) {
		return teacherDAO.get(Teacher.class, id);
	}



	@Override
	public PageBean<Teacher> getTeacherPage(int page) {
		PageBean<Teacher> pageBean = new PageBean<>();
	     //设置当前页数；
	     pageBean.setPage(page);
	     //设置每页显示记录数
	      int pageSize= pageBean.getPageSize();
	      //设置总记录条数
	      int totalCount=(int)teacherDAO.findCount(Teacher.class);
	      pageBean.setTotalCount(totalCount);
	      //设置总页数
	      int totalPage=0;
	      if(totalCount % pageSize==0){
		   totalPage=totalCount/pageSize;
	      }else{
		    totalPage=totalCount/pageSize+1;
	      }
	       pageBean.setTotalPage(totalPage);

	       List<Teacher> list=teacherDAO.findTeacherPage(page, pageSize);
	       pageBean.setList(list);
	       return pageBean;

	}

	

	


	

}