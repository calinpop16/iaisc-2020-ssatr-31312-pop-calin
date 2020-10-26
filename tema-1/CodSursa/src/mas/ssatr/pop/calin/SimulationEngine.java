package mas.ssatr.pop.calin;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import mas.ssatr.pop.calin.models.Input;
import mas.ssatr.pop.calin.models.Node;
import mas.ssatr.pop.calin.models.Output;
import mas.ssatr.pop.calin.models.PetriNet;
import mas.ssatr.pop.calin.models.Transition;

public class SimulationEngine {
	
	private boolean active=true;
	private int clock=0;
	private String marcajInitial;
	private String marcajCurent;
	private int deadlockCount;
	
	public void simulate(PetriNet model) {
		//generam semnal de tact, la fiecare secunda se va genera un astfel de semnal. La fiecare semnal
		//verificam modelul, adica verificam ce tranzitii se pot executa, modificam marcajul, afisam marcajul
		while(active) {
			clock++;
			active=evaluate(model,clock);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean evaluate(PetriNet model,int clock) {
		if (clock==1) {
		marcajInitial=model.getMark();
		// de adaugat scrierea in fisier
		WriteToFile.writeToFile("Marcajul initial: "+marcajInitial);
		System.out.println("Marcajul initial: "+marcajInitial);
		model.setTransitionTime();
		}
		deadlockCount=0;
		for(int i=0;i<model.getTransitions().size();i++) {
			Transition t = model.getTransitions().get(i);
			List<Input> in =t.getInputs();
			List<Output> out=t.getOutputs();
			List<Node> inputNodes= new ArrayList<>();
			List<Node> outputNodes=new ArrayList<>();
			//in inputNodes si outputNodes vom avea nodurile de la id-urile de la intrarile si iesirile din tranzitii
			for(int j=0;j<in.size();j++) {
				inputNodes.add(model.getNodeById(in.get(j).getId()));
			}
			for(int z=0;z<out.size();z++) {
				outputNodes.add(model.getNodeById(out.get(z).getId()));
			}
			if(t.executaTranzitia(inputNodes, outputNodes)) {
				marcajCurent=model.getMark();
				//de adaugat scrierea in fisier
				WriteToFile.writeToFile("Marcajul Curent: " + marcajCurent);
				System.out.println("Marcajul Curent: " + marcajCurent);
			}
			else {
				deadlockCount++;
			}
		}
		if(marcajInitial.equals(marcajCurent)) {
			WriteToFile.writeToFile("Marcaj curent = marcaj initial");
			System.out.print("Marcaj curent = marcaj initial");
			return false;
		}
		if(deadlockCount==model.getTransitions().size()) {
			WriteToFile.writeToFile("Deadlock");
			System.out.print("Deadlock");
			return false;
		}
		return true;
	}
	
	public static void main (String[] args) throws FileNotFoundException {
		PetriNetLoader loader = new PetriNetLoader();
		PetriNet model = loader.dataFromJson();
		
		SimulationEngine se = new SimulationEngine();
		se.simulate(model);
	}
	
}
