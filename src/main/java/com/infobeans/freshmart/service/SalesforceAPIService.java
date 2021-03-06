package com.infobeans.freshmart.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import com.infobeans.freshmart.auth.AccountResponse;
import com.infobeans.freshmart.auth.AuthenticationResponse;
import com.infobeans.freshmart.model.Account;
import com.infobeans.freshmart.model.Invoice__c;

@Service
public class SalesforceAPIService {

	public AuthenticationResponse getLogin() {
		return new AuthenticationResponse().login();
	}
	
	public Account create(String accessToken, String instanceUrl, Account account) {
		return new Account().createAccount(accessToken, instanceUrl, account);
	}
	
	public Account loginAccount(String accessToken, String instanceUrl, String email, String password) {
		return new Account().loginAccount(accessToken, instanceUrl, email, password);		
	}

	public List<Account> getAccounts(String accessToken, String instanceUrl) {
		return new Account().getAccounts(accessToken, instanceUrl);
	}
	
	public String getFile(String path) throws IOException {
		return Invoice__c.createAttachment(path);
	}
}
