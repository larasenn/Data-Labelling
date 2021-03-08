package p;
public class LabelConsistency {
	
	private Label label;
	private double consistencyCheckProbability = 0.1; 
	
	public LabelConsistency(double consistencyCheckProbability) {
		super();
		this.consistencyCheckProbability = consistencyCheckProbability;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public void setLabel(Label label) {
		this.label = label;
	}
	public double getConsistencyCheckProbability() {
		return consistencyCheckProbability;
	}
	public void setConsistencyCheckProbability(double consistencyCheckProbability) {
		this.consistencyCheckProbability = consistencyCheckProbability;
	}
	
	
}
