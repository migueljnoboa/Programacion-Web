package org.example.Data;

public class Tag {
    private long id;
    private String tag;

    public Tag(long id, String tag){
        super();
        this.id = id;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

