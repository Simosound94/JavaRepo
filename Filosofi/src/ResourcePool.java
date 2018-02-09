import java.util.LinkedList;

public class ResourcePool {
	
	private LinkedList<Forchetta> resources;
	private int resDisp;
	private int maxRes;
	
	public ResourcePool(int numRes){
		this.resDisp = numRes;
		this.maxRes = numRes;
		resources = new LinkedList<Forchetta>();
		for(int i = 0; i<numRes; i++){
			resources.add(new Forchetta(i));
		}
	}
	
	public LinkedList<Forchetta> getResources(int numRes){
		if(numRes > maxRes){
			return null;
		}
		
		LinkedList<Forchetta> resp = new LinkedList<Forchetta>();
		try{
			synchronized(this){
				while(resDisp < numRes){
					this.wait();
				}
				int respItem = 0;
				for(Forchetta f : resources){
					if(!f.isBusy()){
						f.setBusy();
						System.out.println("Resource #"+f.name+" busy");
						resp.add(f);
						respItem++;
						resDisp--;
						if(respItem == numRes){
							break;
						}
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}
		return resp;
	}

	public synchronized void releaseResources(LinkedList<Forchetta> res){
		for(Forchetta f : res){
			f.setFree();
			System.out.println("Resource #"+f.name+" free");
			resources.add(f);
			resDisp++;
		}
		res = null;
		this.notifyAll();
	}
	
}
