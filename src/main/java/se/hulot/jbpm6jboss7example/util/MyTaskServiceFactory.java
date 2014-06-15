package se.hulot.jbpm6jboss7example.util;

import org.jbpm.runtime.manager.impl.factory.CDITaskServiceFactory;
import org.kie.api.task.TaskService;

public class MyTaskServiceFactory extends CDITaskServiceFactory {

    @Override
    public TaskService newTaskService() {
        return super.newTaskService();
    }

}
