package p;

public class CompletenessPercentage {

	private double percentage;
	private User user;
	public CompletenessPercentage(double percentage, User user) {
		super();
		this.percentage = percentage;
		this.user = user;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		
	}
	
}
