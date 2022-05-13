package sokoban;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.Scanner;

public class GenFilePddl{
    String board ;
    File file ;
    PrintWriter writer ;
    int maxLineSize ;

    // constructor
    public GenFilePddl(String fileName,String name ) throws IOException{
        File f = new File(fileName);
        Scanner scan = new Scanner(f);
        String line1, line2 ;
        line1 = "";
        maxLineSize = 0 ;
        for(int i=0; i<5; i++){
            if(scan.hasNextLine()){
                line1 = scan.nextLine();
            }
        }

        this.board = line1.split("\"")[3];

        //file = new File("src/main/java/sokoban/" +name) ;
        file = new File(name) ;
        file.createNewFile() ; 
        writer = new PrintWriter(name);

    }
    // read board string and reformat it into an arraylist
    public List<String> matrixBoard(){
        List<String> M = new ArrayList<>() ;
        int start=0 ;
        String l = null ;
        for( int i=0; i<board.length()-1; i++ ){
            if(board.charAt(i)== '\\' && board.charAt(i+1)== 'n'){
                l = new String(board.substring(start,i)) ;
                M.add(l);

                i++;
                start = i+1;
                if(maxLineSize < l.length()) maxLineSize = l.length() ;
            }
        }

        return M;
    }

    // returns the character at M[i][j] if out of bounds returns #
    public char getCase(ArrayList<String> M, int i, int j){
        if(M.size() >= i && M.get(i).length() >= j)
            return M.get(i).charAt(j);
        else
            return '#' ;
    }

    // write all the possible directions depending on (i,j)
    public void writeDir(ArrayList<String> M, int i, int j){
        if(j < M.get(i).length() - 1){
            writer.println("(next-to pos-"+Integer.toString(i)+"-"+Integer.toString(j)+" pos-"+Integer.toString(i)+"-"+Integer.toString(j+1)+" R)");
        }
        if(M.size() > i + 1){
            writer.println("(next-to pos-"+Integer.toString(i)+"-"+Integer.toString(j)+" pos-"+Integer.toString(i+1)+"-"+Integer.toString(j)+" D)");
        }
        if(j != 0){
            writer.println("(next-to pos-"+Integer.toString(i)+"-"+Integer.toString(j)+" pos-"+Integer.toString(i)+"-"+Integer.toString(j-1)+" L)");
        }
        if(i != 0){
            writer.println("(next-to pos-"+Integer.toString(i)+"-"+Integer.toString(j)+" pos-"+Integer.toString(i-1)+"-"+Integer.toString(j)+" U)");
        }     
    }

    public void genFilePddl(){
        ArrayList<String> M = (ArrayList<String>) matrixBoard() ;
        ArrayList<Point> goals = new ArrayList<>();
        writer.print("(define (problem sokobanProblem)\n(:domain sokobanDomain)\n(:objects\n");  
        writer.print("R -direction\nL -direction\nU -direction\nD -direction\n");
        for(int i=0; i<M.size(); i++){
            for(int j=0; j<maxLineSize; j++)
                writer.println("pos-"+Integer.toString(i)+"-"+Integer.toString(j)+ " - location") ;
        }
        writer.print(")\n(:init\n") ;
        for(int i=0; i<M.size(); i++){
            for(int j=0; j<M.get(i).length(); j++){
                writeDir(M, i, j);
                switch(getCase(M,i,j)){
                    case '.':
                        goals.add(new Point(i,j));
                    case ' ': 
                        writer.println("(is-free pos-"+Integer.toString(i)+"-"+Integer.toString(j)+")" );
                        break;
                    case '*':
                        goals.add(new Point(i,j));
                    case '$': 
                        writer.println("(box-at pos-"+Integer.toString(i)+"-"+Integer.toString(j)+")" );
                        break;
                    case '#':
                        break ;
                    case '@': 
                        writer.println("(player-at pos-"+Integer.toString(i)+"-"+Integer.toString(j)+")" );
                        break;
                    default: 
                        goals.add(new Point(i,j));
                        writer.println("(player-at pos-"+Integer.toString(i)+"-"+Integer.toString(j)+")" );
                        break;
                }
            }
        }
        writer.print(")\n(:goal (and\n") ;
        for(int i=0; i<goals.size(); i++){
            writer.print("(box-at pos-"+(int)goals.get(i).getX()+"-"+(int)goals.get(i).getY()+")\n") ;
        }
        writer.print("))\n)");
        writer.close();
     
    }
}


