package Groups;

public class User extends Subject implements GroupComponent, Observer{
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	//When new post on twitter
	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
