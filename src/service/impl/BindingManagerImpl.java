package service.impl;

import java.util.HashSet;
import java.util.Set;

import dao.BindingDAO;
import service.BindingManager;
import vo.Binding;

/**
 * 
 */
public class BindingManagerImpl implements BindingManager {

   private BindingDAO bindingDAO;
	
    public BindingManagerImpl() {
    }

	public BindingDAO getBindingDAO() {
		return bindingDAO;
	}

	public void setBindingDAO(BindingDAO bindingDAO) {
		this.bindingDAO = bindingDAO;
	}
	
	public Binding getBindingByIp(String ip) {
		return bindingDAO.findByIp(ip);
	}

	@Override
	public Set<Binding> getBindingsByIp(String ip) {
		
	    Binding binding = bindingDAO.findByIp(ip);
	    Set<Binding> set = new HashSet<>();
 	    if (binding !=null) {
			set.add(binding);	    	
		}else {
			Binding binding2 = new Binding();
			binding2.setIp(ip);
			set.add(binding2);
		}
		
		return set;
	}

	@Override
	public String getIpByBinding(Set<Binding> set) {
		if (set.size()>0) {
			for(Binding b : set) {
				return b.getIp();
			}
		}
		
		
		return null;
	}

	@Override
	public int addBinding(Binding binding) {
		bindingDAO.save(binding);
		return 1;
	}

	@Override
	public int deleteBinding(Binding binding) {
		bindingDAO.delete(binding);
		return 1;
	}

	@Override
	public Binding getBindingByBindingId(Integer bindingId) {
		// TODO Auto-generated method stub
		return null;
	}

    
}