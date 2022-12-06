package VisitUser;

import java.util.HashSet;
import java.util.Set;

import Groups.User;
import Groups.UserGroup;

public class NameVaildation implements GroupComponentVisitor{
    private Set<String> userSet = new HashSet<>();
    private boolean valid = true;
    public void visitUser(User user){
        if(!valid || userSet.contains(user.getName()) || user.getName().contains(" ")){
            valid = false;
        }
        else{
            userSet.add(user.getName());
        }
    }
    public void visitUserGroup(UserGroup userGroup){
        if(!valid || userSet.contains(userGroup.getName()) || userGroup.getName().contains(" ")){
            valid = false;
        }
        else{
            userSet.add(userGroup.getName());
        }
    }

    public boolean valid(){
        return valid;
    }
}
