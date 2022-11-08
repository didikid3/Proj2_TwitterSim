package VisitUser;

import javax.swing.DefaultListModel;

import Groups.User;
import Groups.UserGroup;

public class MessageTotal implements GroupComponentVisitor{

	private int count = 0;
	@Override
	public void visitUser(User user) {
		DefaultListModel<String> messages = user.getTweetList();
		setCount(getCount() + messages.getSize());
	}

	@Override
	public void visitUserGroup(UserGroup userGroup) {
		setCount(getCount());
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
