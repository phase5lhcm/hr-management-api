package com.portfolioprj.humanresourcemanagementapi;

import com.portfolioprj.humanresourcemanagementapi.helpers.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HumanResourceManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceManagementApiApplication.class, args);
	}

	// filter registration bean will be applied to protected endpoints
	@Bean
	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> employeeRegistrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		employeeRegistrationBean.setFilter(authFilter);
		employeeRegistrationBean.addUrlPatterns("/api/departments/*");
		return employeeRegistrationBean;
	}

}
