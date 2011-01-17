package com.elogiclab.vosao.plugin.service;

import org.jabsorb.JSONRPCBridge;
import org.vosao.business.Business;
import org.vosao.service.plugin.AbstractServicePlugin;
import org.vosao.service.plugin.PluginServiceManager;

import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDao;

public class FlickrAccountFrontServiceManager extends AbstractServicePlugin
		implements PluginServiceManager {

	private FlickrAccountFrontService flickrAccountFrontService;
	private FlickrAccountDao flickrAccountDao;
	private FlickrAccountConfigDao flickrAccountConfigDao;

	public FlickrAccountFrontServiceManager(Business business, FlickrAccountDao flickrAccountDao, FlickrAccountConfigDao flickrAccountConfigDao) {
		setBusiness(business);
		this.flickrAccountConfigDao = flickrAccountConfigDao;
		this.flickrAccountDao = flickrAccountDao;
	}
	
	@Override
	public void register(JSONRPCBridge bridge) {
		bridge.registerObject("flickrAccountFrontService", getFlickrAccountFontService());
	}

	@Override
	public void unregister(JSONRPCBridge bridge) {
		bridge.unregisterObject("flickrAccountFrontService");
	}


	public FlickrAccountFrontService getFlickrAccountFontService() {
		if(flickrAccountFrontService == null) {
			flickrAccountFrontService = new FlickrAccountFrontServiceImpl(getBusiness(), flickrAccountDao, flickrAccountConfigDao);
		}
		return flickrAccountFrontService;
	}

}
