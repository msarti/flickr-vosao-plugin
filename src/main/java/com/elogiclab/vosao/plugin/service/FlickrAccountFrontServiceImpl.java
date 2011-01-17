package com.elogiclab.vosao.plugin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vosao.business.Business;
import org.vosao.i18n.Messages;
import org.vosao.service.ServiceResponse;

import com.aetrion.flickr.FlickrException;
import com.elogiclab.vosao.plugin.dao.FlickrAccountConfigDao;
import com.elogiclab.vosao.plugin.dao.FlickrAccountDao;
import com.elogiclab.vosao.plugin.entity.FlickrAccountEntity;

public class FlickrAccountFrontServiceImpl extends AbstractFlickrAccountService
		implements FlickrAccountFrontService {
	protected static final Log logger = LogFactory.getLog(FlickrAccountFrontServiceImpl.class);

	public FlickrAccountFrontServiceImpl(Business business,
			FlickrAccountDao aflickrAccountDao , FlickrAccountConfigDao flickrAccountConfigDao) {
		super(business, aflickrAccountDao, flickrAccountConfigDao);
	}

	@Override
	public ServiceResponse connectAccount(String email, String nsid,
			String username, String fullname, String token) {
		
		logger.info("Saving flickr account "+nsid +" to "+email+", token "+token);
		
		try {
			getFlickrApi().getAuthInterface().checkToken(token);
		} catch (FlickrException e) {
			logger.error("Error validating token: "+e.getErrorMessage());
			return ServiceResponse.createErrorResponse(Messages.get(
					e.getErrorMessage()));
		} catch (Exception e) {
			logger.error("Error validating token: "+e.getMessage());
			return ServiceResponse.createErrorResponse(Messages.get(
					e.getMessage()));
		}
		FlickrAccountEntity tmpAccount = getFlickrAccountDao().getByNsid(nsid);
		if(tmpAccount != null && ! email.equals(tmpAccount.getEmail())) {
			return ServiceResponse.createErrorResponse(Messages.get(
					"flickr.account_exists", tmpAccount.getNsid()));
		}
		
		
		FlickrAccountEntity flickrAccount = this.getFlickrAccountDao().getByEmail(email);
		if(flickrAccount == null) {
			flickrAccount = new FlickrAccountEntity();
			flickrAccount.setEmail(email);
		}
		flickrAccount.setNsid(nsid);
		flickrAccount.setUsername(username);
		flickrAccount.setFullname(fullname);
		flickrAccount.setToken(token);
		getFlickrAccountDao().save(flickrAccount);
		
		
		
		
		return ServiceResponse.createSuccessResponse(
				Messages.get("success"));
	}

	@Override
	public ServiceResponse disconnectAccount(String email) {
		FlickrAccountEntity flickrAccount = this.getFlickrAccountDao().getByEmail(email);		
		if(flickrAccount != null) {
			logger.info("Removing Flickr account "+flickrAccount.getNsid()+" from "+email);
			getFlickrAccountDao().remove(flickrAccount.getId());
		} else {
			logger.info("Nothing to do for user "+email);
		}
		return ServiceResponse.createSuccessResponse(
				Messages.get("success"));
	}

}
