package se.hulot.jbpm6jboss7example.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import se.hulot.jbpm6jboss7example.dao.MessageDao;
import se.hulot.jbpm6jboss7example.domain.Message;

import javax.ejb.TransactionAttributeType;

@LocalBean
@Stateless
@TransactionAttribute(value = TransactionAttributeType.NEVER)
public class NotificationWorkItemHandler implements WorkItemHandler {
    private Logger log = Logger.getLogger(this.getClass().getName());

	private MessageDao messageDao;

	public NotificationWorkItemHandler() {
		try {
			Context ctx = new InitialContext();
			messageDao = (MessageDao) ctx
					.lookup("global/jbpm6jboss7example-1.0-SNAPSHOT/MessageDaoImpl");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void abortWorkItem(WorkItem item, WorkItemManager manager) {
		// TODO Auto-generated method stub
		
	}
	

	public void executeWorkItem(WorkItem item, WorkItemManager manager) {
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean b = true;

	    resultMap.put("result", b ? "OK" : "ERROR");
	    
	    Map<String, Object> params = item.getParameters();
	    
	    log.info("messageid=" + params.get("messageId"));
	    Message m = messageDao.find(Long.parseLong((String) params.get("messageId")));
	    log.info("message timestamp = " + m != null ? m.getTimestamp().toString() : "null");
	    manager.completeWorkItem(item.getId(), resultMap);		
	}

}
