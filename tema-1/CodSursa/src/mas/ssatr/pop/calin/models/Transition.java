package mas.ssatr.pop.calin.models;

import java.util.List;

public class Transition {
	
	
	    private List<Input> inputs = null; 
	    private List<Output> outputs = null; 
	    private int tmin;  
	    private int tmax;
	    private int transitionTime;

	    public List<Input> getInputs() {
	        return inputs;
	    }

	    public void setInputs(List<Input> inputs) {
	        this.inputs = inputs;
	    }

	    public List<Output> getOutputs() {
	        return outputs;
	    }

	    public void setOutputs(List<Output> outputs) {
	        this.outputs = outputs;
	    }

	    public int getTmin() {
	        return tmin;
	    }

	    public void setTmin(int tmin) {
	        this.tmin = tmin;
	    }

	    public int getTmax() {
	        return tmax;
	    }

	    public void setTmax(int tmax) {
	        this.tmax = tmax;
	    }

	    public void setTransitionTime(int transitionTime) {
	        this.transitionTime = transitionTime;
	    }

	    public int getTransitionTime() {
	        return transitionTime;
	    }
	    
	    public int generateRandomTemp() {
	        if (tmin == tmax) {
	            return tmin;
	        } else {
	            return (tmin + (int) (Math.random() * ((tmax - tmin) + 1)));
	        }
	    }
	    
	    public boolean executabil(List<Node> nodes) {
	    	//functia verifica daca o tranzitie poate fi executabila sau nu 
	    	for(int i=0;i <nodes.size(); i++ ) {
	    		Node n= nodes.get(i);
	    		if(n.getJeton()<=0) {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	    
	    public boolean executaTranzitia(List<Node> inputNodes, List<Node> outputNodes) {
	    	if(!executabil(inputNodes)) {
	    		return false;
	    	}
	    	//programul nu va rula daca pe arce exista costuri, consideram ca pe fiecare arc costul este 1
	    	int countTime=0;
	    	//tranzitie temporizata = grosso modo, jetonul pleaca din locatia de intrare, "sta" in tranzitie x timp dupa care jetonul apare in locatia de iesire
	    	//la fiecare tact, apelam functia aceasta. Jetonul va pleca din locatie, dupa care asteptam x timp si punem jetonul in locatia de iesire
	    	for(int i=0;i<inputNodes.size();i++) {
	    		Node n=inputNodes.get(i);
	    		n.setJeton(n.getJeton()-1);
	    	}
	    	for(int i=0; i<transitionTime;i++) {
	    		countTime++;
	    		if(countTime==transitionTime) {
	    			for(int j=0;j<outputNodes.size();j++) {
	    				Node n1=outputNodes.get(j);
	    				n1.setJeton(n1.getJeton()+1);
	    			}
	    		}
	    	}
	    	return true;
	    	
	    }

}
