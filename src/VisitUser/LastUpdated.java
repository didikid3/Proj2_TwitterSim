package VisitUser;

import Groups.User;
import Groups.UserGroup;

public class LastUpdated implements GroupComponentVisitor{
    private long updateTime = 0;
    private boolean exists = false;
    private User user;

    public void visitUser(User user){
        if(!exists){
            exists = true;
        }
        if(updateTime <= user.getUpdateTime()){
            updateTime = user.getUpdateTime();
            this.user = user;
        }
    }
    public void visitUserGroup(UserGroup userGroup){

    }

    public boolean exists(){
        return exists;
    }

    public String lastUpdated(){
        return user.getName();
    }
}
