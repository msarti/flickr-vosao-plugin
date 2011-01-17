package com.elogiclab.vosao.plugin.service;

import java.util.Map;

import org.vosao.business.Business;
import org.vosao.service.ServiceResponse;

import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDao;
import com.elogiclab.vosao.plugin.entity.FlickrAccountConfigEntity;

public class FlickrAccountBackServiceImpl extends AbstractFlickrAccountService
		implements FlickrAccountBackService {

	public FlickrAccountBackServiceImpl(Business business,
			FlickrAccountDao aflickrAccountDao , FlickrAccountConfigDao flickrAccountConfigDao) {
		super(business, aflickrAccountDao, flickrAccountConfigDao);
	}
	
	
	@Override
	public ServiceResponse saveConfig(Map<String, String> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlickrAccountConfigEntity getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

}
