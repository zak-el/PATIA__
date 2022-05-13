package fr.uga.pddl4j.tutorial.asp;


import fr.uga.pddl4j.heuristics.state.StateHeuristic;

import fr.uga.pddl4j.parser.ParsedProblem;

import fr.uga.pddl4j.plan.Plan;

import fr.uga.pddl4j.plan.SequentialPlan;

import fr.uga.pddl4j.planners.AbstractPlanner;

import fr.uga.pddl4j.planners.Planner;

import fr.uga.pddl4j.planners.PlannerConfiguration;

import fr.uga.pddl4j.planners.SearchStrategy;

import fr.uga.pddl4j.planners.statespace.search.StateSpaceSearch;

import fr.uga.pddl4j.problem.ADLProblem;

import fr.uga.pddl4j.problem.State;

import fr.uga.pddl4j.problem.operator.Action;

import fr.uga.pddl4j.problem.operator.ConditionalEffect;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import picocli.CommandLine;


import java.util.Comparator;

import java.util.HashSet;

import java.util.List;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set; 
import java.util.ArrayList;
import java.util.Vector;

/**

 * The class is an example. It shows how to create a simple A* search planner able to

 * solve an ADL problem by choosing the heuristic to used and its weight.

 *

 * @author D. Pellier

 * @version 4.0 - 30.11.2021

 */

@CommandLine.Command(name = "ArvandStatique",

    version = "ArvandStatique 1.0",

    description = "Solves a specified planning problem using A* search strategy.",

    sortOptions = false,

    mixinStandardHelpOptions = true,

    headerHeading = "Usage:%n",

    synopsisHeading = "%n",

    descriptionHeading = "%nDescription:%n%n",

    parameterListHeading = "%nParameters:%n",

    optionListHeading = "%nOptions:%n")

public class ArvandStatique extends AbstractPlanner<ADLProblem> {
    private double alpha = 0.9;
    private Vector<Double> h = new Vector<>(10, 1);
    private final int MAX_STEPS = 7;
    private final int NUM_WALK = 2000 ;
    private final int LENGTH_WALK = 10;
    
    /**

     * The class logger.

     */

    private static final Logger LOGGER = LogManager.getLogger(ArvandStatique.class.getName());

    /**

     * The weight of the heuristic.

     */

    private double heuristicWeight;


    /**

     * The name of the heuristic used by the planner.

     */

    private StateHeuristic.Name heuristic;

    /**

     * Sets the weight of the heuristic.

     *

     * @param weight the weight of the heuristic. The weight must be greater than 0.

     * @throws IllegalArgumentException if the weight is strictly less than 0.

     */

    @CommandLine.Option(names = { "-w", "--weight" }, defaultValue = "1.0",

        paramLabel = "<weight>", description = "Set the weight of the heuristic (preset 1.0).")

    public void setHeuristicWeight(final double weight) {

        if (weight <= 0) {

            throw new IllegalArgumentException("Weight <= 0");

        }

        this.heuristicWeight = weight;

    }

    /**

     * Set the name of heuristic used by the planner to the solve a planning problem.

     *

     * @param heuristic the name of the heuristic.

     */

    @CommandLine.Option(names = { "-e", "--heuristic" }, defaultValue = "FAST_FORWARD",

        description = "Set the heuristic : AJUSTED_SUM, AJUSTED_SUM2, AJUSTED_SUM2M, COMBO, "

            + "MAX, FAST_FORWARD SET_LEVEL, SUM, SUM_MUTEX (preset: FAST_FORWARD)")
    public void setHeuristic(StateHeuristic.Name heuristic)  {

        this.heuristic = heuristic;

    }

    /**

     * Returns the name of the heuristic used by the planner to solve a planning problem.

     *

     * @return the name of the heuristic used by the planner to solve a planning problem.

     */

    public final StateHeuristic.Name getHeuristic() {

        return this.heuristic;

    }


    /**

     * Returns the weight of the heuristic.

     *

     * @return the weight of the heuristic.

     */

    public final double getHeuristicWeight() {

        return this.heuristicWeight;

    }


    /**

     * Instantiates the planning problem from a parsed problem.

     *

     * @param problem the problem to instantiate.

     * @return the instantiated planning problem or null if the problem cannot be instantiated.

     */

    @Override

    public ADLProblem instantiate(ParsedProblem problem) {

        final ADLProblem pb = new ADLProblem(problem);

        pb.instantiate();

        return pb;

    }

    /**

     * Extracts a search from a specified node.

     *

     * @param node the node.

     * @param problem the problem.

     * @return the search extracted from the specified node.

     */

    private Plan extractPlan(final Node node, final ADLProblem problem) {

        Node n = node;

        final Plan plan = new SequentialPlan();

        while (n.getAction() != -1) {

            final Action a = problem.getActions().get(n.getAction());

            plan.add(0, a);

            n = n.getParent();

        }

        return plan;

    }

    public ArrayList<Action> ApplicableActions(Node s, ADLProblem problem) {
        ArrayList<Action> list = new ArrayList<>();

        for (int i = 0; i < problem.getActions().size(); i++) {

            // We get the actions of the problem
            Action a = problem.getActions().get(i);

            // If the action is applicable in the current node
            if (a.isApplicable(s)) {
                list.add(a);
            }
        }
        return list;

    }


    /**

     * check if we are on a dead end.

     *

     * @param node the node.

     * @param problem the problem.

     * @return true if there is no applicable action.

     */

