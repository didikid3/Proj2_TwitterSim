package Groups;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import VisitUser.GroupComponentVisitor;

@SuppressWarnings("serial")
public class UserGroup extends DefaultMutableTreeNode implements GroupComponent{
	private List<GroupComponent> groupComponent;
	private String groupName;
	
	public UserGroup(String groupName) {
		super();
		setName(groupName);
	}
	
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	
	public void accept(GroupComponentVisitor visitor) {
		visitor.visitUserGroup(this);
	}
	
	public String getName() {
		return groupName;
	}
	public void setName(String name) {
		groupName = name;
	}
	
	
	
	public List<GroupComponent> getList(){
		return groupComponent;
	}
	
	public void setList(List<GroupComponent> groupComponent) {
		this.groupComponent = groupComponent;
	}
	
	
	
	public String toString() {
		return groupName;
	}
	
	
	
}
