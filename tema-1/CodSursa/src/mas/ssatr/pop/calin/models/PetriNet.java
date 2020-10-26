package mas.ssatr.pop.calin.models;

import java.util.List;

public class PetriNet {
	
	private List<Node> nodes=null;
	private List<Transition> transitions=null;
	
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	
	
	public String getMark() {
		//parcurgem nodurile si salvam marcajul;
		String mark="";
		for(int i=0;i<nodes.size();i++) {
			Node n= nodes.get(i);
			mark = mark + n.getJeton() + " ";
		}
		return mark;
	}
	
	public void setTransitionTime() {
		//setam timpul pentru fiecare tranzitie, timp generat de o functie random intre tmin si tmax
        for (Transition transition : transitions) {
            transition.setTransitionTime(transition.generateRandomTemp());
        }
    }
	
	public Node getNodeById(String id) {
		 for (Node node : nodes) {
	            if (node.getId().equals(id)) {
	                return node;
	            }
	        }
	        return null;
	}	
}
