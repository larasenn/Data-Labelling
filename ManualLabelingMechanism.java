package p;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ManualLabelingMechanism extends LabelingMechanism {

	private Label label;

	public Assignment makeAssignment(Instance ins, Dataset dataset, User user, LabelConsistency lc) {

		LocalDateTime now = LocalDateTime.now();
		if (ins.getAssignmentList().size() == 0) {
			ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
			ArrayList<Label> assignedLabels = new ArrayList<Label>();
			assignedLabels.add(this.getLabel());
			Assignment a = new Assignment(user, assignedLabels, now);
			assignmentList.add(a);
			ins.setAssignmentList(assignmentList);
			
			return a;
		} else {
			ins.getAssignmentList().get(0).getLabelList().add(this.getLabel());

		}

		Assignment a = null;
		return a;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
