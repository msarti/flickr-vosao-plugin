package com.elogiclab.vosao.plugin.dao;

import org.vosao.dao.BaseDaoImpl;

import com.elogiclab.vosao.plugin.entity.FlickrAccountEntity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class FlickrAccountDaoImpl extends BaseDaoImpl<FlickrAccountEntity> implements
		FlickrAccountDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public FlickrAccountDaoImpl() {
		super(FlickrAccountEntity.class, "flickr_FlickrAccountEntity");
	}
	
	
	@Override
	public FlickrAccountEntity getByEmail(String email) {
		Query q = newQuery();
		q.addFilter("email", FilterOperator.EQUAL, email);
		return selectOne(q, "getByEmail", params(email));
	}


	@Override
	public FlickrAccountEntity getByNsid(String nsid) {
		Query q = newQuery();
		q.addFilter("nsid", FilterOperator.EQUAL, nsid);
		return selectOne(q, "getByNsid", params(nsid));
	}

}
