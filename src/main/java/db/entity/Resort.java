package db.entity;

import java.util.Comparator;

public class Resort extends Entity implements Comparator<Resort> {

    private Long countryId;
    private String name;
    private String description;
    private String imagePath;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int compare(Resort o1, Resort o2) {
        return o1.name.compareTo(o2.getName());
    }

    @Override
    public String toString() {
        return "Resort{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
