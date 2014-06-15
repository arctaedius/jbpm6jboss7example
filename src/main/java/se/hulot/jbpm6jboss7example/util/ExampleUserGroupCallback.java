package se.hulot.jbpm6jboss7example.util;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import org.kie.api.task.UserGroupCallback;


@Alternative
public class ExampleUserGroupCallback implements UserGroupCallback {

    public boolean existsUser(String userId) {
        return userId.equals("john") || userId.equals("mike") || userId.equals("Administrator");
    }

    public boolean existsGroup(String groupId) {
        return groupId.equals("AA") || groupId.equals("AF");
    }

    public List<String> getGroupsForUser(String userId,
            List<String> groupIds, List<String> allExistingGroupIds) {
        List<String> groups = new ArrayList<String>();
        if (userId.equals("john"))
            groups.add("AA");
        else if (userId.equals("mike"))
            groups.add("AF");
        return groups;
    }
}
