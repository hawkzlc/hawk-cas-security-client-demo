package com.hawk.web.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hawk.cas.client.entity.Resource;
import com.hawk.cas.client.service.ResourceService;
import com.hawk.utils.response.api.ReturnData;
import com.hawk.utils.tool.StringDefaultValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	AuthService authService;
	@Value("${hawk.moduleid}")
	Long moduleId;

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAll() {

		List<Resource> resources = new ArrayList<Resource>();
		ReturnData returnData = authService.moduleGetAllFunctionsById(moduleId);
		List<Object> rList = (List<Object>) returnData.getData();
		Map<String, Object> temp = new HashMap<>();
		if (rList.isEmpty()) {
			;
		} else {
			for (Object i : rList) {
				temp = (Map<String, Object>) i;
				Resource resource = new Resource();
				resource.setId(StringDefaultValue.StringValue(temp.get("funcId")));
				resource.setUrl(StringDefaultValue.StringValue(temp.get("funcUrl")));
				resource.setResMethod(StringDefaultValue.StringValue(temp.get("funcMd5")));
				resource.setModuleId(StringDefaultValue.StringValue(temp.get("moduleId")));
				resource.setResIcon(StringDefaultValue.StringValue(temp.get("funcIco")));
				resource.setResLevel(StringDefaultValue.intValue(temp.get("funcLevel")));
				resource.setResName(StringDefaultValue.StringValue(temp.get("name")));
				resource.setResType(StringDefaultValue.intValue(temp.get("funcType")));
				resource.setRoles(Arrays.asList(StringDefaultValue.StringValue(temp.get("roleIdList")).split(","))
						.stream().map(role -> {
							return "AUTH_" + role;
						}).collect(Collectors.toList()));
				resource.setId(StringDefaultValue.StringValue(temp.get("funcId")));
				resources.add(resource);
			}
		}
		return resources;
	}

}
