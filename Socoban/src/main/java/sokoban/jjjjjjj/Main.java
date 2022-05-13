

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String test = "/home/elbouchz/ubuntu/M1/S2/PATIA/Sokoban/config/test21.json";
        GenFilePddl gfp = new GenFilePddl(test, "Problem.pddl") ;
        gfp.genFilePddl() ;


        Runtime rt = Runtime.getRuntime();
        String pdd = "pddl4j-4.0.0.jar"  ;
        String domain = "sokobanDomain.pddl" ;
        String problem = "Problem.pddl" ;
		//String commands = "java -cp "+ pdd + " -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP "+domain + problem;
        String commands = "java -cp  ../../../../../pddl4j-4.0.0.jar -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP ../../../../../sokobanDomain.pddl ../../../../../Problem.pddl" ;
        //java -cp pdd /home/elbouchz/ubuntu/M1/S2/PATIA/pddl4j2/pddl4j/build/libs/pddl4j-4.0.0.jar -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP sokobanDomain.pddl Problem.pddl
        try {
            java.util.Scanner st = new java.util.Scanner(Runtime.getRuntime().exec(commands).getInputStream()).useDelimiter("\\A");
            
            String solution = "";
            String s = null;
            if(st.hasNext())
                s = st.next() ;

            String sp[] = s.split("\n");
            int i = 0 ;
            while(!(sp[i].split(" ")[0]).equals("00:")){
                i++;
            }
            int ascii ;
            while(!(sp[i].split(" ")[0]).equals("time")){
                System.out.println(sp[i]);

                if(sp[i].length() >= 6)
                    solution += sp[i].toUpperCase().charAt(sp[i].length() - 6);
                i++;
            }

            for (char c : solution.toCharArray()) 
                    System.out.println(c);
            
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
