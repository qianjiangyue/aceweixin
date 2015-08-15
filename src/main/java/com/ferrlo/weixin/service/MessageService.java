package com.ferrlo.weixin.service;

import com.ferrlo.weixin.domain.Message;

public interface MessageService {
	void addMessage(Message msg);

	String replyMessage(Message message);
	

}
