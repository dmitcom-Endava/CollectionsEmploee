import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by User on 04.07.2017.
 */
public class Worker implements Comparable {
    private String firstName;
    private String lastName;
    private int  age;
    private boolean readyForTrip;
    private TreeSet <Country> availibleCountries;

    public Worker(String firstName, String lastName, int age, boolean readyForTrip, TreeSet availibleCountries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.readyForTrip = readyForTrip;
        this.availibleCountries = availibleCountries;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isReadyForTrip() {
        return readyForTrip;
    }

    public void setReadyForTrip(boolean readyForTrip) {
        this.readyForTrip = readyForTrip;
    }

    public TreeSet<Country> getAvailibleCountries() {
        return availibleCountries;
    }

    public void setAvailibleCountries(TreeSet<Country> availibleCountries) {
        this.availibleCountries = availibleCountries;
    }

    public String getLastName() {
        return lastName;
    }

    public  String toString(){
        return firstName+" "+lastName/*+ "+age+" "+showReady()+showAvailibleCountries()*/;
    }

    public String showAvailibleCountries(){
        int i=0;
        String str="";
        for (Country c: availibleCountries) {
            i++;
            str+=" "+i+" "+c.getName();
        }
        return str+" ";

    }

    public String showReady(){
        if (this.readyForTrip) return " is ready to go to ... ";
        else return  " isn't ready to go anywhere ";
    }

    public boolean findCountry(Country fCountry){
        for (Country c: availibleCountries)
            if (c.equals(fCountry))return true;
        return false;
    }
    public int compareTo(Object c) {
        Worker workerT = (Worker) c;
        if (this.getLastName().compareTo(workerT.getLastName())==0) return 0;
        if (this.getLastName().compareTo(workerT.getLastName())>0) return 1;
        if (this.getLastName().compareTo(workerT.getLastName())<0) return -1;
        return -1;
    }


}
