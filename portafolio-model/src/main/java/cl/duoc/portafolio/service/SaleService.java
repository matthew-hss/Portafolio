package cl.duoc.portafolio.service;

import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.Place;
import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SaleItem;
import cl.duoc.portafolio.model.Voucher;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface SaleService {
    
    public Sale getSale(final Long id);
    
    public List<Sale> getSales();
    
    public List<Sale> getSales(final Place place);
    
    public List<Sale> getSales(final Voucher voucher);
    
    public Sale save(final Sale sale);
    
    public boolean delete(final Sale sale);
    
    public Place getPlace(final Long id);
    
    public List<Place> getPlaces();
    
    public Place save(final Place place);
    
    public boolean delete(final Place place);
    
    public Product getProduct(final Long id);

    public List<Product> getProducts();
    
    public Product save(final Product product);
    
    public boolean delete(final Product product);
    
    public SaleItem getSaleItem(final Long id);
    
    public List<SaleItem> getSaleItems();
    
    public List<SaleItem> getSaleItems(final Sale sale);
    
    public List<SaleItem> getSaleItems(final Product product);
    
    public SaleItem save(final SaleItem saleItem);
    
    public boolean delete(final SaleItem saleItem);
    
    public MealService getMealService(final Long id);
    
    public List<MealService> getMealServices();
    
    public MealService getMealService(final Product product);
    
    public MealService save(final MealService mealService);
    
    public boolean delete(final MealService mealService);
}
