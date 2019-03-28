package com.hawk.web.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hawk.cas.client.security.SecurityMetaDataSource;
import com.hawk.utils.response.api.ReturnData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author: Lucio Zhou
 * @date: 2018/12/16
 * @description:用于对系统某些基础配置进行不停服刷新操作
 */
@Controller
@RequestMapping(value = "/notify")
@Api(value = "AMS账户操作API", tags = "AMS Account Api")
public class RefreshController {
	@Autowired
	private SecurityMetaDataSource securityMetaDataSource;

	/**
	 * @description:用于在不停服的前提下,主动刷新新配置的系统功能权限
	 * @return
	 */
	@ApiOperation(value = "手动刷新系统功能与角色关联权限(不包括角色与组织节点的权限刷新)", notes = "刷新权限,不需要参数")
	@RequestMapping(value = "/refreshAuth", method = RequestMethod.GET)
	@ResponseBody
	public ReturnData refreshAuth() {
		securityMetaDataSource.getAllConfigAttributes();
		return ReturnData.success();
	}

}