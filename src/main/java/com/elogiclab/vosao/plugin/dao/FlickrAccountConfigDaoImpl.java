package com.elogiclab.vosao.plugin.dao;

import java.util.List;

import org.vosao.dao.BaseDaoImpl;

import com.elogiclab.vosao.plugin.entity.FlickrAccountConfigEntity;

@SuppressWarnings("serial")
public class FlickrAccountConfigDaoImpl 
	extends BaseDaoImpl<FlickrAccountConfigEntity>
	implements FlickrAccountConfigDao {

	public FlickrAccountConfigDaoImpl() {
		super(FlickrAccountConfigEntity.class, "flickr_FlickrAccountConfigEntity");
	}

	@Override
	public FlickrAccountConfigEntity getConfig() {
		List<FlickrAccountConfigEntity> result = select();
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return new FlickrAccountConfigEntity();
	}


}
