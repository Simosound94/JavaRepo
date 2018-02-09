
public class Writer implements Runnable{

	public String name;
	public Resource res;
	
	public Writer(String name, Resource res){
		this.name = name;
		this.res = res;
	}
	
	@Override
	public void run() {
		while(true){
			try{
			System.out.println("Thread " + name + " tries to write");
			RisorsaString newValue = new RisorsaString(name);
			res.write(newValue, name);
			System.out.println("Thread " + name + " finishes write");

			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
