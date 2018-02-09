import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class DeleteComments {
	
	private static ArrayList<PatternRecord> findOccurrences(String regex, StringBuffer text){
		
		ArrayList<PatternRecord> ris = new ArrayList<PatternRecord>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find())
			ris.add(new PatternRecord(matcher.group(), matcher.start(), matcher.end()));
		return ris;
	}
	
	
	public static void deComment(StringBuffer javaIn){
		PatternRecord next;
		String regEx1 = "//[^\\Q\n\\E]*\\Q\n\\E";
		/*
		 * regEx2:
		 * Potrei non beccare commenti che hanno uno slash " / "
		 * in mezzo al commento (preceduto prima da un qualche * )
		 *  sarebbe giusto definirla come
		 *  = " /*[qualsiasi cosa non sia * /]+ * / ";
		 */
		String regEx2 = "/\\Q*\\E[^\\Q*\\E]*[^/]+/";
		ArrayList<PatternRecord> comments = findOccurrences(regEx1, javaIn);
		//Devo rimuoverli dal fondo verso la cima per evitare di scalare gli indici dei
		//Commenti successivi
		int i = comments.size()-1;
		while(i>=0){
			next = comments.get(i);
			javaIn.delete(next.start, next.end-1); //-1 perche nelle regEx1 l'ultimo è sempre \n
			i--;
			}
		comments = findOccurrences(regEx2, javaIn);
		i = comments.size()-1;
		while(i>=0){
			next = comments.get(i);
			javaIn.delete(next.start, next.end);
			i--;
			}
	}
	
	
	public static void main(String[] args) throws Exception{
		if(args.length!=1){
			System.out.println("Err");
			System.exit(0);
		}
		FileReader file = new FileReader(args[0]);
		Scanner in = new Scanner(file);
		StringBuffer javaIn = new StringBuffer();
		while(in.hasNext()){
			javaIn.append(" "+in.nextLine());
			javaIn.append("\n");	
		}
		System.out.println(javaIn.toString()+"\n\n\n Senza Commenti:");
		deComment(javaIn);
		System.out.println(javaIn.toString());
		in.close();
		file.close();

		
	}
	
}
