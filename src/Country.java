import java.util.Objects;

/**
 * Created by User on 04.07.2017.
 */
public class Country implements Comparable {
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Country c) {
        if (this.name.equals(c.getName())) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Object c) {
        Country countryT = (Country) c;
        if (this.getName().compareTo(countryT.getName())==0) return 0;
        if (this.getName().compareTo(countryT.getName())>0) return 1;
        if (this.getName().compareTo(countryT.getName())<0) return -1;
        return -1;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
