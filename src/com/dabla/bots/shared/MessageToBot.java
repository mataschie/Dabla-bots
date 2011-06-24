package com.dabla.bots.shared;

public class MessageToBot {
	
	private String conversationId;
	private String userId;
	private String userName;
	private String content;
	
	@SuppressWarnings("unused")
	private MessageToBot(){
		super();
	}
	
	public MessageToBot(String conversationId, String userId, String userName, String content){
		this.setConversationId(conversationId);
		this.setUserId(userId);
		this.setUserName(userName);
		this.setContent(content);
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
