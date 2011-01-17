package com.elogiclab.vosao.plugin.entity;

import static org.vosao.utils.EntityUtil.getStringProperty;
import static org.vosao.utils.EntityUtil.setProperty;

import org.vosao.entity.BaseEntityImpl;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class FlickrAccountEntity extends BaseEntityImpl {
	private String email;
	private String nsid;
	private String username;
	private String fullname;
	private String token;
	
	@Override
		public void load(Entity entity) {
		super.load(entity);
		email = getStringProperty(entity, "email");
		nsid = getStringProperty(entity, "nsid");
		username = getStringProperty(entity, "username");
		fullname = getStringProperty(entity, "fullname");
		token = getStringProperty(entity, "token");
	}	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "email", email, true);
		setProperty(entity, "nsid", nsid, true);
		setProperty(entity, "username", username, false);
		setProperty(entity, "fullname", fullname, false);
		setProperty(entity, "token", token, false);
	}	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String name) {
		this.email = name;
	}
	public String getNsid() {
		return nsid;
	}
	public void setNsid(String nsid) {
		this.nsid = nsid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	
	
}
