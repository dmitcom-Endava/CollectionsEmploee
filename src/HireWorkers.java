import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Created by User on 04.07.2017.
 */
public class HireWorkers {
    public Worker worker;
    private HireDates hireDates;
    private String wokersFN[] = {"Peter", "Andrew", "Klim", "Anton", "Kaban" };
    private String wokersLN[] = {"Ivanov", "Sidorov", "Joice", "Bidon", "Mihu",
            "Nicolaev", "Kirkorov", "Vaenga", "Gleev", "Toneev",
            "Brinza", "Crotov", "Pereteatcu", "Sangheli", "Vangheli",
            "Ivanov0", "Sidorov0", "Joice0", "Bidon0", "Mihu0",
            "Crimov", "Leonov", "Gagarin", "Fedorov", "Antonov",

    };
    private int workerAges[] = {33,27,49,55,25};
    private String countriesArr[]= {"Moldova", "Holland", "USA", "Brazil", "Russia", "Canada" };
    private Date hireDate;
    private LocalDate hiredatesArr []   = {LocalDate.of(2011, Month.JUNE, 10),
            LocalDate.of(2014, Month.MAY, 17),
            LocalDate.of(2013, Month.APRIL, 13),
            LocalDate.of(2016, Month.NOVEMBER, 10),
            LocalDate.of(2012, Month.MARCH, 6),
            LocalDate.of(2011, Month.FEBRUARY, 3),
            LocalDate.of(2010, Month.SEPTEMBER, 9)
    };
    private TreeSet <Country> countries;
    private TreeSet<HireDates> workers= new TreeSet();
    private TreeSet <HireDates> readyWorkerstoTrip = new TreeSet();
    private TreeMap <Country, TreeSet <Worker>> CountryWithWorkers;

    public HireWorkers() {
        fillCountries();
        fillWorkers();
        showWorkers();
        readyWorkerstoTrip = workersReadyForTrip(LocalDate.of(2013, Month.APRIL, 01),LocalDate.of(2015, Month.MARCH, 1));
        System.out.println(checkCountry(findWorkerByLastName("Gagarin"),findCountry("USA")));
        CountryWithWorkers=makeCountryMap();
    }
    public void fillCountries(){
        countries = new TreeSet();
        for (String c: countriesArr)
            countries.add(new Country(c));
    }


    public void generateDates(HireDates hd){
        int i=0;
        for (LocalDate dh : hiredatesArr)
            if (rBool()) {
                hd.addHireData(dh); // добавляем дату приема в коллекцию работника
                i++;
                if (i>=1&&rBool())break;  // лимитируем количество дат приема
            }
    }

    public void fillWorkers(){
        for (int i = 0; i<20; i++){
            Random rand = new Random();
            boolean isReady=rBool();
            Worker workerTemp = new Worker(wokersFN[rand.nextInt(wokersFN.length-1)],
                    wokersLN[i],
                    workerAges[rand.nextInt(workerAges.length-1)],
                    isReady,                            //генерируем готовность работника
                    randomAvCountries(isReady)
            );
            hireDates = new HireDates(workerTemp);
            generateDates(hireDates);               //генерируем даты приема
            workers.add(hireDates);
        }
    }

    public boolean rBool(){
        if ( new Random().nextInt(2)==0) return false; else return true;
    }

    public TreeSet randomAvCountries(boolean ready){   //набор стран работника
            TreeSet randomTS = new TreeSet();
            if (ready)
                for (Country c : countries)
                    if (rBool()) randomTS.add(c);
            return randomTS;
    }

    public void showWorkers(){
        int i=0;
        for (HireDates hd: workers) {
            i++;
            System.out.println(i+" "+hd);
        }
    }

    public TreeSet <HireDates>  workersReadyForTrip(LocalDate startDate, LocalDate endDate){
        TreeSet <HireDates> readyWorkers = new TreeSet<>();
        for (HireDates hd: workers) {
            int i=0;
            for (LocalDate dates: hd.getHireDates()) {
                if (!((dates.compareTo(startDate)>0) && (dates.compareTo(endDate)<0))) i++;
            }
            if (i==0 && hd.getWorker().isReadyForTrip()==true) readyWorkers.add(hd);
        }
        System.out.println("\n in range  "+startDate+" - "+endDate+ " Are ready to go:");
        for (HireDates hd: readyWorkers)
            System.out.println(" " + hd.getWorker().getLastName() + hd.getDates());
        return readyWorkers;
    }

    public boolean checkCountry(Worker worker, Country counry){
        for (HireDates hd: workers)
            if (hd.getWorker().equals(worker)&& hd.getWorker().isReadyForTrip()&& hd.getWorker().findCountry(counry))
                return true;
        return false;
    }

    public Worker findWorkerByLastName(String name){
        for (HireDates hd: workers)
            if (hd.getWorker().getLastName().equals(name)) return hd.getWorker();
        return null;
    }

    public Country findCountry(String name){
        for (Country c: countries)
            if (c.getName().equals(name)) return c;
        return null;
    }

    public TreeMap makeCountryMap(){
        CountryWithWorkers=new TreeMap<>();
        for (Country c: countries) {
            TreeSet <Worker> workersTemp= new TreeSet<>();
            for (HireDates hd : workers)
                if (hd.getWorker().findCountry(c)) workersTemp.add(hd.getWorker());
            CountryWithWorkers.put( c, workersTemp);
        }

        return CountryWithWorkers;
    }

    public void showCountries(){
        Country country;
        TreeSet workers;
        for( Map.Entry<Country, TreeSet <Worker>> pair : CountryWithWorkers.entrySet()) {
           country= pair.getKey();
           System.out.println(country);
           workers  = pair.getValue();System.out.println(workers);
        }
    }
}
