package com.infobeans.freshmart.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.infobeans.freshmart.auth.AccountResponse;
import com.infobeans.freshmart.auth.AuthenticationResponse;

public class Account {
	@JsonProperty("attributes")
	private Attribute attributes;
	@JsonProperty("Id")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Email__c")
	private String email__c;
	@JsonProperty("Phone")
	private String phone;
	@JsonProperty("Password__c")
	private String password__c;
	@JsonProperty("Address__c")
	private String address__c;
	
	
	public String getEmail__c() {
		return email__c;
	}

	public void setEmail__c(String email__c) {
		this.email__c = email__c;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword__c() {
		return password__c;
	}

	public void setPassword__c(String password__c) {
		this.password__c = password__c;
	}

	public String getAddress__c() {
		return address__c;
	}

	public void setAddress__c(String address__c) {
		this.address__c = address__c;
	}

	
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("attributes")
	public Attribute getAttributes() {
		return attributes;
	}

	@JsonProperty("attributes")
	public void setAttributes(Attribute attributes) {
		this.attributes = attributes;
	}

	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
//	public Account getAccountData(String accessToken, String instanceUrl,Account account) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("Authorization", "Bearer " + accessToken);
//		MultiValueMap<String, Account> params= new LinkedMultiValueMap<String, Account>();
//		params.add("ac", account);
//		HttpEntity<MultiValueMap<String, Account>> entity = new HttpEntity<MultiValueMap<String, Account>>(params,headers);
//		RestTemplate restTemplate = new RestTemplate();
//		Account ac = restTemplate.exchange(instanceUrl+ "/services/apexrest/SellerLogin/", HttpMethod.POST, entity, Account.class).getBody();
//		return ac;
//	}
	
	public Account createAccount(String accessToken, String instanceUrl, Account account) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken);
	    HttpEntity<Account> entity = new HttpEntity<Account>(account,headers);
		RestTemplate restTemplate = new RestTemplate();
		Account a = restTemplate.exchange(instanceUrl+ "/services/data/v51.0/sobjects/account/", HttpMethod.POST, entity, Account.class).getBody();
		return a;
	}
}
