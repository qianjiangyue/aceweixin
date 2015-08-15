package com.ferrlo.weixin.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.ferrlo.test.FerrloTest;
import com.ferrlo.weixin.dao.MessageDao;
import com.ferrlo.weixin.domain.Message;

public class MessageDaoTest extends FerrloTest {
	@SpringBean("msgDao")
	private MessageDao messageDao;

	@Test
	public void testMessageDaoNotNull() {
		assertNotNull(messageDao);
	}
	@Test
	public void testAddMessage(){
		Message message=new Message();
		message.setContent("你好啊");
		message.setToUserName("123");
		message.setFromUserName("456");
		message.setMsgType("text");
		message.setCreateTime(new Date());
		messageDao.addMessage(message);
	}

}
