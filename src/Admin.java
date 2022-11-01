import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;


public class Admin implements ActionListener{
	private static Admin adminInstance; 
	
	private JFrame frame = new JFrame();
	
	private JButton addUser, addGroup, openUser;
	private JButton userTotal, groupTotal, messageTotal, positivePercentage;
	
	private JTextField userID, groupID;

	private DefaultMutableTreeNode root;
	private JTree userTree; 
	
	
	private JPanel pL = new JPanel();
	private JPanel pRightUpper = new JPanel();
	private JPanel pRightLower = new JPanel();
	
	private JSplitPane mainPane, right;

	private Admin() {
	
		root =  new DefaultMutableTreeNode("Root");
		userTree = new JTree(root);
		pL.add(userTree);
		
		createRightPanel();
		createMainPane();
		frame.add(mainPane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Admin Window");
		frame.setSize(800, 300);
		frame.setVisible(true);
	}
	
	private void createRightLowerPanel() {
		userTotal = new JButton("Show User Total");
		groupTotal = new JButton("Show Group Total");
		messageTotal = new JButton("Show Message Total");
		positivePercentage = new JButton("Show Positive Percentage");
		
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
		userID = new JTextField("New User Name", 16);
		groupID = new JTextField("New Group Name", 16);
		
		addUser = new JButton("Add User");
		addGroup = new JButton("Add Group");
		openUser = new JButton("Open User View");
		
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
	
	public static Admin getInstance() {
		if (adminInstance == null) {
			adminInstance = new Admin();
		}
		return adminInstance;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	

}
