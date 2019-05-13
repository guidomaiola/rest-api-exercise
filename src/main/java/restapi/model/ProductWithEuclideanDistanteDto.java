package restapi.model;

public class ProductWithEuclideanDistanteDto extends ProductBase implements Comparable<ProductWithEuclideanDistanteDto> {

	double similarity = 0d;
	  
	public ProductWithEuclideanDistanteDto(int id, String name) {
		super(id, name);
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	@Override
	public int compareTo(ProductWithEuclideanDistanteDto o) {
		return this.getSimilarity() < o.getSimilarity() ? -1 : this.getSimilarity() > o.getSimilarity() ? 1 : 0;
	}
}
