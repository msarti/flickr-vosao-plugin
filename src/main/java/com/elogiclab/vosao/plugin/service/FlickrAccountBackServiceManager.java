package com.elogiclab.vosao.plugin.service;

import org.jabsorb.JSONRPCBridge;
import org.vosao.business.Business;
import org.vosao.service.plugin.AbstractServicePlugin;
import org.vosao.service.plugin.PluginServiceManager;

import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDao;

public class FlickrAccountBackServiceManager  extends AbstractServicePlugin 
implements PluginServiceManager {

	private FlickrAccountBackService flickrAccountBackService;
	private FlickrAccountDao flickrAccountDao;
	private FlickrAccountConfigDao flickrAccountConfigDao;
	
	
	public FlickrAccountBackServiceManager(Business business, FlickrAccountDao flickrAccountDao, FlickrAccountConfigDao flickrAccountConfigDao) {
		setBusiness(business);
		setFlickrAccountConfigDao(flickrAccountConfigDao);
		setFlickrAccountDao(flickrAccountDao);
	}
	
	@Override
	public void register(JSONRPCBridge bridge) {
		bridge.registerObject("flickrAccountBackService", getFlickrAccountBackService());
	}

	@Override
	public void unregister(JSONRPCBridge bridge) {
		bridge.unregisterObject("flickrAccountBackService");
	}

	public FlickrAccountBackService getFlickrAccountBackService() {
		if(flickrAccountBackService == null) {
			flickrAccountBackService = new FlickrAccountBackServiceImpl(getBusiness(), getFlickrAccountDao(), getFlickrAccountConfigDao());
		}
		return flickrAccountBackService;
	}

	public void setFlickrAccountDao(FlickrAccountDao flickrAccountDao) {
		this.flickrAccountDao = flickrAccountDao;
	}

	public FlickrAccountDao getFlickrAccountDao() {
		return flickrAccountDao;
	}

	public void setFlickrAccountConfigDao(FlickrAccountConfigDao flickrAccountConfigDao) {
		this.flickrAccountConfigDao = flickrAccountConfigDao;
	}

	public FlickrAccountConfigDao getFlickrAccountConfigDao() {
		return flickrAccountConfigDao;
	}

}
