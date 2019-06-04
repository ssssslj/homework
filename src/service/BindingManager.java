package service;

import java.util.Set;

import dao.BindingDAO;
import vo.Binding;

/**
 * 
 */
public interface BindingManager {
	
	public Binding getBindingByBindingId(Integer bindingId);

	public Binding getBindingByIp(String ip);// 从数据库查询
	
	public Set<Binding> getBindingsByIp(String ip); // 将ip转换成set集合
	
	public String getIpByBinding(Set<Binding> set);
    
	public int addBinding(Binding binding);
	
	public int deleteBinding(Binding binding);

}