package org.leo.shiro.dao;

import org.konghao.basic.dao.IBaseDao;
import org.leo.shiro.model.Resource;

import java.util.List;

public interface IResourceDao extends IBaseDao<Resource>{
	public List<Resource> listResource();
}
