package alpos.entity;

import java.io.Serializable;

public class Hashtag implements Serializable {
    private Integer id;
    private String  name;

    public Hashtag() {

    }

    public Hashtag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}