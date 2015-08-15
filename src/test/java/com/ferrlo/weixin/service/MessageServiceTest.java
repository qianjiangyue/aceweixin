package com.ferrlo.weixin.service;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.ferrlo.test.FerrloTest;
import com.ferrlo.weixin.dao.MessageDao;
import com.ferrlo.weixin.domain.Message;

public class MessageServiceTest extends FerrloTest {
	@SpringBean("msgDao")
	private MessageDao msgDao;
	
	@Test
	public void testAddMessage(){
		Message message=new Message();
		message.setContent("你好啊");
		message.setToUserName("1234");
		message.setFromUserName("4567");
		message.setMsgType("text");
		message.setCreateTime(new Date());
		msgDao.addMessage(message);
	}

}
