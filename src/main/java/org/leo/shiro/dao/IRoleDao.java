package org.leo.shiro.dao;

import java.util.List;

import org.konghao.basic.dao.IBaseDao;
import org.leo.shiro.model.Resource;
import org.leo.shiro.model.Role;
import org.leo.shiro.model.RoleResource;
import org.leo.shiro.model.UserRole;

public interface IRoleDao extends IBaseDao<Role>{
	public List<Role> listRole();
	
	public UserRole loadUserRole(int uid, int roleId);
	
	public void addUserRole(int uid, int roleId);
	
	public void deleteUserRole(int uid, int roleId);
	
	/**
	 * 删除某个用户的所有角色
	 * @param uid
	 */
	public void deleteUserRoles(int uid);
	/**
	 * 根据角色id获取可以访问的所有资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> listRoleResource(int roleId);
	
	public void addRoleResource(int roleId, int resId);
	
	public void deleteRoleResource(int roleId, int resId);
	
	public RoleResource loadResourceRole(int roleId, int resId);
}
