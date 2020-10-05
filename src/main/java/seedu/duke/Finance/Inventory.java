package seedu.duke.Finance;

public class Inventory {
    private String[] list;
    private int size;

    public Inventory() {
        list=new String[100];
        size=0;
    }

    public void addList(String input) {
        list[size]=input;
        size++;
    }

    public void delList (int index) {
        for (int i=index-1;i<size-1;i++) {
            list[i]=list[i+1];
        }
        list[size-1]="";
        size--;
    }

    public void summary() {
        for (int i=0;i<size;i++) {
            System.out.println("\t"+(i+1)+"."+list[i]);
        }
    }
}
