package restapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restapi.model.Product;
import restapi.model.ProductListDto;
import restapi.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public ProductListDto product(@RequestBody List<Product> products) {
        return productService.calculateProductsListWithTagsVector(products);
    }
}
