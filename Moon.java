
public class Moon implements Mineable{
	String Name;
	int size;
	String Physical_composition;
	public Moon moon2;
	
	public Moon(){
		this.Name = "unknown";
		this.size = 0;
		this.Physical_composition ="unknown";		
	}
	public Moon(String name, int size, String physical_composition){
		this.Name = name;
		this.size = size;
		this.Physical_composition = physical_composition;	
	}
	
	String getName(){
		return Name;
	}
	int getSize(){
		return size;
	}

	String getPhysical_composition(){
		return Physical_composition;
	}
	
	public String toString(){
		return(this.Name +","+ this.Physical_composition +","+ this.size);
	}
	
	public float mineMe(){
		float mineableAmmount = size/ 10;
		System.out.println("Ammount of mineable materials  is " + mineableAmmount);
		return mineableAmmount;
	}
}
