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

	@Autowired
	private RestTemplate restTemplate;
	
	private @Autowired HttpServletRequest request;
	
	@RequestMapping("/oauth/vpsa/callback")
	public String callback()
	{
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<RequestInformation> httpEntity = new HttpEntity<RequestInformation>(getRequestInformation(request.getParameter("code")), headers);
		
		ResponseEntity<Authorization> exchange = restTemplate.exchange("https://vpsa-oauth-server.herokuapp.com/oauth/token", HttpMethod.POST, httpEntity, Authorization.class,  (Object)null);
		request.getSession().setAttribute("access_token", exchange.getBody().getAccess_token());	    	
			    		
		return "redirect:/mvc" + request.getSession().getAttribute("url_before_auth");
	}
	
	private RequestInformation getRequestInformation(String requestToken) {
		RequestInformation requestInformation = new RequestInformation();
		
		requestInformation.setCode(requestToken);
		requestInformation.setRedirect_uri(URL_CALLBACK);
		requestInformation.setClient_secret("592344d14c9f5d737b0ec1c8191c6e9df5351b4c434e1dc331ce00625f1a3ffa");
		requestInformation.setClient_id("5053319cd93a4b7a7c000007");
		requestInformation.setGrant_type("authorization_code");
		
		return requestInformation;
	}

	@RequestMapping("/auth/oauth")
	public String auth()
	{
		String urlAuth = "https://www.vpsa.com.br/apps/oauth/authorization?";
		urlAuth += "response_type=code&";
		urlAuth += "app_id=5053319cd93a4b7a7c000007&";
		urlAuth += "redirect_uri=" + URL_CALLBACK;
			    		
		return "redirect:" + urlAuth;
	}
}
