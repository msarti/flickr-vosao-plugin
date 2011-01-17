package com.elogiclab.vosao.plugin.entity;

import static org.vosao.utils.EntityUtil.getStringProperty;
import static org.vosao.utils.EntityUtil.setProperty;

import org.vosao.entity.BaseEntityImpl;

import com.google.appengine.api.datastore.Entity;

public class FlickrAccountConfigEntity extends BaseEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String apiKey;
	private String apiSecret;
	
	@Override
	public void load(Entity entity) {
		super.load(entity);
		apiKey = getStringProperty(entity, "apiKey");
		apiSecret = getStringProperty(entity, "apiSecret");
	}
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "apiKey", apiKey, false);
		setProperty(entity, "apiSecret", apiSecret, false);
	}	
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

}
