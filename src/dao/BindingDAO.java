package dao;

import vo.Binding;

/**
 * 
 */
public interface BindingDAO extends BaseDAO<Binding> {

    public Binding findByIp(String ip);
    

}