package VisitUser;

import Groups.User;
import Groups.UserGroup;

public interface GroupComponentVisitor {
	public void visitUser(User user);
	public void visitUserGroup(UserGroup userGroup);
}
