import java.util.ArrayList;

public class Planet implements Mineable {
	String name;
	int size;
	int popSize;
	ArrayList<Moon> moons;
	
	Planet(){
		this("Name unknown", 100, 500);
	}
	Planet(String name, int size, int popSize){
		this(name, size, popSize, null);
	}
	Planet(String name, int size, int popSize, ArrayList<Moon> moons){
		this.name = name;
		this.size = size;
		this.popSize = popSize;
		this.moons = moons;
	}
	String getName(){
		return name;
	}
	int getSize(){
		return size;
	}
	int getPopSize(){
		return popSize;
	}
	public String toString(){
		String strings="";
		for(Moon moo : moons){
			strings += "\t" + moo.toString();
		}
		return(this.name + ", " + this.size + ", " + this.popSize + ", " + strings);
	}
	public float mineMe(){
		float mineableAmmount = size/ 10;
		System.out.println("Ammount of mineable materials is " + mineableAmmount);
		return mineableAmmount;
	}
}
