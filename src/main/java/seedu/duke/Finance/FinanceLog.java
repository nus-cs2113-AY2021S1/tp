package seedu.duke.Finance;

public class FinanceLog {
    public String[] finLog;
    public double[] finLogVal;
    public int finSize;
    public double sum;

    public FinanceLog() {
        finLog=new String[100];
        finLogVal=new double[100];
        finSize=0;
        sum=0;
    }

    public void addFin(String log, double val) {
        System.out.println("Got it! I've added this into the list!");
        finLog[finSize]=log;
        finLogVal[finSize]=val;
        finSize++;
        System.out.println("\t"+finSize+"."+finLog[finSize-1]+" $"+finLogVal[finSize-1]);
        sum=sum+val;
        showSum();
        System.out.println();
    }

    public void delFin(int index) {
        System.out.println("Got it! I will delete this task!");
        System.out.println("\t"+index+"."+finLog[index-1]+" $"+finLogVal[index-1]);
        sum=sum-finLogVal[index-1];
        for (int i=index-1;i<finSize-1;i++) {
            finLog[i]=finLog[i+1];
            finLogVal[i]=finLogVal[i+1];
        }
        finLog[finSize-1]="";
        finLogVal[finSize-1]=0;
        finSize--;
        showSum();
        System.out.println();
    }

    public void summary() {
        System.out.println("Here is the summary of finance log.");
        for (int i=0;i<finSize;i++) {
            System.out.println("\t"+(i+1)+"."+finLog[i]+" $"+finLogVal[i]);
        }
        System.out.println("\tTotal budget: "+String.format("%.2f",sum)+"\n");
    }

    public void showSum() {
        if (finSize>1) {
            System.out.println("There are "+finSize+" logs in the list");
        }
        else {
            System.out.println("There is "+finSize+" logs in the list");
        }
    }
}
