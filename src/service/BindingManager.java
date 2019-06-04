package service;

import java.util.Set;

import dao.BindingDAO;
import vo.Binding;

/**
 * 
 */
public interface BindingManager {
	
	public Binding getBindingByBindingId(Integer bindingId);

	public Binding getBindingByIp(String ip);// �����ݿ��ѯ
	
	public Set<Binding> getBindingsByIp(String ip); // ��ipת����set����
	
	public String getIpByBinding(Set<Binding> set);
    
	public int addBinding(Binding binding);
	
	public int deleteBinding(Binding binding);

}