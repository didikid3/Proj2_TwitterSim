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
	private JPanel upperHalf, bottomHalf;
	
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
		
		frame.setLayout(new GridLayout(2, 1, 0, 10));
		
		frame.add(upperHalf);
		frame.add(bottomHalf);
		
		frame.setTitle("Twitter User: " + user.getName() + "'s Window");
		frame.setSize(600, 600);
		frame.setVisible(true);
		
	}
	
	private void createUpperHalf() {
		followUser = new JButton("Follow User");
		follow_UserID = new JTextField("User ID");
		followingList = new JList<>(followingListText);
		
		
		followUser.setPreferredSize(new Dimension(170, 50));
		follow_UserID.setPreferredSize(new Dimension(280, 50));
		followingList.setPreferredSize(new Dimension(550, 200));
		
		
		followUser.addActionListener(this);
		
		JPanel tmp = new JPanel();
		tmp.setSize(800, 100);
		tmp.add(follow_UserID);
		tmp.add(followUser);
		
		JScrollPane scroll = new JScrollPane (followingList, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		upperHalf = new JPanel();
		upperHalf.setSize(800, 350);
		
		upperHalf.add(tmp);
		upperHalf.add(scroll);
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
		bottomHalf.setSize(800, 350);
		
		JScrollPane scroll = new JScrollPane (newsFeed, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		bottomHalf.add(tmp);
		bottomHalf.add(scroll);
	}

	public void updateNewsFeed(String newMessage) {
		newsFeedText.addElement(newMessage);
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
			String tweetMsg = post_TweetMessage.getText(); 
			user.sendTweet(tweetMsg);
			user.notifyObservers();
		}
		
	}
}
