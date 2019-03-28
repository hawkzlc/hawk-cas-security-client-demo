package com.hawk.web.ams.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.hawk.auth.api.AuthControllerApi;

@FeignClient(value = "auth-service")
public interface AuthService extends AuthControllerApi {

}