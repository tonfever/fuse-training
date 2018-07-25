package com.test.lab03.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*
* wrapped response object. marshall the wrapped object to JSON before sending to client 
*/

public class ResponseWrapper {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

	public ResponseWrapper(int code){
		this();
		this.errorCode = code;
	}
	public ResponseWrapper(){
		messages = new ArrayList<String>();
		traceTimestamp = dateFormat.format(LocalDateTime.now());
		traceId = UUID.randomUUID().toString();
	}
	
	
	private String traceId;
	private String traceTimestamp;
	
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public String getTraceTimestamp() {
		return traceTimestamp;
	}
	public void setTraceTimestamp(String traceTimestamp) {
		this.traceTimestamp = traceTimestamp;
	}

	@JsonIgnore
	private Object body;
	@JsonProperty("result")
	public Object getBody() {
		return body;
	}
	@JsonIgnore
	public void setBody(Object body) {
		setBody(body, false);
	}
	@JsonIgnore
	public void setBody(Object body, boolean marshalToJsonObject) {
		if(marshalToJsonObject == true){
			try {
				this.body = mapper.readTree((String)body);
			} catch (Exception e) {
				this.body = body;
			}
		}else{
			this.body = body;
		}
	}
	@JsonProperty("code")
	private int errorCode;
	
	@JsonProperty("message")
	private List<String> messages;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void addMessages(String... messages) {
		this.messages.addAll(Arrays.asList(messages));
	}

}
