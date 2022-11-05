package VisitUser;

import Groups.*;

public class UserTotal implements GroupComponentVisitor{
	private int count = 0;
	@Override
	public void visitUser(User user) {
		setCount(getCount() + 1);
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
