package com.example.model;

public class RequestInformation {

	private String grant_type;
	
	private String app_id;
	
	private String app_secret;
	
	private String redirect_uri;
	
	private String code;

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}
}
