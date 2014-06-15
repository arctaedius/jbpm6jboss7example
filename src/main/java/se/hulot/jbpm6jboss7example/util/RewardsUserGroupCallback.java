/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.hulot.jbpm6jboss7example.util;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import org.kie.api.task.UserGroupCallback;


@Alternative
public class RewardsUserGroupCallback implements UserGroupCallback {

    public boolean existsUser(String userId) {
        return userId.equals("john") || userId.equals("mike") || userId.equals("Administrator");
    }

    public boolean existsGroup(String groupId) {
        return groupId.equals("AF");
    }

    public List<String> getGroupsForUser(String userId,
            List<String> groupIds, List<String> allExistingGroupIds) {
        List<String> groups = new ArrayList<String>();
        if (userId.equals("john"))
            groups.add("PM");
        else if (userId.equals("mike"))
            groups.add("AF");
        return groups;
    }
}
