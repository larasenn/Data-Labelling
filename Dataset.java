package p;

import java.util.ArrayList;

class Dataset {

	private int datasetID;
	private String datasetName;
	private int maxLabel;
	private ArrayList<Instance> instanceList = new ArrayList<Instance>();
	private ArrayList<Label> labelList = new ArrayList<Label>();
	private CompletenessPercentage completenessPercentage;
	private ArrayList<User> assignedUserList = new ArrayList<User>();

	public Dataset(int datasetID, String datasetName, int maxLabel, ArrayList<Instance> instanceList,
			ArrayList<Label> labelList, ArrayList<User> assignedUserList) {
		super();
		this.datasetID = datasetID;
		this.datasetName = datasetName;
		this.maxLabel = maxLabel;
		this.instanceList = instanceList;
		this.labelList = labelList;
		this.assignedUserList = assignedUserList;
	}

	public int getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(int datasetID) {
		this.datasetID = datasetID;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

	public int getMaxLabel() {
		return maxLabel;
	}

	public void setMaxLabel(int maxLabel) {
		this.maxLabel = maxLabel;
	}

	public ArrayList<Instance> getInstanceList() {
		return instanceList;
	}

	public void setInstanceList(ArrayList<Instance> instanceList) {
		this.instanceList = instanceList;
	}

	public ArrayList<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(ArrayList<Label> labelList) {
		this.labelList = labelList;
	}

	public CompletenessPercentage getCompletenessPercentage() {
		return completenessPercentage;
	}

	public void setCompletenessPercentage(CompletenessPercentage completenessPercentage) {
		this.completenessPercentage = completenessPercentage;
	}

	public ArrayList<User> getAssignedUserList() {
		return assignedUserList;
	}

	public void setAssignedUserList(ArrayList<User> assignedUserList) {
		this.assignedUserList = assignedUserList;
	}

	// ***********************************************************

	public double calculateCompletenessPercentage() {

		int total = 0;
		for (int i = 0; i < getInstanceList().size(); i++) {
			if (getInstanceList().get(i).getIsAsssigned().isAsssigned() == true)
				total = total + 1;
		}
		double percentage = ((double) ((double) total / (double) getInstanceList().size() * 100));
		// System.out.println("Completeness Percentage: " + percentage);
		return percentage;
	}

	public double sizeofUser() {
		int size = assignedUserList.size();

		return size;

	}
	public int instanceListSize() {
		int size =instanceList.size();
		
		return size;
	}
	public void addUser (User u) {
		assignedUserList.add(u);
	}
	
	public int userID(int i) {
		int id = assignedUserList.get(i).getUserID();
		
		return id;
	}
}