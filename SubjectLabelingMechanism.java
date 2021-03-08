package p;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class SubjectLabelingMechanism extends LabelingMechanism {

	public Assignment makeAssignment( Instance ins,Dataset dataset, User user, LabelConsistency lc) {
		int maxLabel = dataset.getMaxLabel();
		ArrayList<Label> assignedLabels = new ArrayList<Label>();
		ArrayList<Label> labels=new ArrayList<Label>();
		labels=dataset.getLabelList();
		LocalDateTime now = LocalDateTime.now();
		ArrayList<String> words = new ArrayList<String>();
		
		words=ins.words();
		int size = labels.size();
		
		Random r = new Random();
		for (int i = 0; i < maxLabel ; i++) {
			
		for(int j=0;j<words.size();j++) {
			if(words.get(j).equals(labels.get(i))) {
				assignedLabels.add(labels.get(i));
			    break;
			}
		}
		
		}
		int rest =maxLabel-assignedLabels.size();
		for (int i = 0; i < rest ; i++) {
			int randomLabel = r.nextInt(labels.size());
			Label assignedLabel = labels.get(randomLabel);
			assignedLabels.add(assignedLabel);
		
		}
		
		
		Assignment assignment = new Assignment(user, assignedLabels, now);
		return assignment;
	}
}
