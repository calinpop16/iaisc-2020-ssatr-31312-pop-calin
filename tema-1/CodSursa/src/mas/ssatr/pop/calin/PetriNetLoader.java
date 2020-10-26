package mas.ssatr.pop.calin;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import mas.ssatr.pop.calin.models.PetriNet;


public class PetriNetLoader {
	
	public PetriNet dataFromJson () throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader;
		reader = new JsonReader(new FileReader("petri_net.json"));
		PetriNet data = gson.fromJson(reader, PetriNet.class); //data- pachetul json, fiecare element este sub forma de lista
		return data; // returnam pachetul de date sosit din json	
	}

}
