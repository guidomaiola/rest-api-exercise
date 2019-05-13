package restapi.model;

public class ProductBase {
	private final int id;
    private String name;
    
    public ProductBase(int id, String name) {
    	this.id = id;
    	this.name = name;
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
}
