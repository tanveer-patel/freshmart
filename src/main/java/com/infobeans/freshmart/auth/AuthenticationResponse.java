package com.infobeans.freshmart.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class AuthenticationResponse {
 
private String access_token;
private String instance_url;
private String token_type;
private String issued_at;
 
public String getAccess_token() {
return access_token;
}
public void setAccess_token(String access_token) {
this.access_token = access_token;
}
public String getInstance_url() {
return instance_url;
}
public void setInstance_url(String instance_url) {
this.instance_url = instance_url;
}
public String getToken_type() {
return token_type;
}
public void setToken_type(String token_type) {
this.token_type = token_type;
}
public String getIssued_at() {
return issued_at;
}
public void setIssued_at(String issued_at) {
this.issued_at = issued_at;
}
public AuthenticationResponse login(){
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
 
params.add("username", "sarthak.kanojiya@wise-unicorn-rw83rl.com");
params.add("password", "9098202058@aAuClxYTN5h9PEOt90diFlDgKf" );
params.add("client_secret", "AFA6A933A272B5F36AC0463A5B4D03B22513C6AECD991C5C44882E1254FDDF5F");
params.add("client_id", "3MVG9fe4g9fhX0E7ymsmVD.SrF4DXbl39lh6bnWhoHl31BRGMBv_uTlxyUQTJxC5i18S8gcDFI8172Bo33_J3");
params.add("grant_type","password");
 
HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
RestTemplate restTemplate = new RestTemplate();
ResponseEntity response = restTemplate.postForEntity("https://login.salesforce.com/services/oauth2/token", request, AuthenticationResponse.class);
return (AuthenticationResponse) response.getBody();
}
 
@Override
public String toString() {
return "AuthenticationResponse [access_token=" + access_token + ", instance_url=" + instance_url
+ ", token_type=" + token_type + ", issued_at=" + issued_at + "]";
}
}