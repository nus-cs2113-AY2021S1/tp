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
        finLog[finSize]=log;
        finLogVal[finSize]=val;
        finSize++;
        sum=sum+val;
    }

    public void delFin(int index) {
        sum=sum-finLogVal[index-1];
        for (int i=index-1;i<finSize-1;i++) {
            finLog[i]=finLog[i+1];
            finLogVal[i]=finLogVal[i+1];
        }
        finLog[finSize-1]="";
        finLogVal[finSize-1]=0;
        finSize--;
    }

    public void summary() {
        for (int i=0;i<finSize;i++) {
            System.out.println("\t"+(i+1)+"."+finLog[i]+" $"+finLogVal[i]);
        }
        System.out.println("\tTotal budget: "+sum);
    }
}
