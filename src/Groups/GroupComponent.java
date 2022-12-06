package Groups;

import VisitUser.Visitable;

public interface GroupComponent extends Visitable{
	public String getName();
	public void setName(String name);
	public User findUser(String name);
	public long getCreateTime();
}
