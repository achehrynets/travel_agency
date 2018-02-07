package db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
