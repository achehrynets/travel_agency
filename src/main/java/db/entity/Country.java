package db.entity;

import java.util.Comparator;

public class Country extends Entity implements Comparator<Country> {

    private String name;
    private boolean visa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisa() {
        return visa;
    }

    public void setVisa(boolean visa) {
        this.visa = visa;
    }

    @Override
    public int compare(Country o1, Country o2) {
        return o1.name.compareTo(o2.getName());
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
