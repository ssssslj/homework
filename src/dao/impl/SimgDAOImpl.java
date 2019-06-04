package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.SimgDAO;
import vo.Simg;

public class SimgDAOImpl extends BaseDAOImpl<Simg> implements SimgDAO{

	public SimgDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getimgbynumber(String number) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("select s from Simg s where s.number = ?");
		query.setParameter(0,number);
		Simg simg = (Simg)query.uniqueResult();
		return simg.getImg();
	}

	@Override
	public int addsimg(Simg simg) {
		
		return 0;
	}
	
	
	
}
