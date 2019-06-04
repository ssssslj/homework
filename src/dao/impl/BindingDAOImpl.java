package dao.impl;

import java.util.List;

import dao.BindingDAO;
import vo.Binding;

/**
 * 
 */
public class BindingDAOImpl extends BaseDAOImpl<Binding> implements BindingDAO{

    /**
     * Default constructor
     */
    public BindingDAOImpl() {
    }

	@Override
	public Binding findByIp(String ip) {
		
		List<Binding> list = (List<Binding>) super.find("select b from Binding b where b.ip = ?0",ip);
		if (list != null && list.size() >0) {
			return list.get(0);
		}
		return null;
	}


	

}