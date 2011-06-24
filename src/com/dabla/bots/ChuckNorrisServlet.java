package com.dabla.bots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabla.bots.shared.MessageFromBot;
import com.dabla.bots.shared.MessageToBot;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@SuppressWarnings("serial")
public class ChuckNorrisServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ChuckNorrisServlet.class.getName());
	
	private static String URL_ICNDb = "http://api.icndb.com/jokes/random";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {

		if ( req.getContentLength() == 0 ) {
			logger.severe( "request content length is 0" );
		}else{
			byte[] bytesIn = new byte[ req.getContentLength() ];
			req.getInputStream().readLine( bytesIn, 0, bytesIn.length );
			String recieve = new String(bytesIn);
			logger.info("recieve : "+recieve);
			Gson gson = new Gson();
			MessageToBot action = gson.fromJson(recieve, MessageToBot.class);
			String contentBack = send(URL_ICNDb);
			MessageFromBot messageResponse = new MessageFromBot(action.getConversationId(), contentBack);
			
			String response = gson.toJson(messageResponse);
			logger.info("send : "+response);
			resp.setContentType("application/json");
			resp.setContentLength(response.length());
			resp.getWriter().write(response);
		}
	}
	
	private static String send(String callbackUrl)throws IOException,JsonSyntaxException {

		String result = null;
		// Hit the dm URL.
		URL url = new URL(callbackUrl);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("Get");
		connection.setDoOutput(false);
		
		int responseCode = connection.getResponseCode();
		if(responseCode!=200){
			logger.severe("Bad request to "+callbackUrl+" return code "+responseCode);
		}else if(responseCode == 200){
			String response = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
			logger.info("Response from Bot : "+response);
			result = extractJoke(response);
		}
		return result;
	}
	
	private static String  extractJoke(String resp) {
		logger.info("Extracting joke from resp : "+resp);
		JSONObject j;
		String result = null;
		try {
			j = new JSONObject(resp);
			JSONObject value = j.getJSONObject("value");
			result = value.getString("joke");
			logger.info("joke from JSON: " +value);
		} catch (com.google.appengine.repackaged.org.json.JSONException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
}
