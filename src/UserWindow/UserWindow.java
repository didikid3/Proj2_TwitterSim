package UserWindow;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import Groups.*;

public class UserWindow {
	private JFrame frame = new JFrame();
	private JPanel upperHalf, bottomHalf;
	
	private JButton followUser, postTweet;
	
	private JTextField follow_UserID, post_TweetMessage;
	
	private JTextField followingList, newsFeed;
	
	public UserWindow(User user) {
		
		createUpperHalf();
		createBottomHalf();
		
		frame.setLayout(new GridLayout(2, 1, 0, 50));
		
		frame.add(upperHalf);
		frame.add(bottomHalf);
		
		frame.setTitle("Twitter User: " + user.getName() + "'s Window");
		frame.setSize(800, 500);
		frame.setVisible(true);
	}
	
	private void createUpperHalf() {
		followUser = new JButton("Follow User");
		follow_UserID = new JTextField("User ID", 16);
		followingList = new JTextField("Following List");
		
		followingList.setSize(new Dimension(800, 200));
		
		JPanel tmp = new JPanel();
		tmp.setLayout(new GridLayout(1, 2, 50, 0));
		tmp.add(follow_UserID);
		tmp.add(followUser);
		
		upperHalf = new JPanel();
		upperHalf.setLayout(new GridLayout(2, 1, 0, 15));
		
		upperHalf.add(tmp);
		upperHalf.add(followingList);
		
		upperHalf.setEnabled(false);
		}
	
	private void createBottomHalf() {
		postTweet = new JButton("Post Tweet");
		post_TweetMessage = new JTextField("Tweet MSG", 16);
		newsFeed = new JTextField("News List");
		
		newsFeed.setSize(new Dimension(800, 200));
		
		JPanel tmp = new JPanel();
		tmp.setLayout(new GridLayout(1, 2, 50, 0));
		tmp.add(post_TweetMessage);
		tmp.add(postTweet);
		
		bottomHalf = new JPanel();
		bottomHalf.setLayout(new GridLayout(2, 1, 0, 15));
		
		bottomHalf.add(tmp);
		bottomHalf.add(newsFeed);
		
		bottomHalf.setEnabled(false);
	}
}