    public boolean DeadEnd(Node s, ADLProblem problem){
        for(int i = 0; i < problem.getActions().size(); i++) {

            // We get the actions of the problem
            Action a = problem.getActions().get(i);

            // If the action is applicable in the current node
            if (a.isApplicable(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Search a solution plan for a planning problem using an arvand search strategy.
     *
     * @param problem the problem to solve.
     * @return a plan solution for the problem or null if there is no solution
     */
    public Plan arvand(ADLProblem problem) {
        // First we create an instance of the heuristic to use to guide the search
        final StateHeuristic heuristic = StateHeuristic.getInstance(this.getHeuristic(), problem);

        Plan plan = null;

        // We get the initial state from the planning problem
        final State init = new State(problem.getInitialState());
        Node s = new Node(init, null, -1, 0, heuristic.estimate(init, problem.getGoal()));

        double hmin = s.getHeuristic();
        int counter = 0;

        System.out.println("hmin = " + hmin) ;

        while (!s.satisfy(problem.getGoal())) {
            
            if (counter > MAX_STEPS || DeadEnd(s, problem)){
                s = new Node(init, null, -1, 0, heuristic.estimate(init, problem.getGoal()));
                hmin = s.getHeuristic();
                counter = 0;
                //System.out.println("hmin = " + hmin) ;
            }

            s = MonteCarloRandomWalk(s, problem);

            if (s.getHeuristic() < hmin){
                hmin = s.getHeuristic();
                counter = 0;
            }else{
                counter ++;
            }
        }
        return this.extractPlan(s, problem);
    }

public Node MonteCarloRandomWalk(Node s, ADLProblem problem){
        final StateHeuristic heuristic = StateHeuristic.getInstance(this.getHeuristic(), problem);
        double hmin = Integer.MAX_VALUE;
        Node smin = null;

        for(int i = 0; i < NUM_WALK; i++){
            Node ss = new Node(s);
            ss.setParent(s.getParent());
            ss.setCost(s.getCost());
            ss.setAction(s.getAction());
            ss.setHeuristic(s.getHeuristic());

            for (int j = 0; j < LENGTH_WALK; j++){
                Node parent = new Node(ss);
                parent.setParent(ss.getParent());
                parent.setCost(ss.getCost());
                parent.setAction(ss.getAction());
                
                ArrayList<Integer> applicableActions = new ArrayList<>();
                int nbActions = problem.getActions().size();
                for (int k = 0; k < nbActions; k++){
                    if (problem.getActions().get(k).isApplicable(ss)){
                        applicableActions.add(k); 
                    }
                }

                if(applicableActions.size() == 0){
                    break;
                }
                
                Random rand = new Random();
                int numAction = rand.nextInt(applicableActions.size());
                Action a = problem.getActions().get(applicableActions.get(numAction));

                final List<ConditionalEffect> effects = a.getConditionalEffects();
                for (ConditionalEffect ce : effects) {
                    if (parent.satisfy(ce.getCondition())) {
                        ss.apply(ce.getEffect());
                    }
                }

                final double g = parent.getCost() + 1;
                ss.setCost(g);
                ss.setParent(parent);
                ss.setAction(applicableActions.get(numAction));
                ss.setHeuristic(heuristic.estimate(ss,problem.getGoal()));

                if(ss.satisfy(problem.getGoal())){
                    return ss;
                }
            }

            if(ss.getHeuristic() < hmin){
                smin = ss;
                hmin = ss.getHeuristic();
            }

        }
        if (smin == null){
            return s;
        }else{
            return smin;
        }
    }
/**

 * Search a solution plan to a specified domain and problem using monte carlo random walk.

 *

 * @param problem the problem to solve.

 * @return the plan found or null if no plan was found.

 */

@Override

public Plan solve(final ADLProblem problem) {

    LOGGER.info("* Starting arvand search \n");

    // Search a solution

    final long begin = System.currentTimeMillis();

    final Plan plan = this.arvand(problem);

    final long end = System.currentTimeMillis();

    // If a plan is found update the statistics of the planner

    // and log search information

    if (plan != null) {

        LOGGER.info("* arvand search succeeded\n");

        this.getStatistics().setTimeToSearch(end - begin);

    } else {

        LOGGER.info("* arvand search failed\n");

    }

    // Return the plan found or null if the search fails.

    return plan;

}

     /**

     * The main method of the <code>ArvandStatique</code> planner.

     *

     * @param args the arguments of the command line.

     */

    public static void main(String[] args) {

        try {

            final ArvandStatique planner = new ArvandStatique();

            CommandLine cmd = new CommandLine(planner);

            cmd.execute(args);

        } catch (IllegalArgumentException e) {

            LOGGER.fatal(e.getMessage());

        }

    }

}


/*
java -cp lib/pddl4j-4.0.0.jar fr.uga.pddl4j.planners.statespace.HSP -e MAX -w 1.2 -t 600





javac -d classes -cp lib/pddl4j-4.0.0.jar src/fr/uga/pddl4j/tutorial/ArvandStatique/Node.java src/fr/uga/pddl4j/tutorial/ArvandStatique/ArvandStatique.java

java --add-opens java.base/java.lang=ALL-UNNAMED -server -Xms2048m -Xmx2048m -cp "$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q):target/test-classes/:target/classes" sokoban.SokobanMain
*/