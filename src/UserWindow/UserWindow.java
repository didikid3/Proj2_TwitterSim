package UserWindow;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Groups.*;
import Admin.*;

public class UserWindow implements ActionListener{
	private JFrame frame = new JFrame();
	private JPanel updateInfo, upperHalf, bottomHalf;
	
	private String creationTime, updateTime;
	private JTextArea createText, updateText;

	private JButton followUser, postTweet;
	
	private JTextField follow_UserID, post_TweetMessage;
	
	private JList<String> followingList, newsFeed;
	private DefaultListModel<String> followingListText, newsFeedText;
	
	private Admin admin;
	private User user;
	
	public UserWindow(User user) {
		this.user = user;
		admin = Admin.getInstance();
		
		followingListText = user.getFollowingList();
		newsFeedText = new DefaultListModel<>();
		
		createUpperHalf();
		createBottomHalf();
		createUpdateInfo();
		
		frame.add(updateInfo);

		JPanel tmp = new JPanel();
		tmp.setSize(600, 300);
		tmp.add(upperHalf);
		tmp.add(bottomHalf);

		JSplitPane tmp2 = new JSplitPane(SwingConstants.HORIZONTAL, updateInfo, tmp);
		frame.add(tmp2);

		
		frame.setTitle("Twitter User: " + user.getName() + "'s Window");
		frame.setSize(600, 600);
		frame.setVisible(true);
		
	}
	
	private void createUpdateInfo(){
		if (user.getUpdateTime() == 0){
			updateTime = "[Update Time] : Null";
		}
		else{
			updateTime = "[Update Time] : " + String.valueOf(user.getUpdateTime());
		}

		creationTime = "[Creaetion Time] : " + String.valueOf(user.getCreateTime());

		createText = new JTextArea(creationTime);
		updateText = new JTextArea(updateTime);

		updateInfo = new JPanel();
		updateInfo.setSize(500, 100);
		updateInfo.add(createText);
		updateInfo.add(updateText);
	}

	private void createUpperHalf() {
		followUser = new JButton("Follow User");
		follow_UserID = new JTextField("User ID");
		followingList = new JList<>(followingListText);
		
		
		followUser.setPreferredSize(new Dimension(200, 40));
		follow_UserID.setPreferredSize(new Dimension(300, 40));
		followingList.setPreferredSize(new Dimension(500, 150));
		
		
		followUser.addActionListener(this);
		
		JPanel tmp = new JPanel();
		tmp.setSize(500, 100);
		tmp.add(follow_UserID);
		tmp.add(followUser);
		
		JScrollPane scroll = new JScrollPane (followingList, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		upperHalf = new JPanel();
		upperHalf.setSize(500, 300);
		
		JSplitPane tmp2 = new JSplitPane(SwingConstants.HORIZONTAL, tmp, scroll);

		upperHalf.add(tmp2);
	}
	
	private void createBottomHalf() {
		postTweet = new JButton("Post Tweet");
		post_TweetMessage = new JTextField("Tweet MSG");
		newsFeed = new JList<>(newsFeedText);
		
		postTweet.setPreferredSize(new Dimension(170,50));
		post_TweetMessage.setPreferredSize(new Dimension(280,50));
		newsFeed.setPreferredSize(new Dimension(550, 200));
		
		postTweet.addActionListener(this);
		
		JPanel tmp = new JPanel();
		tmp.setSize(800,100);
		tmp.add(post_TweetMessage);
		tmp.add(postTweet);
		
		bottomHalf = new JPanel();
		bottomHalf.setSize(600, 300);
		
		JScrollPane scroll = new JScrollPane (newsFeed, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JSplitPane tmp2 = new JSplitPane(SwingConstants.HORIZONTAL, tmp, scroll);
		bottomHalf.add(tmp2);
		
	}

	public void updateNewsFeed(String newMessage) {
		newsFeedText.addElement(newMessage);

		updateTime = "[Update Time] : " + String.valueOf(user.getUpdateTime());
		updateText.setText(updateTime);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == followUser) {
			UserGroup root = admin.getRoot();
			String newID = follow_UserID.getText();
			
			//Maybe re-write so that user handle's followers
			User newFollowing= root.findUser(newID);
			if(newFollowing != null) {
				followingListText = user.followUser(newFollowing, newID);
			}
			
		}
		else if(e.getSource() == postTweet) {
			updateTime = "[Update Time] : " + String.valueOf(user.getUpdateTime());
			updateText.setText(updateTime);

			String tweetMsg = post_TweetMessage.getText(); 
			user.sendTweet(tweetMsg);
			user.notifyObservers();
		}
		
	}
}
