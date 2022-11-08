package VisitUser;

import Groups.User;
import Groups.UserGroup;
import UserWindow.UserWindow;

public class OpenUser implements GroupComponentVisitor{

	@Override
	public void visitUser(User user) {
		UserWindow userWindow = new UserWindow(user);
		user.setUserWindow(userWindow);
	}

	@Override
	public void visitUserGroup(UserGroup userGroup) {
		System.out.println("NOT POSSIBLE");		
	}
	
}
