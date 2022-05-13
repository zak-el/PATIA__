import sys, os, subprocess, shlex
import matplotlib.pyplot as plt
import numpy as np
import threading

"""
input == [ARS, ARD]  [Domaine.pddl] [Probleme.pddl] [L, M, W]  

python3 validateur.py ARD pddl/blocksworld/domain.pddl pddl/blocksworld/p01.pddl L
"""


chemin = "./pddl"
cmd1 = "java -cp classes:lib/pddl4j-4.0.0.jar fr.uga.pddl4j.tutorial.asp.ArvandStatique "
cmd2 = "java -cp classes:lib/pddl4j-4.0.0.jar fr.uga.pddl4j.tutorial.asp.ArvandDynamique "
cmd3 = " -e FAST_FORWARD -w 1.2 -t 1000"
cmd4 = "java -cp build/libs/pddl4j-4.0.0.jar -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP "


 

def writeOut(planner, Domaine, Probleme, sys):
    expt = False
    cmd = ""

    myFile = open("out.txt", "w+")

    if(planner == "ARS"):
        cmd += cmd1 + Domaine + " " + Probleme + cmd3
    elif(planner == "ARD"):
        cmd += cmd2 + Domaine + " " + Probleme + cmd3


    args = shlex.split(cmd)
    try:
        res = subprocess.run(args, capture_output=True, text=True, timeout=60)
    except:
        print(" Erreur lors de l'execution de la commande ...")
        expt = True

    if(expt == False):
        try:
            sortie = res.stdout.split("\n")

            i = 0
            while(i < len(sortie) and  "follows:" not in sortie[i]): # and sortie[i] != "00:"):
                i += 1

            
            i += 1
            while(i < len(sortie) and "time" not in sortie[i]):
                myFile.write(sortie[i])
                myFile.write("\n")
                i += 1

        except:
            print("ERREUR")

    
    myFile.close()

    cmd = ""

    if(sys == "L" or sys == "l"):
        cmd += "./validate-nux " + Domaine + " " + Probleme + " out.txt"
    elif(sys == "M" or sys == "m"):
        cmd += "./validate-osx " + Domaine + " " + Probleme + " out.txt"
    elif(sys == "W" or sys == "w"):
        cmd += "./validate-win.exe " + Domaine + " " + Probleme + " out.txt"

    args = shlex.split(cmd)
    try:
        res = subprocess.run(args, capture_output=True, text=True, timeout=60)
        print(res.stdout)
    except:
        print(" Erreur lors de l'execution du validateur ...")

str = sys.argv

writeOut(str[1] , str[2], str[3], str[4])


