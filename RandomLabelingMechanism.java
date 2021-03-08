package p;
import java.util.ArrayList;
import java.util.Random;
//import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class RandomLabelingMechanism extends LabelingMechanism {

	// ArrayList<User> userList = new ArrayList<User>();
	public Assignment makeAssignment(Instance ins, Dataset dataset, User user, LabelConsistency lc
			) {

		int maxLabel = dataset.getMaxLabel();
		Output out = new Output();
		ArrayList<Label> labelList = new ArrayList<Label>(dataset.getLabelList());
		ArrayList<Label> assignedLabels = new ArrayList<Label>();
		LocalDateTime now = LocalDateTime.now();
		
		int size = labelList.size();

		Random r = new Random();
		int index = r.nextInt(size);
		Label firstLabel = labelList.get(index);
		assignedLabels.add(firstLabel);
		int count = firstLabel.getCount() + 1;
		firstLabel.setCount(count);
		finalLabel(labelList);
		double probability = size * lc.getConsistencyCheckProbability();
		labelList.remove(index);
		for (int i = 0; i < maxLabel - 1; i++) {

			int randomLabel = r.nextInt(labelList.size());

			if (randomLabel <= probability) {
				labelList.add(firstLabel);
				Label assignedLabel = labelList.get(randomLabel);
				count = assignedLabel.getCount() + 1;
				assignedLabel.setCount(count);
				assignedLabels.add(assignedLabel);
				if (assignedLabel.getLabelID() == firstLabel.getLabelID()) {
					labelList.remove(assignedLabel);

				} else {
					labelList.remove(assignedLabel);
					labelList.remove(firstLabel);
				}

			} else {
				Label assignedLabel = labelList.get(randomLabel);
				assignedLabels.add(assignedLabel);
				count = assignedLabel.getCount() + 1;
				assignedLabel.setCount(count);
				labelList.remove(assignedLabel);

			}
			finalLabel(labelList);
		}
		Assignment assignment = new Assignment(user, assignedLabels, now);
		return assignment;
	}

	public void finalLabel(ArrayList<Label> list) {
		int max = 0;
		Label finalLabel;
		ArrayList<Label> maxList = new ArrayList<Label>();
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getCount() > max)
				max = list.get(i).getCount();

		}
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getCount() == max)
				maxList.add(list.get(i));
		}

		if (maxList.size() > 1) {
			Random r = new Random();
			int randomLabel = r.nextInt(maxList.size());
			finalLabel = maxList.get(randomLabel);

		} else {
			finalLabel = maxList.get(0);
		}

	}

	

}