package com.pachiraframework.java8.ch04;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClientAbortMethod {  
	  
    public final static void main(String[] args) throws Exception {  
    	DefaultHttpClient httpclient = new DefaultHttpClient();  
        try {  
            HttpGet httpget = new HttpGet("http://www.apache.org/");  
  
            System.out.println("executing request " + httpget.getURI());  
            HttpResponse response = httpclient.execute(httpget);  
            HttpEntity entity = response.getEntity();  
  
            System.out.println("----------------------------------------");  
            System.out.println(response.getStatusLine());  
            if (entity != null) {  
                System.out.println("Response content length: " + entity.getContentLength());  
            }  
            System.out.println("----------------------------------------");  
  
            // Do not feel like reading the response body   
            // Call abort on the request object  
            // 不打算读取response body   
            // 调用request的abort方法  
            httpget.abort();  
        } finally {  
            // When HttpClient instance is no longer needed,  
            // shut down the connection manager to ensure  
            // immediate deallocation of all system resources  
            // 当HttpClient实例不再需要是，确保关闭connection manager，以释放其系统资源  
            httpclient.getConnectionManager().shutdown();  
        }  
    }  
  
}  
