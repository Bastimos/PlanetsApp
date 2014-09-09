import java.util.ArrayList;


public class Uninhabitable extends Planet{

	String atmosphericComposition;
	
	 public Uninhabitable() {
	 }
	 public  Uninhabitable(String name, int size, int population, String atmosphericComposition, ArrayList<Moon> moons) {
		 super(name, size, population, moons);
		 this.atmosphericComposition = atmosphericComposition;
	 }
	 
	 public String getAtmosphericComposition(){
		 return atmosphericComposition;
	 }
	 
	 public String toString(){
		
		 return(this.name + ", " + this.size + ", " + this.popSize + ", " + this.atmosphericComposition + ", " + this.moons);
	 }
}
