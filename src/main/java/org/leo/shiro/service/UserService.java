package org.leo.shiro.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.leo.shiro.dao.IRoleDao;
import org.leo.shiro.dao.IUserDao;
import org.leo.shiro.kit.ShiroKit;
import org.leo.shiro.model.Resource;
import org.leo.shiro.model.Role;
import org.leo.shiro.model.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {
	@Inject
	private IUserDao userDao;
	@Inject
	private IRoleDao roleDao;
	
	public User add(User user) {
		if(ShiroKit.isEmpty(user.getUsername())||ShiroKit.isEmpty(user.getPassword())) {
			throw new RuntimeException("用户名或者密码不能为空！");
		}
		user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
		userDao.add(user);
		return user;
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public User update(User user,List<Integer> rids) {
		roleDao.deleteUserRoles(user.getId());
		for(int rid:rids) {
			roleDao.addUserRole(user.getId(), rid);
		}
		userDao.update(user);
		return user;
	}
	
	public User update(User user) {
		userDao.update(user);
		return user;
	}

	public User load(int id) {
		return userDao.load(id);
	}

	public User loadByUsername(String username) {
		return userDao.loadByUsername(username);
	}

	public User login(String username, String password) {
		User u = userDao.loadByUsername(username);
		if(u==null) throw new UnknownAccountException("用户名或者密码出错");
		if(!u.getPassword().equals(ShiroKit.md5(password, username)))
			throw new IncorrectCredentialsException("用户名或者密码出错");
		if(u.getStatus()==0) throw new LockedAccountException("用户已经被锁定");
		return u;
	}

	public List<User> list() {
		return userDao.listUser();
	}

	public List<User> listByRole(int id) {
		return userDao.listByRole(id);
	}

	public List<Resource> listAllResource(int uid) {
		return userDao.listAllResource(uid);
	}

	public User add(User user, List<Integer> rids) {
		this.add(user);
		for(int rid:rids) {
			roleDao.addUserRole(user.getId(), rid);
		}
		return user;
	}

	@Override
	public List<String> listRoleSnByUser(int uid) {
		return userDao.listRoleSnByUser(uid);
	}

	@Override
	public List<Role> listUserRole(int uid) {
		return userDao.listUserRole(uid);
	}

}
