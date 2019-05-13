package restapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import restapi.model.Product;
import restapi.model.ProductListDto;
import restapi.model.ProductWithEuclideanDistanteDto;
import restapi.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Receives a payload of products and for each product creates a vector of tags of dimension 20, where each position of the vector
     *  corresponds to a characteristic and has a value equal to 1 if the product has the characteristic, otherwise the value is equal to zero.
     * @param products
     * @return
     */
    @RequestMapping("/products")
    public ProductListDto products(@RequestBody List<Product> products) {
        return productService.calculateProductsListWithTagsVector(products);
    }

    /**
     * The API should receive as input the id (the unique identifier of the product) and the payload generated in the previous endpoint,
     *  which contains the vector of tags in each product. For the product supplied in the input (through the id), the API should fetch the 
     *  product in the input payload of the search engine and calculate the similarity between the input product and all other products,
     *  and return a list with the three most similar products.
     *  The similarity between two products can be calculated taking into account the Euclidean distance between their tag vectors, and 
     *  the similarity is inversely proportional to the distance. That is, we can define the similarity S between two products p1 and p2,
     *  which have vectors of tags v1 and v2 of dimension N, such as:
     *     S = 1/(1 + D), where  
     *     D = sqrt((v1[0] - v2[0])^2 + (v1[1] - v2[1])^2 + .. + (v1[N-1] - v2[N-1])^2) is the distance between the vectors  v1 and v2
	 *
     * @param productId the product supplied
     * @param products the payload generated in the previous method
     * @return a list with the three most similar products.
     */
    @RequestMapping(value="/productsSearch/{productId}", method=RequestMethod.POST)
    public List<ProductWithEuclideanDistanteDto> productSearch(@PathVariable("productId") int productId, @RequestBody ProductListDto products) {
        return productService.calculateThreeMostSimilarProducts(productId, products);
    }
}
