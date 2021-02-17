package productcrudapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productcrudapp.model.Product;

@Component
public class ProductDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Transactional
	public void saveProduct(Product product) {
		this.hibernateTemplate.saveOrUpdate(product);
	}
	
	public List<Product> getAllProduct(){
		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
	}
	
	public Product getOneProduct(int pid) {
		Product product = this.hibernateTemplate.get(Product.class,pid );
		return product;
	}
	
	@Transactional
	public void deleteProduct(int pid) {
		Product product = this.hibernateTemplate.get(Product.class,pid );
		this.hibernateTemplate.delete(product);
	}
	
}
