package restapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import restapi.utils.ProductUtils;
import restapi.enumerations.TagEnum;
import restapi.model.Product;
import restapi.model.ProductDto;
import restapi.model.ProductListDto;
import restapi.model.ProductWithEuclideanDistanteDto;

@Service
public class ProductService {

	@Resource
	ProductUtils productUtils;

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

  public List<ProductWithEuclideanDistanteDto> calculateThreeMostSimilarProducts(int productId, ProductListDto products) {
	  
	  List<ProductDto> productsList = products.getProducts();
	  List<ProductWithEuclideanDistanteDto> productWithEuclideanDistanteDtoList = new ArrayList<ProductWithEuclideanDistanteDto>();
	  
	  Optional<ProductDto> findAny = productsList.stream().filter(x -> x.getId() == productId).findAny();
	  if (findAny.isPresent()) {
		  ProductDto productToCompareDto = findAny.get();
		  productsList.remove(productToCompareDto);
		  productsList.stream().forEach(p -> {
			  ProductWithEuclideanDistanteDto productWithEuclideanDistanteDto = new ProductWithEuclideanDistanteDto(p.getId(),p.getName());

			  // D = sqrt((v1[0] - v2[0])^2 + (v1[1] - v2[1])^2 + .. + (v1[N-1] - v2[N-1])^2) is the distance between the vectors  v1 and v
			  double euclideanDistance = productUtils.euclideanDistance(productToCompareDto.getTagsVector(), p.getTagsVector());

			  //Similarity = S = 1/(1 + D), where D is the Euclidean distance
			  double similarity = 1 / (1 + euclideanDistance);

			  productWithEuclideanDistanteDto.setSimilarity(similarity);
			  productWithEuclideanDistanteDtoList.add(productWithEuclideanDistanteDto);
		  });
		  
	  }
	  // Sort by euclidean distance to the product indicated to compare.
	  Collections.sort(productWithEuclideanDistanteDtoList);

	  // Returns the first three
	  return productWithEuclideanDistanteDtoList.stream().limit(3).collect(Collectors.toList());
	  
  }

public ProductUtils getProductUtils() {
	return productUtils;
}

public void setProductUtils(ProductUtils productUtils) {
	this.productUtils = productUtils;
}

}
