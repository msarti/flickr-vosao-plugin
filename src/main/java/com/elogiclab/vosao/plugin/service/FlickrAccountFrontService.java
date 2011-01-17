package com.elogiclab.vosao.plugin.service;

import org.vosao.service.ServiceResponse;

public interface FlickrAccountFrontService {
	ServiceResponse connectAccount(String email, String nsid, String username, String fullname, String token);
	ServiceResponse disconnectAccount(String email);
}
