package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.model.Authorization;
import com.example.model.RequestInformation;

@Controller
public class OauthController {

	private static final String URL_CALLBACK = "http://mapa-terceiros.herokuapp.com/mvc/oauth/vpsa/callback";
//	private static final String URL_CALLBACK = "http://localhost:8080/mapa-terceiros-java/mvc/oauth/vpsa/callback";

	@Autowired
	private RestTemplate restTemplate;
	
	private @Autowired HttpServletRequest request;
	
	@RequestMapping("/oauth/vpsa/callback")
	public String callback()
	{
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<RequestInformation> httpEntity = new HttpEntity<RequestInformation>(getRequestInformation(request.getParameter("code")), headers);
		
		ResponseEntity<Authorization> exchange = restTemplate.exchange("https://www.vpsa.com.br/apps/oauth/token", HttpMethod.POST, httpEntity, Authorization.class,  (Object)null);
		request.getSession().setAttribute("access_token", exchange.getBody().getAccess_token());	    	
			    		
		return "redirect:/mvc" + request.getSession().getAttribute("url_before_auth");
	}
	
	private RequestInformation getRequestInformation(String requestToken) {
		RequestInformation requestInformation = new RequestInformation();
		
		requestInformation.setCode(requestToken);
		requestInformation.setRedirect_uri(URL_CALLBACK);
		requestInformation.setApp_secret("3463798f6a4c10b1b998634f7c3b3fff713362abaf6153bd428b0219e6144e1d");
		requestInformation.setApp_id("50533a04d93a4b7a7c00000d");
		requestInformation.setGrant_type("authorization_code");
		
		return requestInformation;
	}

	@RequestMapping("/auth/oauth")
	public String auth()
	{
		String urlAuth = "https://www.vpsa.com.br/apps/oauth/authorization?";
		urlAuth += "response_type=code&";
		urlAuth += "app_id=50533a04d93a4b7a7c00000d&";
		urlAuth += "redirect_uri=" + URL_CALLBACK;
			    		
		return "redirect:" + urlAuth;
	}
}
