package restapi.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import restapi.enumerations.TagEnum;
import restapi.model.Product;
import restapi.model.ProductDto;
import restapi.model.ProductListDto;

@Service
public class ProductService {

  /**
   * This method receives a product and returns the same list, where each product adds a vector of tags of dimension 20,
   *  where each position of the vector corresponds to a characteristic and has a value equal to 1 if the product has the 
   *  characteristic, otherwise the value is equal to zero.
   * @param products an input list of products
   * @return a list of productDto where each product adds the tagsVector
   */
  public ProductListDto calculateProductsListWithTagsVector(List<Product> products) {
    ProductListDto productsDto= new ProductListDto();
    products.stream().forEach(p -> {
      productsDto.addProduct(calculateTagsVector(p));
    });
    return productsDto;
  }
  
  /**
   * Returns a productDto adding the tagsVector to the input product.
   * @param p the input product
   * @return the productDto with the tagsVector
   */
  private ProductDto calculateTagsVector(Product p) {
    int[] tagsVector = new int[20];
    List<TagEnum> pTags = Arrays.asList(p.getTags());
    for (int i = 0; i <20 ; i++) {
      TagEnum tag = TagEnum.getById(i);
      tagsVector[i] = (pTags.contains(tag)) ? 1 : 0;
    }
    return new ProductDto(p.getId(),p.getName(),p.getTags(),tagsVector);
  }

}
