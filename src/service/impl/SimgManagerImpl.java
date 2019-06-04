package service.impl;

import dao.SimgDAO;
import service.SimgManager;
import vo.Simg;
import vo.Student;

public class SimgManagerImpl implements SimgManager{

	private SimgDAO simgDAO;
	
	
	

	public SimgDAO getSimgDAO() {
		return simgDAO;
	}

	public void setSimgDAO(SimgDAO simgDAO) {
		this.simgDAO = simgDAO;
	}

	public SimgManagerImpl() {
		
	}
	
	@Override
	public int addsimg(Simg simg) {

		simgDAO.save(simg);
		return 1;
	}

	@Override
	public String getimgbynumber(String number) {
		
		return simgDAO.getimgbynumber(number);
	}

}
