package com.ferrlo.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ferrlo.weixin.dao.MessageDao;
import com.ferrlo.weixin.domain.Message;
import com.ferrlo.weixin.domain.Reply;
@Service("msgService")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao msgDao;

	@Override
	public void addMessage(Message msg) {
		msgDao.addMessage(msg);
	}
	
	@Override
	public String replyMessage(Message message) {
		String replyContent=Reply.WELCOME_CONTENT;
		String type=message.getMsgType();
		if(type.equals(Message.TEXT)){
			String content=message.getContent();
			if(content.startsWith("#")){
				String serialNum=content.substring(1);
				try{
					replyContent=serialNum;
					
				}catch(NumberFormatException e){
					replyContent=Reply.ERROR_CONTENT;
				}
				
			}
			
		}
		return replyContent;
	}

	public MessageDao getMsgDao() {
		return msgDao;
	}

	public void setMsgDao(MessageDao msgDao) {
		this.msgDao = msgDao;
	}

}
