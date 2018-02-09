package eserc;
import java.util.Scanner;


public class Temperature {
	

	public static Scanner input =new Scanner(System.in);
	public static void main(String[] args) {
			int[][] temp = new int[30][12];
			Temperature.inserimento(temp);
			int scelta = Temperature.scriviMenu();
			switch(scelta){
			case 1:{
				Temperature.op1(temp);
				break;
				}
			case 2:{
				Temperature.op2(temp);
				break;
				}
			case 3:{
				Temperature.op3();
				break;
				}
			}
			
			
	}
	
	
	
	
	
	public static void inserimento(int[][] temp){
		for(int i = 0; i<30; i++){
			for(int j = 0; j<12; j++){
				System.out.println("mese: "+(j+1)+" giorno: "+(i+1));
				temp[i][j] = input.nextInt();
			}
		}
	}
	
	
	public static int scriviMenu(){
		System.out.println("scegli:");
		return input.nextInt();
	}
	
	
	public static void op1(int [][] temp){
		
		int i,j,app;
		
		int giorni[]=new int[12];
		int mese[]={1,2,3,4,5,6,7,8,9,10,11,12};
	
		System.out.println("quale giorno vuoi ricercare?");
		int ric=input.nextInt();
		
		for(i=0;i<12;i++){
			
			giorni[i]=temp[ric][i];
			
		}//for i	
		
		for(i=0;i<11;i++){
			
			for(j=i+1;j<12;j++){
				
				if(giorni[i]<giorni[j]){
				
					app=giorni[i];
					giorni[i]=giorni[j];
					giorni[j]=app;
					
					app=mese[i];
					mese[i]=mese[j];
					mese[j]=app;
				
				}//if
				
			}//for j
		
		}//for i
		
		for(i=0;i<12;i++){
			
			System.out.println("mese: "+mese[i]+" temperatura: "+giorni[i]);
			
		}//for i
		
	}
	
	public static void op2(int temp[][]){
	
		int i,j;
		
		System.out.println("quale giorno vuoi ricercare?");
		int giorno=input.nextInt();
		
		System.out.println("di quale mese?");
		int mese=input.nextInt();
		
		giorno--;
		mese--;
		int somma = 0;
		for(i=giorno-1;i<= giorno+1;i++){
			
			for(j=mese-1;j<= mese+1;j++){
				if(i>=0 && i<30 && j>=0 && j<12){
					somma += temp[i][j];
				}
			}
		}
		
	}

	public static void op3(){
	
	}
	

}
