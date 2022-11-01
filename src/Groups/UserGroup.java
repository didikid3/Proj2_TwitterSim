package Groups;

import java.util.List;

public class UserGroup implements GroupComponent{
	private List<GroupComponent> groupComponent;
	
	public List<GroupComponent> getList(){
		return groupComponent;
	}
	
	public void setList(List<GroupComponent> groupComponent) {
		this.groupComponent = groupComponent;
	}
	
	private String groupName;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String name) {
		groupName = name;
	}
	
}
