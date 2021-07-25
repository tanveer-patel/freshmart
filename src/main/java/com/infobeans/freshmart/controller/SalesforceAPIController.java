package com.infobeans.freshmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

//@RequestMapping(value = "/createAccountApex", method = RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
//public Account getAccountData(@RequestBody Account a) {
//	AuthenticationResponse authenticationResponse = salesforceAPIService.getLogin();
//	return new Account().getAccountData(authenticationResponse.getAccess_token(), authenticationResponse.getInstance_url(),a);
//}
}