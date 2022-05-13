package sokoban;

import com.codingame.gameengine.runner.SoloGameRunner;
import java.io.IOException;

public class SokobanMain {
    public static void main(String[] args) {
        String test = "test3.json" ;
        String ch = "config/"+test;
        try{
            GenFilePddl gfp = new GenFilePddl(ch, "Problem.pddl") ;
            gfp.genFilePddl() ;
        } catch (IOException e) {
			e.printStackTrace();
		}

        SoloGameRunner gameRunner = new SoloGameRunner();
        gameRunner.setAgent(Agent.class);
        gameRunner.setTestCase(test);

        gameRunner.start();
    }
}
