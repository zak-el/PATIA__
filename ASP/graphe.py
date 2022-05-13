import sys, os, subprocess, shlex
import matplotlib.pyplot as plt
import numpy as np
import threading


chemin = "./pddl"
cmd1 = "java -cp classes:lib/pddl4j-4.0.0.jar fr.uga.pddl4j.tutorial.asp.ArvandStatique "
cmd2 = " -e FAST_FORWARD -w 1.2 -t 1000"
cmd3 = "java -cp build/libs/pddl4j-4.0.0.jar -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP "
cmd4 = "java -cp classes:lib/pddl4j-4.0.0.jar fr.uga.pddl4j.tutorial.asp.ArvandDynamique "

def fonct_thread(cmd1, cmd2,e,j,list_cl):
    expt = False
    l = [int(j[1:(len(j)-5)])]

    cmd = cmd1 +"pddl/"+e+"/domain.pddl" + " pddl/"+e+"/"+j + cmd2
    args = shlex.split(cmd)
    try:
        res = subprocess.run(args, capture_output=True, text=True, timeout=180)
    except:
        l.append(0)
        l.append(300)
        expt = True

    if(expt == False):
        try:
            sortie = res.stdout.split(" ")

            i = 0
            while(sortie[i] != "spent:"):
                i += 1
        except:
            print(sortie)
        k = i
        i-=1
        while( ":" not in sortie[i] ):
            i-=1

        a = sortie[i].split(":")
        lenght = a[0].split("\n")
        l.append(int(lenght[1]))
        i = k
        while(sortie[i] != "total" and sortie[i+1] != "time"):
            i+=1
        time = float(sortie[i-2].replace(',','.'))
        l.append(time)
        list_cl.append(l)
        
    else:
        expt = False
        list_cl.append(l)

def asp_exc(cmd1, cmd2):
    class_list = []
    list1 = os.listdir(chemin)
    
    
    for e in list1 :
        list_cl = [e]
        list2 = os.listdir(chemin + "/"+ e)
        t = []

        for j in list2 :
            if(j != "domain.pddl"):

                print("j = ", j)
                #fonct_thread(cmd1, cmd2,e,j,list_cl)
                t1 = threading.Thread(target=fonct_thread, args=(cmd1, cmd2,e,j,list_cl)) 
                t.append(t1)
                t1.start()
            
                
        for j in t :
            j.join()
        print("+++++++++++++++++++++++++")
        class_list.append(list_cl)
        
    return class_list

def list_ind(list, ind):
    l = []
    for e in list:
        l.append(e[ind])
    return l

def sort_list(list1, list2, ind):
    l = []
    for e in list2 :
        if(e[0]-1 < len(list1)):
            l.append(list1[e[0]-1][ind])
    return l


def plot():
    
    os.chdir("../pddl4j2/pddl4j/")
    list1_ = asp_exc(cmd3, "")
    

    os.chdir("../../ASP")
    list2_ = asp_exc(cmd1, cmd2)
    list3_ = asp_exc(cmd4, cmd2)
    
    for i in range(len(list1_)):

        list1 = list1_[i]
        list1.pop(0)

        list2 = list2_[i]
        list2.pop(0)

        list3 = list3_[i]
        nom = list3.pop(0)

        ls1 = sort_list(list2, list1, 1)
        ls2 = sort_list(list2, list1, 2)

        ld1 = sort_list(list3, list1, 1)
        ld2 = sort_list(list3, list1, 2)
        

        from operator import itemgetter
        
        
        lst1 = list_ind(sorted(list1, key=itemgetter(1)), 1)
        lst2 = list_ind(sorted(list1, key=itemgetter(2)), 2)
        n1 = len(lst1)
        n2 = len(ls1)
        n3 = len(ld1)
       
        plt.subplot(211)
        title1 = nom + " : La taille du plan"
        plt.title(title1)
        plt.plot(range(n1),lst1, color = 'b', label = ' ASP ' )
        plt.plot(range(n2),ls1, color = 'r', label = ' ArvandStatique ' )
        plt.plot(range(n3),ld1, color = 'g', label = ' ArvandDynamique ' )
        plt.legend(loc='best')

        plt.subplot(212)
        title2 = nom + " : Le temps d'execution "
        plt.title(title2)
        plt.plot(range(n1),lst2, color = 'b', label = ' ASP ' )
        plt.plot(range(n2),ls2, color = 'r', label = ' ArvandStatique ' )
        plt.plot(range(n3),ld2, color = 'g', label = ' ArvandDynamique ' )
        plt.legend(loc='best')

        plt.show()
        
        


plot()

