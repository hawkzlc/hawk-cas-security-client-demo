package com.hawk.web.ams.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.hawk.ams.api.PartnerConfControllerApi;

@FeignClient(value = "ams-service")
public interface AmsPartnerConfService extends PartnerConfControllerApi {

}