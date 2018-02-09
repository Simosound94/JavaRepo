package eserc;

public class Prova3 {

	public static void main(String[] args) {
		int[][] matr1 = new int[10][10];

		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				matr1[i][j] = (int) (Math.random()*10);
			}
		}
		
		//Media riga 3
		int i = 3;
		int sum = 0;
		for(int j = 0; j<10; j++){
		 sum += matr1[i][j];
		}
		System.out.println("La media riga 3 è: "+sum/10);
		
		
		
		//Media colonna 6
		int j = 6;
		sum = 0;
		for(i = 0; j<10; j++){
		 sum += matr1[i][j];
		}
		System.out.println("La media colonna 6 è: "+sum/10);
	
		
		
		//Operazioni tra matrici
		int[][] matr2 = new int[10][10];

		for(i = 0; i<10; i++){
			for(j = 0; j<10; j++){
				matr1[i][j] = (int) (Math.random()*10);
			}
		}
		
		//Somma elementi 5 6
		int ris = matr1[5][6] + matr2[5][6];
		
		
		
	}

}
