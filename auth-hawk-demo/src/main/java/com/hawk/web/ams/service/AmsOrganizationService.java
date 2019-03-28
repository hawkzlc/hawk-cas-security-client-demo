package com.hawk.web.ams.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.hawk.ams.api.OrganizeControllerApi;

@FeignClient(value = "ams-service")
public interface AmsOrganizationService extends OrganizeControllerApi {

}