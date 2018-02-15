package classes;

import java.util.ArrayList;

public abstract class Item {
	// abstract messages
	private transient ArrayList<Msg> messages = new ArrayList<Msg>();
	private ArrayList<Integer> mIds = new ArrayList<Integer>();

	public Item() {}
	public Item(ArrayList<Msg> msgs) {
		if (msgs != null && !msgs.isEmpty()) {
			this.setMessages(msgs);	
		}
	}
	public ArrayList<Msg> getMessages() {
		return this.messages;
	}
	public ArrayList<Integer> getMIds() {
		return this.mIds;
	}
	public void setMessages(ArrayList<Msg> messages) {
		this.messages = messages;
		for (int iterator = 0; iterator < this.messages.size(); iterator++) {
			Msg msg = this.messages.get(iterator);
			this.mIds.add(msg.getId());
		}
	}
	public void addMessage(Msg m) {
		if (!this.messages.contains(m)) {
			this.messages.add(m);
		if (!this.mIds.contains(m.getId()))
			this.mIds.add(m.getId());
		}
	}
	public void deleteMessage(Msg m) {
		if (this.messages.contains(m)) {
			this.messages.remove(m);
		if (this.mIds.contains(m.getId()))
			this.mIds.remove(m.getId());
		}
	}
}
