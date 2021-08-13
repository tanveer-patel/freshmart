package com.infobeans.freshmart.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class Invoice__c {
	
//	private static final Object Name = null;

	public static String createAttachment(String path) throws IOException {
    	File f = new File(path);
 	    InputStream is = new FileInputStream(f);
 	    byte[] inbuff = new byte[(int)f.length()];        
 	    is.read(inbuff);
 	   String base64String = Base64.encodeBase64String(inbuff);
 	   return base64String;
 	    
//        System.out.println("\n_______________ Attachment INSERT _______________");
 
//        String baseUri = "dfds";
//		String uri = baseUri + "/sobjects/Attachment/";
//        try {
// 
//            //create the JSON object containing the new Attachment details.
//            JSONObject Attachment = new JSONObject();
//            Attachment.put("Name", Name);
//            Attachment.put("Body", base64String);
//            Attachment.put("IsPrivate", "false");
//            Attachment.put("ParentId", "a069000000sldHB");
// 
//            System.out.println("JSON for Attachment record to be inserted:\n" + Attachment.toString(1));
// 
//            //Construct the objects needed for the request
//            HttpClient httpClient = HttpClientBuilder.create().build();
// 
//            HttpPost httpPost = new HttpPost(uri);
//            Header oauthHeader;
//			httpPost.addHeader(oauthHeader);
//            Header prettyPrintHeader;
//			httpPost.addHeader(prettyPrintHeader);
//            // The message we are going to post
//            StringEntity body = new StringEntity(Attachment.toString(1));
//            body.setContentType("application/json");
//            httpPost.setEntity(body);
// 
//            //Make the request
//            HttpResponse response = httpClient.execute(httpPost);
// 
//            //Process the results
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == 201) {
//                String response_string = EntityUtils.toString(response.getEntity());
//                JSONObject json = new JSONObject(response_string);
//                // Store the retrieved Attachment id to use when we update the Attachment.
//                String AttachmentId = json.getString("id");
//                System.out.println("New Attachment id from response: " + AttachmentId);
//            } else {
//                System.out.println("Insertion unsuccessful. Status code returned is " + response.getStatusLine().getReasonPhrase());
//            }
//        } catch (JSONException e) {
//            System.out.println("Issue creating JSON or processing results");
//            e.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (NullPointerException npe) {
//            npe.printStackTrace();
//        }
    }
}
