package com.hawk.web.ams.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.hawk.ams.api.PartnerControllerApi;

@FeignClient(value = "ams-service")
public interface AmsPartnerService extends PartnerControllerApi {

}