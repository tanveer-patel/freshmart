package com.infobeans.freshmart.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.infobeans.freshmart.auth.AccountResponse;
import com.infobeans.freshmart.auth.AuthenticationResponse;
import com.infobeans.freshmart.model.Account;
import com.infobeans.freshmart.service.SalesforceAPIService;

@RestController
public class SalesforceAPIController {

	@Autowired
	SalesforceAPIService salesforceAPIService;

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account checkCreadential(@RequestBody Account a) {
		AuthenticationResponse authenticationResponse = salesforceAPIService.getLogin();
		return salesforceAPIService.create(authenticationResponse.getAccess_token(),
				authenticationResponse.getInstance_url(), a);
	}

	@RequestMapping(value = "/login/{email}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account loginAccount(@PathVariable String email, @PathVariable String password) {
		AuthenticationResponse authenticationResponse = salesforceAPIService.getLogin();
		return salesforceAPIService.loginAccount(authenticationResponse.getAccess_token(),
				authenticationResponse.getInstance_url(), email, password);
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAccounts() {
		AuthenticationResponse authenticationResponse = salesforceAPIService.getLogin();
		return salesforceAPIService.getAccounts(authenticationResponse.getAccess_token(),
				authenticationResponse.getInstance_url());
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getFiles(@RequestPart String path) throws IOException {
		return salesforceAPIService.getFile(path.toString());
	}
//@RequestMapping(value = "/createAccountApex", method = RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
//public Account getAccountData(@RequestBody Account a) {
//	AuthenticationResponse authenticationResponse = salesforceAPIService.getLogin();
//	return new Account().getAccountData(authenticationResponse.getAccess_token(), authenticationResponse.getInstance_url(),a);
//}
}