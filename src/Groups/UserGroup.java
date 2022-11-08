package Groups;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import VisitUser.GroupComponentVisitor;

@SuppressWarnings("serial")
public class UserGroup extends DefaultMutableTreeNode implements GroupComponent{
	private List<GroupComponent> groupComponent;
	private String groupName;
	
	public UserGroup(String groupName) {
		setName(groupName);
	}
	
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	
	public void accept(GroupComponentVisitor visitor) {
		visitor.visitUserGroup(this);
		for(GroupComponent gc : groupComponent) {
			gc.accept(visitor);
		}
	}
	
	public String getName() {
		return groupName;
	}
	public void setName(String name) {
		groupName = name;
	}
	
	public User findUser(String name) {
		User tmp = null;
		for(GroupComponent gc: groupComponent) {
			tmp = gc.findUser(name);
			if(tmp != null) {
				break;
			}
		}
		
		return tmp;
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
