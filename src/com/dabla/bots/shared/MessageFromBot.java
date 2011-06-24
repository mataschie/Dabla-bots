package com.dabla.bots.shared;

public class MessageFromBot {
	private String conversationId;
	private String content;

	@SuppressWarnings("unused")
	private MessageFromBot(){
		super();
	}
	
	public MessageFromBot(String conversationId, String content){
		this.setConversationId(conversationId);
		this.setContent(content);
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
