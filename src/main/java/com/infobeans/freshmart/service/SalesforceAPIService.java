package com.infobeans.freshmart.service;

import org.springframework.stereotype.Service;

import com.infobeans.freshmart.auth.AccountResponse;
import com.infobeans.freshmart.auth.AuthenticationResponse;
import com.infobeans.freshmart.model.Account;

@Service
public class SalesforceAPIService {

	public AuthenticationResponse getLogin() {
		return new AuthenticationResponse().login();
	}
	
	public Account create(String accessToken, String instanceUrl, Account account) {
		return new Account().createAccount(accessToken, instanceUrl, account);
	}
	
}
