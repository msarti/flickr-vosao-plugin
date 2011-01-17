package com.elogiclab.vosao.plugin.dao;

import org.vosao.dao.BaseDao;

import com.elogiclab.vosao.plugin.entity.FlickrAccountEntity;

public interface FlickrAccountDao extends BaseDao<FlickrAccountEntity>{
	FlickrAccountEntity getByEmail(String email);
	FlickrAccountEntity getByNsid(String nsid);
	
}
