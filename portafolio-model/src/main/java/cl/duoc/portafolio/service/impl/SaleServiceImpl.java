package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.model.Place;
import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SaleItem;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.repository.PlaceRepository;
import cl.duoc.portafolio.repository.ProductRepository;
import cl.duoc.portafolio.repository.SaleItemRepository;
import cl.duoc.portafolio.repository.SaleRepository;
import cl.duoc.portafolio.service.SaleService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author matthew
 */
@Service("functionaryService")
public class SaleServiceImpl implements SaleService, Serializable {

    private static final long serialVersionUID = 7419845182456041234L;

    @Resource(name = "saleRepository")
    private SaleRepository saleRepository;
    @Resource(name = "placeRepository")
    private PlaceRepository placeRepository;
    @Resource(name = "productRepository")
    private ProductRepository productRepository;
    @Resource(name = "saleItemRepository")
    private SaleItemRepository saleItemRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Override
    public Sale getSale(Long id) {
        Sale sale = null;
        try {
            if (id != null && id > 0) {
                sale = saleRepository.findById(id);
            }
        } catch (Exception e) {
            sale = null;
            LOGGER.error("Error al obtener venta: {}", e.toString());
            LOGGER.debug("Error al obtener venta: {}", e.toString());
        }
        return sale;
    }

    @Override
    public List<Sale> getSales() {
        List<Sale> sales = new ArrayList<>();
        try {
            sales = saleRepository.findAll();
        } catch (Exception e) {
            sales = null;
            LOGGER.error("Error al obtener ventas: {}", e.toString());
            LOGGER.debug("Error al obtener ventas: {}", e.toString());
        }
        return sales;
    }

    @Override
    public List<Sale> getSales(Place place) {
        List<Sale> sales = new ArrayList<>();
        try {
            if (place != null) {
                sales = saleRepository.findByPlace(place);
            }
        } catch (Exception e) {
            sales = null;
            LOGGER.error("Error al obtener ventas: {}", e.toString());
            LOGGER.debug("Error al obtener ventas: {}", e.toString());
        }
        return sales;
    }

    @Override
    public List<Sale> getSales(Voucher voucher) {
        List<Sale> sales = new ArrayList<>();
        try {
            if (voucher != null) {
                sales = saleRepository.findByVoucher(voucher);
            }
        } catch (Exception e) {
            sales = null;
            LOGGER.error("Error al obtener ventas: {}", e.toString());
            LOGGER.debug("Error al obtener ventas: {}", e.toString());
        }
        return sales;
    }

    @Override
    @Transactional
    public Sale save(Sale sale) {
        Sale saved = null;
        try {
            if (sale != null) {
                saved = saleRepository.save(sale);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar venta: {}", e.toString());
            LOGGER.debug("Error al guardar venta: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(Sale sale) {
        boolean deleted = false;
        try {
            saleRepository.delete(sale);
            deleted = true;
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar venta: {}", e.toString());
            LOGGER.debug("Error al eliminar venta: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public Place getPlace(Long id) {
        Place place = null;
        try {
            if (id != null && id > 0) {
                place = placeRepository.findById(id);
            }
        } catch (Exception e) {
            place = null;
            LOGGER.error("Error al obtener lugar: {}", e.toString());
            LOGGER.debug("Error al obtener lugar: {}", e.toString());
        }
        return place;
    }

    @Override
    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        try {
            places = placeRepository.findAll();
        } catch (Exception e) {
            places = new ArrayList<>();
            LOGGER.error("Error al obtener lugares: {}", e.toString());
            LOGGER.debug("Error al obtener lugares: {}", e.toString());
        }
        return places;
    }

    @Override
    @Transactional
    public Place save(Place place) {
        Place saved = null;
        try {
            if (place != null) {
                saved = placeRepository.save(saved);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar lugar: {}", e.toString());
            LOGGER.debug("Error al guardar lugar: {}", e.toString());
        }
        return place;
    }

    @Override
    @Transactional
    public boolean delete(Place place) {
        boolean deleted = false;
        try {
            if (place != null) {
                placeRepository.delete(place);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar lugar: {}", e.toString());
            LOGGER.debug("Error al eliminar lugar: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public Product getProduct(Long id) {
        Product product = null;
        try {
            if (id != null && id > 0) {
                product = productRepository.findById(id);
            }
        } catch (Exception e) {
            product = null;
            LOGGER.error("Error al obtener producto: {}", e.toString());
            LOGGER.debug("Error al obtener producto: {}", e.toString());
        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try {
            products = productRepository.findAll();
        } catch (Exception e) {
            products = new ArrayList<>();
            LOGGER.error("Error al obtener productos: {}", e.toString());
            LOGGER.debug("Error al obtener productos: {}", e.toString());
        }
        return products;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        Product saved = null;
        try {
            if (product != null) {
                saved = productRepository.save(product);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar producto: {}", e.toString());
            LOGGER.debug("Error al guardar producto: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(Product product) {
        boolean deleted = false;
        try {
            if (product != null) {
                productRepository.delete(product);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar producto: {}", e.toString());
            LOGGER.debug("Error al eliminar producto: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public SaleItem getSaleItem(Long id) {
        SaleItem saleItem = null;
        try {
            if (id != null && id > 0) {
                saleItem = saleItemRepository.findById(id);
            }
        } catch (Exception e) {
            saleItem = null;
            LOGGER.error("Error al obtener item de venta: {}", e.toString());
            LOGGER.debug("Error al obtener item de venta: {}", e.toString());
        }
        return saleItem;
    }

    @Override
    public List<SaleItem> getSaleItems() {
        List<SaleItem> salesItems = new ArrayList<>();
        try {
            salesItems = saleItemRepository.findAll();
        } catch (Exception e) {
            salesItems = new ArrayList<>();
            LOGGER.error("Error al obtener items de ventas: {}", e.toString());
            LOGGER.debug("Error al obtener items de ventas: {}", e.toString());
        }
        return salesItems;
    }

    @Override
    public List<SaleItem> getSaleItems(Sale sale) {
        List<SaleItem> salesItems = new ArrayList<>();
        try {
            if (sale != null) {
                salesItems = saleItemRepository.findBySale(sale);
            }
        } catch (Exception e) {
            salesItems = new ArrayList<>();
            LOGGER.error("Error al obtener items de ventas: {}", e.toString());
            LOGGER.debug("Error al obtener items de ventas: {}", e.toString());
        }
        return salesItems;
    }

    @Override
    public List<SaleItem> getSaleItems(Product product) {
        List<SaleItem> salesItems = new ArrayList<>();
        try {
            if (product != null) {
                salesItems = saleItemRepository.findByProduct(product);
            }
        } catch (Exception e) {
            salesItems = new ArrayList<>();
            LOGGER.error("Error al obtener items de ventas: {}", e.toString());
            LOGGER.debug("Error al obtener items de ventas: {}", e.toString());
        }
        return salesItems;
    }

    @Override
    @Transactional
    public SaleItem save(SaleItem saleItem) {
        SaleItem saved = null;
        try {
            if(saleItem!=null){
                saved = saleItemRepository.save(saleItem);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar item de venta: {}", e.toString());
            LOGGER.debug("Error al guardar item de venta: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(SaleItem saleItem) {
        boolean deleted = false;
        try {
            if(saleItem != null){
                saleItemRepository.delete(saleItem);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar item de venta: {}", e.toString());
            LOGGER.debug("Error al eliminar item de venta: {}", e.toString());
        }
        return deleted;
    }

}
