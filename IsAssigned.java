package p;

public class IsAssigned {
	private boolean isAsssigned;
	private Instance instance;
	private User user;
	
	public IsAssigned(boolean isAsssigned) {
		super();
		this.isAsssigned = isAsssigned;
		
	}

	public boolean isAsssigned() {
		return isAsssigned;
	}

	public void setAsssigned(boolean isAsssigned) {
		this.isAsssigned = isAsssigned;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
