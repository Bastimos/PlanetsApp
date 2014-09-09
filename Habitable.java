import java.util.ArrayList;

public class Habitable extends Planet{

	private String percOfWater;

	Habitable(){
	}
	Habitable(String name, int size, int population, String water, ArrayList<Moon> moons){
		super(name, size, population, moons);
		this.percOfWater = water;
	}
	public String getWater(){
		return percOfWater;
	}
	public String toString(){
		return(this.name + ", " + this.size + ", " + this.popSize + ", " + this.percOfWater + ", " + this.moons);
		
	}
}

