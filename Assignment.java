package p;
import java.util.ArrayList;
import java.time.LocalDateTime;

class Assignment {

	private User User;
	private ArrayList<Label> labelList = new ArrayList<Label>();
	private LocalDateTime DateTime;

	


	public Assignment(p.User user, ArrayList<Label> labelList, LocalDateTime dateTime) {
		super();
		User = user;
		this.labelList = labelList;
		DateTime = dateTime;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public ArrayList<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(ArrayList<Label> labelList) {
		this.labelList = labelList;
	}

	public LocalDateTime getDateTime() {
		return DateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		DateTime = dateTime;
	}
	
	public void addLabelList(ArrayList<Label> labelList) {
		setLabelList(labelList);
	}

}