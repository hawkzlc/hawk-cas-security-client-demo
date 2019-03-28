package com.hawk.web.ams.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hawk.cas.client.entity.Role;
import com.hawk.cas.client.entity.User;
import com.hawk.cas.client.service.UserService;
import com.hawk.utils.response.api.ReturnData;
import com.hawk.utils.tool.StringDefaultValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	AmsAccountService amsAccountService;

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		ReturnData userData = amsAccountService.getFullInfoById(Long.valueOf(username));
		logger.info("ReturnData: {}", userData);
		User user = new User();
		Map<String, Object> data = (Map<String, Object>) userData.getData();
		Map<String, Object> account = (Map<String, Object>) data.get("accountInfoMap");
		Map<String, Object> organization = (Map<String, Object>) data.get("organizationInfoMap");
		Map<String, Object> partner = (Map<String, Object>) data.get("partnerInfoMap");
		List<Number> roleIdList = (List<Number>) data.get("roleIdList");
		user.setId(StringDefaultValue.StringValue(account.get("accountId")));
		user.setPassword("");
		user.setUsername(StringDefaultValue.StringValue(account.get("accountName")));
		account.putAll(organization);
		account.putAll(partner);
		user.setAttributes(account);
		List<Role> roles = new ArrayList<>();
		if (roleIdList.isEmpty()) {
			logger.info(">>>>>>>>>>>>>>{}");
		} else {
			for (Number i : roleIdList) {
				logger.info("================{}", i);
				Role tRole = new Role();
				tRole.setId("AUTH_" + i.toString());
				tRole.setRoleName("AUTH_" + i.toString());
				roles.add(tRole);
			}

		}
		logger.info("user's roles: {}", roles);
		user.setRoles(roles);
		return user;
	}

}
