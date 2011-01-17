package com.elogiclab.vosao.plugin;

import org.vosao.business.plugin.AbstractPluginEntryPoint;
import org.vosao.service.plugin.PluginServiceManager;

import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDaoImpl;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDaoImpl;
import com.elogiclab.vosao.plugin.service.FlickrAccountBackServiceManager;
import com.elogiclab.vosao.plugin.service.FlickrAccountFrontServiceManager;

public class FlickrEntryPoint extends AbstractPluginEntryPoint {

	private FlickrAccountBackServiceManager flickrAccountBackServiceManager;
	private FlickrAccountFrontServiceManager flickrAccountFrontServiceManager;
	
	private FlickrAccountDao flickrAccountDao;
	private FlickrAccountConfigDao flickrAccountConfigDao;
	
	
	
	
	public String getBundleName() {
		return "com.elogiclab.vosao.flickr.messages";
	}



	public FlickrAccountBackServiceManager getFlickrAccountBackServiceManager() {
		if(flickrAccountBackServiceManager == null) {
			flickrAccountBackServiceManager = new FlickrAccountBackServiceManager(getBusiness(), getFlickrAccountDao(), getFlickrAccountConfigDao());
		}
		return flickrAccountBackServiceManager;
	}


	public FlickrAccountDao getFlickrAccountDao() {
		if(flickrAccountDao == null) {
			flickrAccountDao = new FlickrAccountDaoImpl();
		}
		
		return flickrAccountDao;
	}



	public FlickrAccountConfigDao getFlickrAccountConfigDao() {
		if(flickrAccountConfigDao == null) {
			flickrAccountConfigDao = new FlickrAccountConfigDaoImpl();
		}
		
		return flickrAccountConfigDao;
	}



	@Override
	public PluginServiceManager getPluginBackService() {
		return getFlickrAccountBackServiceManager();
	}



	public FlickrAccountFrontServiceManager getFlickrAccountFrontServiceManager() {
		if(flickrAccountFrontServiceManager == null) {
			flickrAccountFrontServiceManager = new FlickrAccountFrontServiceManager(getBusiness(), getFlickrAccountDao(), getFlickrAccountConfigDao());
		}
		return flickrAccountFrontServiceManager;
	}
	
	
	

}
