import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Frequencies {
	
	
	public static int[] keyWordsFreq(StringBuffer text, String[] keyWords){
		int i=0;
		int[] freq = new int[50];
		Pattern pattern;
		Matcher matcher;
		while(i<keyWords.length){
			pattern = Pattern.compile("[^a-zA-Z0-9]"+keyWords[i]+"[^a-zA-Z0-9]");
			matcher = pattern.matcher(text);
			while(matcher.find())
				freq[i]++;
			i++;
		}
		return freq;
	}

	public static void main(String[] args) throws Exception {
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
		System.out.println(javaIn.toString()+"\n\n\n");
		DeleteComments.deComment(javaIn);
		
		//KeyWords
		String[] keyWords = {"abstract","continue","for","new","switch",
				"assert","default","goto","package","synchronized",
				"boolean","do","if","private","this","break","double","implements","protected",
				"throw","byte","else","import","public","throws",
				"case","enum","instanceof","return", 	"transient",
				"catch","extends","int","short","try",
				"char","final","interface","static","void",
				"class","finally","long","strictfp","volatile",
				"const","float","native","super","while"};
		int[] ris = keyWordsFreq(javaIn, keyWords);
		for(int i =0; i<ris.length; i++){
			System.out.println(keyWords[i]+": "+ris[i]);
		}
		in.close();
		file.close();
	}

}
