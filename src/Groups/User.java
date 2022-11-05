package Groups;

import VisitUser.GroupComponentVisitor;

@SuppressWarnings("serial")
public class User extends Subject implements GroupComponent, Observer{
	private String userName;

	public User(String name) {
		userName = name;
	}
	
	@Override
	public boolean getAllowsChildren() {
		return false;
	}
	
	public void accept(GroupComponentVisitor visitor) {
		visitor.visitUser(this);
	}
	
	
	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	//When new post on twitter
	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return userName;
	}
	
	
	
}
