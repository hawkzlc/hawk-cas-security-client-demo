package com.hawk.web.ams;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hawk.ams.model.AccountInfo;
import com.hawk.cas.client.entity.User;
import com.hawk.utils.exception.BusinessException;
import com.hawk.utils.response.api.ReturnData;
import com.hawk.web.ams.service.AmsAccountService;

/**
 * @author Administrator
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hawk.web.ams.service")
@ComponentScan(basePackages = { "com.hawk" })
public class AmsWebApplication {
	Logger logger = LoggerFactory.getLogger(AmsWebApplication.class);

	public static final String className = AmsWebApplication.class.getName();

	public static void main(String[] args) {
		SpringApplication.run(AmsWebApplication.class, args);
	}

	@Autowired
	AmsAccountService amsAccountService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		logger.info("Current User-Name={}", securityContextImpl.getAuthentication().getName());
		User user = (User) securityContextImpl.getAuthentication().getPrincipal();
		logger.info("Current User-Attributes={}", user.getAttributes());
		logger.info("Current User-ID={}", user.getId());
		return "访问了首页哦";
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(HttpServletRequest request) {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			throw new BusinessException(className + "<0000>", "该功能不支持GET方法访问.");
		}
		return "不验证哦";
	}

//	@PreAuthorize("hasAuthority('TEST')") // 有TEST权限的才能访问
	@RequestMapping("/security")
	public String security() {
		return "hello world security";
	}

//	@PreAuthorize("hasAuthority('ADMIN')") // 必须要有ADMIN权限的才能访问
	@RequestMapping("/authorize")
	@ResponseBody
	public Map<String, Object> authorize() {
		Map<String, Object> aMap = new HashMap<>();
		aMap.put("key", 1000);
		return aMap;
	}

	@RequestMapping("/getAllAccount")
	@ResponseBody
	public ReturnData getAllAccount() {
		AccountInfo condition = new AccountInfo();
		condition.setAccountName("112222");
		return amsAccountService.getListByCondition(condition, 1, 10);
	}

}
