package se.hulot.jbpm6jboss7example.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import se.hulot.jbpm6jboss7example.domain.Message;

@Local(MessageDao.class)
@Stateless(mappedName="MessageDao")
public class MessageDaoImpl extends DaoImpl<Message> implements MessageDao {

}
