package com.ferrlo.weixin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ferrlo.weixin.domain.Message;
@Repository("msgDao")
public class MessageDaoHibernate implements MessageDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	public MessageDaoHibernate(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addMessage(Message msg) {
		currentSession().save(msg);
	}

}
