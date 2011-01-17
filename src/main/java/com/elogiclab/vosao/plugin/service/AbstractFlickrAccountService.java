package com.elogiclab.vosao.plugin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vosao.business.Business;
import org.vosao.service.plugin.AbstractServicePlugin;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;
import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDao;
import com.elogiclab.vosao.plugin.entity.FlickrAccountConfigEntity;

public class AbstractFlickrAccountService extends AbstractServicePlugin {
	protected static final Log logger = LogFactory.getLog(AbstractFlickrAccountService.class);
	
	private FlickrAccountDao flickrAccountDao;
	private FlickrAccountConfigDao flickrAccountConfigDao;
	private Flickr flickrApi;
	
	public AbstractFlickrAccountService(Business business, FlickrAccountDao aflickrAccountDao, FlickrAccountConfigDao flickrAccountConfigDao) {
		setBusiness(business);
		setFlickrAccountDao(aflickrAccountDao);
		setFlickrAccountConfigDao(flickrAccountConfigDao);
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



	public Flickr getFlickrApi() {
		if(flickrApi == null) {
			try {
				REST transport = new REST();
				FlickrAccountConfigEntity config = getFlickrAccountConfigDao().getConfig();
				String apiKey = "changeme";
				String sharedSecret = "changeme";
				if(config != null) {
					apiKey = config.getApiKey();
					sharedSecret = config.getApiSecret();
				}
				flickrApi = new Flickr(apiKey, sharedSecret, transport);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return flickrApi;
	}



	public void setFlickrApi(Flickr flickrApi) {
		this.flickrApi = flickrApi;
	}




}
