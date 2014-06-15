package se.hulot.jbpm6jboss7example.util;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class NotificationWorkItemHandler implements WorkItemHandler {

	public void abortWorkItem(WorkItem item, WorkItemManager manager) {
		// TODO Auto-generated method stub
		
	}

	public void executeWorkItem(WorkItem item, WorkItemManager manager) {
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean b = true;

	    resultMap.put("result", b ? "OK" : "ERROR");
	 
	    manager.completeWorkItem(item.getId(), resultMap);		
	}

}
