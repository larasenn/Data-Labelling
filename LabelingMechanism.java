package p;
import java.util.ArrayList;

abstract class LabelingMechanism {// Creates an abstract method
	public abstract Assignment makeAssignment(Instance ins, Dataset dataset, User user,LabelConsistency lc);
}
