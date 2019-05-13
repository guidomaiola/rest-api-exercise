package restapi.model;

import restapi.enumerations.TagEnum;

public class Product extends ProductBase {

    private TagEnum[] tags;

    public Product(int id, String name, TagEnum[] tags) {
        super(id,name);
        this.tags = tags;
    }

    public void setTags(TagEnum[] tags) {
      this.tags = tags;
    }
    
    public TagEnum[] getTags() {
      return tags;
    }

}