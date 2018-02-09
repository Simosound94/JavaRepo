import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.PrintWriter;

public class Matrice {
	public float matrice[][];
	int r,c; //    /!\ NON SERVE IL PUBLIC Anche il main è dentro la classe,
			// sono sempre dentro la classe, non vi accedo dall'esterno
	
	public Matrice(int ri, int co){
		matrice = new float[ri][co];
		r=ri;
		c=co;
	}
	
	public Matrice mol (Matrice matr){
		if(c != matr.r){
			System.err.println("Unable to moltiplicate, wrong dimensions.");
			System.exit(1);
		}
		int riga,colonna;
		Matrice ris = new Matrice(r, matr.c);
		for(riga=0;riga<r;riga++){
			for(colonna=0;colonna<matr.c;colonna++){
				ris.matrice[riga][colonna]=0;
				for(int k=0;k<c;k++)
				ris.matrice[riga][colonna]+=matrice[riga][k]*matr.matrice[k][colonna];
			}
		}
		return ris;

	}
	
	public static void main(String[] args) throws Exception{
		if(args.length < 2){
			System.out.println("Please, insert 2 files source, and file to create.");
			System.exit(0);
		}
		FileReader Filematr0 = new FileReader(args[0]);
		FileReader Filematr1 = new FileReader(args[1]);
		FileWriter out = new FileWriter(args[2]);
		Scanner input = new Scanner(Filematr0);
		int r,c;
		Matrice mat1, mat2;
		
		r=input.nextInt();
		c=input.nextInt();
		mat1=new Matrice(r,c);
		System.out.print("Oggetto mat1: "+mat1.toString());
		for(int i=0; i<r;i++)
			for(int j=0;j<c;j++)
				mat1.matrice[i][j]=input.nextFloat();
		//Garbage collector per il flusso Filematr0 credo
		input = new Scanner(Filematr1);
		r=input.nextInt();
		c=input.nextInt();
		mat2=new Matrice(r,c);
		for(int i=0; i<r;i++)
			for(int j=0;j<c;j++)
				mat2.matrice[i][j]=input.nextFloat();
		input.close();
		Filematr0.close();
		Filematr1.close();
		Matrice ris = new Matrice(mat1.r,mat2.c);
		ris= mat1.mol(mat2);
		PrintWriter print = new PrintWriter(out);
		print.println(ris.r +" "+ ris.c);
		for(int i=0; i<ris.r;i++){
			for(int j=0;j<ris.c;j++)
				print.print(ris.matrice[i][j]+" ");
			print.println("");
		}
		
		print.close();
		out.close();
		System.out.println("It created the file "+ args[2] + " containing the result.");
	}
	
}
