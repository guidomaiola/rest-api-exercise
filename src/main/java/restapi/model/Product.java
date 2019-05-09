package restapi.model;

import restapi.enumerations.TagEnum;

public class Product {

    private final int id;
    private String name;
    private TagEnum[] tags;

    public Product(int id, String name, TagEnum[] tags) {
        this.id = id;
        this.name = name;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setTags(TagEnum[] tags) {
      this.tags = tags;
    }
    
    public TagEnum[] getTags() {
      return tags;
    }
}