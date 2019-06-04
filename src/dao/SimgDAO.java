package dao;

import vo.Simg;

public interface SimgDAO extends BaseDAO<Simg>{
	
	public String getimgbynumber(String number);
	
	public int addsimg(Simg simg);
}
