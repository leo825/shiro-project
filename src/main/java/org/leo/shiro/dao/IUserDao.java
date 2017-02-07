package org.leo.shiro.dao;

import org.konghao.basic.dao.IBaseDao;
import org.leo.shiro.model.Resource;
import org.leo.shiro.model.Role;
import org.leo.shiro.model.User;

import java.util.List;

public interface IUserDao extends IBaseDao<User> {
	public List<User> listUser();
	
	public User loadByUsername(String username);
	
	public List<User> listByRole(int id);
	
	public List<Resource> listAllResource(int uid);
	
	public List<String> listRoleSnByUser(int uid);
	
	public List<Role> listUserRole(int uid);
	
}
