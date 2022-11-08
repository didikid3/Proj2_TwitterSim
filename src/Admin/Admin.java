package Admin;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import Groups.*;
import VisitUser.*;


public class Admin implements ActionListener{
	private static Admin adminInstance; 
	
	private JFrame frame = new JFrame();
	
	private JButton addUser, addGroup, openUser;
	private JButton userTotal, groupTotal, messageTotal, positivePercentage;
	
	private JTextField userID, groupID;

	private JScrollPane scrollPane;
	private JTree userList;
	private UserGroup root;
	private static List<GroupComponent> temp = new ArrayList<>();
	
	
	private JPanel pL = new JPanel();
	private JPanel pRightUpper = new JPanel();
	private JPanel pRightLower = new JPanel();
	
	private JSplitPane mainPane, right;

	private Admin() {
		createLeftPanel();
		createRightPanel();
		createMainPane();
		frame.add(mainPane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Admin Window");
		frame.setSize(800, 500);
		frame.setVisible(true);
	}
	
	private void createLeftPanel() {
				
		root = new UserGroup("Root");
		root.setList(temp);
				
		userList = new JTree(root);
		userList.getSelectionModel().setSelectionMode
			(TreeSelectionModel.SINGLE_TREE_SELECTION);
	
		
		scrollPane = new JScrollPane(userList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension( 200, 400));
		
		
		pL.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void createRightLowerPanel() {
		userTotal = new JButton("Show User Total");
		groupTotal = new JButton("Show Group Total");
		messageTotal = new JButton("Show Message Total");
		positivePercentage = new JButton("Show Positive Percentage");
		
		userTotal.addActionListener(this);
		groupTotal.addActionListener(this);
		messageTotal.addActionListener(this);
		positivePercentage.addActionListener(this);
		
		JSplitPane tmp1 = new JSplitPane(SwingConstants.HORIZONTAL, userTotal, messageTotal);
		JSplitPane tmp2 = new JSplitPane(SwingConstants.HORIZONTAL, groupTotal, positivePercentage);
		
		JSplitPane tmp3 = new JSplitPane(SwingConstants.VERTICAL, tmp1, tmp2);
		
		tmp3.setResizeWeight(0.5);
		
		tmp1.setEnabled(false);
		tmp2.setEnabled(false);
		tmp3.setEnabled(false);
		
		pRightLower.add(tmp3);
	}
	
	private void createRightUpperPanel() {
		userID = new JTextField("", 16);
		groupID = new JTextField("", 16);
		
		addUser = new JButton("Add User");
		addGroup = new JButton("Add Group");
		openUser = new JButton("Open User View");
		
		addUser.addActionListener(this);
		addGroup.addActionListener(this);
		openUser.addActionListener(this);
		
		JSplitPane tmp1 = new JSplitPane(SwingConstants.HORIZONTAL, userID, addUser);
		JSplitPane tmp2 = new JSplitPane(SwingConstants.HORIZONTAL, groupID, addGroup);
		JSplitPane tmp3 = new JSplitPane(SwingConstants.VERTICAL, tmp1, tmp2);
		JSplitPane tmp4 = new JSplitPane(SwingConstants.HORIZONTAL, tmp3, openUser);
		
		tmp1.setEnabled(false);
		tmp2.setEnabled(false);
		tmp3.setEnabled(false);
		tmp4.setEnabled(false);
		
		pRightUpper.add(tmp4);
	}
	
	private void createRightPanel() {
		createRightUpperPanel();
		createRightLowerPanel();
		right = new JSplitPane(SwingConstants.HORIZONTAL, pRightUpper, pRightLower);
		
		right.setResizeWeight(.75);
		right.setEnabled(false);
	}
	
	private void createMainPane() {
		mainPane = new JSplitPane(SwingConstants.VERTICAL, pL, right);
		mainPane.setDividerLocation(250);
		mainPane.setEnabled(false);
	}
	
	private void addUser(DefaultMutableTreeNode top, String name) {
		User tmp = new User(name);
		top.add(tmp);
		
		UserGroup userGroup = (UserGroup) top;
		List<GroupComponent> tmpList = userGroup.getList();
		tmpList.add((GroupComponent)tmp);
		
		//Let JTree know that TreeModel was updated with new node
		((DefaultTreeModel) userList.getModel()).
			nodesWereInserted(top,new int[]{top.getChildCount()-1});
	}
	
	private void addGroup(DefaultMutableTreeNode top, String name) {
		UserGroup tmp = new UserGroup(name);
		List<GroupComponent> newList = new ArrayList<>();
		tmp.setList(newList);
		top.add(tmp);
		
		UserGroup userGroup = (UserGroup) top;
		List<GroupComponent> tmpList = userGroup.getList();
		tmpList.add((GroupComponent)tmp);
		
		//Let JTree know that TreeModel was updated with new node
		((DefaultTreeModel) userList.getModel()).
		nodesWereInserted(top,new int[]{top.getChildCount()-1});

	}
	
	private void addUserButtonClicked() {
		String uID = userID.getText();
		if (uID == "") {
			return;
		}
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
			 userList.getLastSelectedPathComponent();
		
		//No location selected
		if(node == null) { 
			addUser(root, uID);
		}
		else {
			//If it is a group
			//Add to group
			if(node.getAllowsChildren() == true) {
				addUser(node, uID);
			}
			//Otherwise it is a child
			//Add to parent node
			else {
				UserGroup tmp = (UserGroup) node.getParent();
				addUser(tmp, uID);
			}
		}
	}
	
	private void addGroupButtonClicked() {
		String gID = groupID.getText();
		if(gID == "") {
			return;
		}
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
				 userList.getLastSelectedPathComponent();
		
		//No Selected Location
		if(node == null) {
			addGroup(root, gID);
		}
		else {
			//Adding to Selected Group
			if(node.getAllowsChildren() == true) {
				addGroup(node, gID);
			}
			//If it is a child then find it's parent
			else {
				UserGroup tmp = (UserGroup) node.getParent();
				addGroup(tmp, gID);
			}
		}
	}
	
	private void openUserButtonClicked() {
		GroupComponentVisitor openUser = new OpenUser();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
				 userList.getLastSelectedPathComponent();
		
		//Has to select some user
		if(node != null) {
			//Has to be user and not a group
			if(node.getAllowsChildren() == false) {
				//Open User View
				openUser.visitUser((User)node);
			}
		}
	}
	
	private void userTotalButtonClicked() {
		UserTotal userTotal = new UserTotal();
		
		root.accept(userTotal);
		
		//Redo for UI Later
		System.out.println(userTotal.getCount());
	}
	
	private void groupTotalButtonClicked() {
		GroupTotal groupTotal = new GroupTotal();
		
		root.accept(groupTotal);
		
		//Redo for UI Later
		System.out.println(groupTotal.getCount()-1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addUser) {
				addUserButtonClicked();
			}
			else if(e.getSource() == addGroup) {
				addGroupButtonClicked();
			}
			else if(e.getSource() == openUser) {
				openUserButtonClicked();
			}
			else if(e.getSource() == userTotal) {
				userTotalButtonClicked();
			}
			else if(e.getSource() == groupTotal) {
				groupTotalButtonClicked();
			}
			else if(e.getSource() == messageTotal) {
				
			}
			else if(e.getSource() == positivePercentage) {
				
			}
			else {
				System.out.println("Unexpected " + e.getSource());
			}
	}
	
	public JTree getUserList() {
		return userList;
	}
	public UserGroup getRoot(){
		return root;
	}
	
	public static Admin getInstance() {
		if (adminInstance == null) {
			adminInstance = new Admin();
		}
		return adminInstance;
	}
	

}
