package sokoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Agent {
   public static void main(String[] args) throws IOException {

        String commands = "java -cp pddl4j-4.0.0.jar -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP sokobanDomain.pddl Problem.pddl" ;
        //String commands = "java -cp classes:/home/elbouchz/ubuntu/M1/S2/PATIA/ASP/lib/pddl4j-4.0.0.jar /home/elbouchz/ubuntu/M1/S2/PATIA/ASP/src/fr.uga.pddl4j.tutorial.asp.ASP sokobanDomain.pddl Problem.pddl" ;
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
                //System.out.println(sp[i]);

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



//java --add-opens java.base/java.lang=ALL-UNNAMED -server -Xms2048m -Xmx2048m -cp "$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q):target/test-classes/:target/classes" sokoban.SokobanMain