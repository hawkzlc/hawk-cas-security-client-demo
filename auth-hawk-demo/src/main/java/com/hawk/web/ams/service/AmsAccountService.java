package com.hawk.web.ams.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.hawk.ams.api.AccountControllerApi;

@FeignClient(value = "ams-service")
public interface AmsAccountService extends AccountControllerApi {

}