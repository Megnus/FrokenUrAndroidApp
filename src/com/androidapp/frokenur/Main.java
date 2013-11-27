package com.androidapp.frokenur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;


public class Main extends Activity {
    /** Called when the activity is first created. */
	TextView display;	
	
	@Override
    
    public void onCreate(Bundle savedInstanceState) {	
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);	
				
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        display = (TextView) findViewById(R.id.tvDisplay);
        display.setText("asdasd");
        TestHttpGet httpGetObj = new TestHttpGet();
        
        try {
        	httpGetObj.executeHttpGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }	
    
	public class TestHttpGet {
	    public void executeHttpGet() throws Exception {
	        BufferedReader in = null;
	        try {
	        	HttpClient client = new DefaultHttpClient();
	        	HttpGet request = new HttpGet();
	        	request.setURI(new URI("http://time.is/"));
	        	HttpResponse response = client.execute(request);
	        	in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        	
	        	StringBuffer sb = new StringBuffer("");
	            String line = "";
	            String NL = System.getProperty("line.separator");
	            
	            while ((line = in.readLine()) != null) {
	                sb.append(line + NL);
	            }
	           
	            in.close();
	            String page = sb.toString();
	            
	            // String[] separated = page.split("\n");
	            display.setText(page);
	            
	            } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                    } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
}


