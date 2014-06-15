package se.hulot.jbpm6jboss7example.ejb;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import se.hulot.jbpm6jboss7example.dao.MessageDao;
import se.hulot.jbpm6jboss7example.domain.Message;

@LocalBean
@Stateless
public class MessageService {

	@EJB
	private MessageDao messageDao;
	
	public Message createMessage(String name) {
		Message m = new Message();
		m.setName(name);
		m.setTimestamp(new Date());
		m = messageDao.create(m);
		return m;
	}
}
