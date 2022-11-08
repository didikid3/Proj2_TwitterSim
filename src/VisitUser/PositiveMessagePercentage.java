package VisitUser;


import javax.swing.DefaultListModel;

import Groups.User;
import Groups.UserGroup;

public class PositiveMessagePercentage implements GroupComponentVisitor{
	
	private int count = 0;
	private int positiveCount = 0;
	
	@Override
	public void visitUser(User user) {
		DefaultListModel<String> messages = user.getTweetList();
		int size = messages.getSize();
		setCount(getCount() + size);
		
		for(int i = 0; i < size; i++) {
			String msg = messages.get(i);
			
			if(msg.contains("Yay")) {
				setPositive(getPositiveCount() + 1);
			}
		}
		
	}

	@Override
	public void visitUserGroup(UserGroup userGroup) {
		setCount(getCount());		
	}
	
	public double getPercentage() {
		System.out.println(getPositiveCount());
		return (double)getPositiveCount() / getCount();
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setPositive(int count) {
		positiveCount = count;
	}
	public int getPositiveCount() {
		return positiveCount;
	}

}
