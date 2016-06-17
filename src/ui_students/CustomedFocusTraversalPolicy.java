package ui_students;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

public class CustomedFocusTraversalPolicy extends FocusTraversalPolicy{
	
	private Vector<Component> order;
	
	public CustomedFocusTraversalPolicy(Vector<Component> order) {
		this.order = new Vector<>(order.size());
		this.order.addAll(order);
	}

	 @Override
	public Component getComponentAfter(Container focusCycleRoot,
	             Component aComponent)
	{
		 int idx = (order.indexOf(aComponent) + 1) % order.size();
		 return order.get(idx);
	}
	
	@Override
	public Component getComponentBefore(Container focusCycleRoot,
	              Component aComponent)
	{
		int idx = order.indexOf(aComponent) - 1;
		if (idx < 0) {
		idx = order.size() - 1;
		}
		return order.get(idx);
	}
	
	@Override
	public Component getDefaultComponent(Container focusCycleRoot) {
		return order.get(0);
	}
	
	@Override
	public Component getLastComponent(Container focusCycleRoot) {
		return order.lastElement();
	}
	
	@Override
	public Component getFirstComponent(Container focusCycleRoot) {
		return order.get(0);
	}

}
