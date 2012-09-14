package com.example.model;

public class Authorization {

	private String access_token;
	
	private String expires_in;
	
	private String refresh_token;
	
	private String cnpj_empresa;
	
	private String id_terceiro;
	
	private String nome_terceiro;
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getCnpj_empresa() {
		return cnpj_empresa;
	}

	public void setCnpj_empresa(String cnpj_empresa) {
		this.cnpj_empresa = cnpj_empresa;
	}

	public String getId_terceiro() {
		return id_terceiro;
	}

	public void setId_terceiro(String id_terceiro) {
		this.id_terceiro = id_terceiro;
	}

	public String getNome_terceiro() {
		return nome_terceiro;
	}

	public void setNome_terceiro(String nome_terceiro) {
		this.nome_terceiro = nome_terceiro;
	}
}
