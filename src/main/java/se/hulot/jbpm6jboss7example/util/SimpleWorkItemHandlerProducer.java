package se.hulot.jbpm6jboss7example.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.jbpm.runtime.manager.api.WorkItemHandlerProducer;
import org.kie.api.runtime.process.WorkItemHandler;

public class SimpleWorkItemHandlerProducer implements WorkItemHandlerProducer {
    private Logger log = Logger.getLogger(this.getClass().getName());
	@Override
    public Map<String, WorkItemHandler> getWorkItemHandlers(String s, Map<String, Object> stringObjectMap) {
    	Map<String,WorkItemHandler> handlers = new HashMap<String, WorkItemHandler>();
    	handlers.put("Notification", new NotificationWorkItemHandler());
    	log.info("Adding work item handler");
        return handlers;
    }
}
