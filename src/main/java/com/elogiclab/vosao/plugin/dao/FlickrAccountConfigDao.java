package com.elogiclab.vosao.plugin.dao;

import org.vosao.dao.BaseDao;

import com.elogiclab.vosao.plugin.entity.FlickrAccountConfigEntity;

public interface FlickrAccountConfigDao extends BaseDao<FlickrAccountConfigEntity> {
	FlickrAccountConfigEntity getConfig();
}
