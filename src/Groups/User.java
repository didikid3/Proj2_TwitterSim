package Groups;


import javax.swing.DefaultListModel;

import UserWindow.UserWindow;
import VisitUser.GroupComponentVisitor;

@SuppressWarnings("serial")
public class User extends Subject implements GroupComponent, Observer{
	private String userName;
	
	private UserWindow userWindow;
	
	private DefaultListModel<String> followingList;
	private DefaultListModel<String> tweets;

	public User(String name) {
		userName = name;
		followingList = new DefaultListModel<>();
		tweets = new DefaultListModel<>();
	}
	
	@Override
	public boolean getAllowsChildren() {
		return false;
	}
	
	public DefaultListModel<String> followUser(User user, String name) {
		user.attach(this);
		followingList.addElement(name);
		return followingList;
	}
	
	public void sendTweet(String tweet) {
		tweets.addElement(tweet);
	}
	
	public String getMostRecentTweet() {
		return tweets.lastElement();
	}
	
	public DefaultListModel<String> getFollowingList() {
		return followingList;
	}
	
	public User findUser(String name) {
		if(name.equals(userName)){
			return this;
		}
		return null;
	}
	
	public void accept(GroupComponentVisitor visitor) {
		visitor.visitUser(this);
	}
	
	public void setUserWindow(UserWindow window) {
		userWindow = window;
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
		User tmp = (User) subject;
		userWindow.updateNewsFeed("[" + tmp.getName() + "]: "
				+ tmp.getMostRecentTweet());
		
	}
	
	public String toString() {
		return userName;
	}
	
	
	
}
