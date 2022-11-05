package Groups;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class Subject extends DefaultMutableTreeNode{
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(Observer o: observers) {
			o.update(this);
		}
	}
	
}
