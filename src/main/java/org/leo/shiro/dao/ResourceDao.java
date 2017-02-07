package org.leo.shiro.dao;

import org.konghao.basic.dao.BaseDao;
import org.leo.shiro.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("resourceDao")
public class ResourceDao extends BaseDao<Resource> implements IResourceDao {

	public List<Resource> listResource() {
		return super.list("from Resource");
	}
	
}
