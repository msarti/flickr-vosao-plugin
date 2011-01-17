package com.elogiclab.vosao.plugin.service;

import java.util.Map;

import org.vosao.service.ServiceResponse;

import com.elogiclab.vosao.plugin.entity.FlickrAccountConfigEntity;

public interface FlickrAccountBackService {
	ServiceResponse saveConfig(Map<String, String> vo);
	FlickrAccountConfigEntity getConfig();
}
