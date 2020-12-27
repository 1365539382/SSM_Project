package nuc.zy.service;
import nuc.zy.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll(int page ,int size) ;
    public void addProduct(Product product) ;

    void deleteProductById(String id);

    public Product findById(String id);

    void updateById(String id, Integer status);
}
