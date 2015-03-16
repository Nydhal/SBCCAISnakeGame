package AI;

import java.util.Arrays;

import AI.Zach.Seeker;

public class AIList {
	// ADD YOUR AI TO THIS LIST
	static public AI[] list = {
		new DummyAI(), 
		new Seeker()
	};
	
	static public String[] asArray() {
		String[] out = new String[list.length];
		
		for (int i = 0; i < out.length; i++)
			out[i] = list[i].getName() + " " + Arrays.toString(list[i].getAuthors());
		
		return out;
	}
}
