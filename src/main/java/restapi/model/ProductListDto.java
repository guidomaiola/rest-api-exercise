package restapi.model;

import java.util.ArrayList;
import java.util.List;

public class ProductListDto {
  
  private List<ProductDto> products = new ArrayList<ProductDto>();

  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }
  
  public void addProduct(ProductDto product) {
    this.products.add(product);
  }

}
