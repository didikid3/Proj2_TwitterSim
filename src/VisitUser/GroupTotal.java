package VisitUser;

import Groups.User;
import Groups.UserGroup;

public class GroupTotal implements GroupComponentVisitor{

	private int count = 0;
	@Override
	public void visitUser(User user) {
		setCount(getCount());
	}

	@Override
	public void visitUserGroup(UserGroup userGroup) {
		setCount(getCount() + 1);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


}


