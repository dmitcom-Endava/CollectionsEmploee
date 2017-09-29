import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by User on 05.07.2017.
 */
public class HireDates implements Comparable {
    private Worker worker;
    private LocalDate hiredate;
    private  ArrayList<LocalDate>  hireDates;

    public HireDates(Worker worker, LocalDate hiredate) {
        this.worker = worker;
        this.hiredate = hiredate;
        hireDates = new ArrayList<>();
        hireDates.add(this.hiredate);
    }

    public HireDates(Worker worker) {
        this.worker = worker;
        hireDates = new ArrayList<>();
    }


    public void addHireData( LocalDate newHiredate){
        boolean isAvailibleDate=true;
        for (LocalDate hd:hireDates) {
            if (hd.equals(newHiredate))isAvailibleDate=false;
        }
        hireDates.add(newHiredate);
    }

    public String toString(){

        String str=" "+ worker+": hired ";
        for (LocalDate hd:hireDates) {
            str += hd + " ";
        }
        //return "444"+worker.getLastName()+worker.getFirstName();
        return  str;
    }

    public String getDates(){
        String str="";
        for (LocalDate hd:hireDates)
            str+=hd+" ";
        return str;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public ArrayList<LocalDate> getHireDates() {
        return hireDates;
    }

    public void setHireDates(ArrayList<LocalDate> hireDates) {
        this.hireDates = hireDates;
    }

    public int compareTo(Object hd) {
        HireDates hireDatesT= (HireDates) hd;
        if (this.worker.getLastName().compareTo(hireDatesT.worker.getLastName())==0) return 0;
        if (this.worker.getLastName().compareTo(hireDatesT.worker.getLastName())>0) return 1;
        if (this.worker.getLastName().compareTo(hireDatesT.worker.getLastName())<0) return -1;
        return -1;
    }
}
