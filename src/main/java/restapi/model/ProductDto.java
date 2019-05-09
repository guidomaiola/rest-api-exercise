package restapi.model;

import restapi.enumerations.TagEnum;

public class ProductDto extends Product {

  int[] tagsVector = new int[20];

  public ProductDto(int idParam, String nameParam, TagEnum[] tagsParam, int[] tagsVectorParam) {
    super(idParam, nameParam, tagsParam);
    this.tagsVector = tagsVectorParam;
  }

  public int[] getTagsVector() {
    return tagsVector;
  }

}
