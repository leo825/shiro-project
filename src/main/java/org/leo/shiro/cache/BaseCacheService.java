package org.leo.shiro.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import javax.inject.Inject;

public class BaseCacheService implements InitializingBean {

	@Inject
	private EhCacheCacheManager myCacheManager;
	private Cache cache;
	private String cacheName;

	@Override
	public void afterPropertiesSet() throws Exception {
		cache = myCacheManager.getCache(cacheName);
	}

	public void setMyCacheManager(EhCacheCacheManager ehCacheCacheManager) {
		this.myCacheManager = ehCacheCacheManager;
	}

	public void setCache(EhCacheCache cache) {
		this.cache = cache;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public void clear() {
		cache.clear();
	}

	public void put(String key,Object value) {
		cache.put(key, value);
	}

	public void evict(String key) {
		cache.evict(key);
	}

	public Object get(String key) {
		ValueWrapper vw = cache.get(key);
		if(vw==null) return null;
		return vw.get();
	}
}
