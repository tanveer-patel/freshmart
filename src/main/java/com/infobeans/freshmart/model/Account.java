package com.infobeans.freshmart.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
	@JsonProperty("id")
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

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
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
		HttpEntity<Account> entity = new HttpEntity<Account>(account, headers);
		RestTemplate restTemplate = new RestTemplate();
		Account a = restTemplate.exchange(instanceUrl + "/services/data/v51.0/sobjects/account/", HttpMethod.POST,
				entity, Account.class).getBody();
		account.setId(a.getId());
		return account;
	}

	public Account loginAccount(String accessToken, String instanceUrl, String email, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity salesforceTestData = restTemplate.exchange(instanceUrl
				+ "/services/data/v51.0/query/?q=SELECT+Id,Name,Password__c,Email__c,Address__c,Phone+From+Account+WHERE+Email__c='"
				+ email + "'", HttpMethod.GET, request, AccountResponse.class);

		AccountResponse ar = (AccountResponse) salesforceTestData.getBody();
//		Account a = (Account) ar.getRecords().get(0);
		if (ar.getTotalSize() > 0) {
			System.out.println(ar.getRecords().get(0));
			LinkedHashMap<String, Object> lhm = (LinkedHashMap<String, Object>) ar.getRecords().get(0);
			System.out.println(lhm.get("attributes"));
			Account a = new Account();
			if (password.equals((String) lhm.get("Password__c"))) {

				a.setEmail__c((String) lhm.get("Email__c"));
				a.setName((String) lhm.get("Name"));
				a.setId((String) lhm.get("Id"));
				a.setPhone((String) lhm.get("Phone"));
				a.setPassword__c(password);
				a.setAddress__c((String) lhm.get("Address__c"));

				return a;
			}else
				return null;
		} else
			return null;
	}
}
