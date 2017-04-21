/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.SaleService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author matthew
 */
@Component
@Scope("view")
@Qualifier("productAdminBean")
public class ProductAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748547255L;

    @Resource(name = "saleService")
    private transient SaleService saleService;

    private Product product = null;
    private List<Product> products = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductAdminBean.class);

    @PostConstruct
    public void init() {
        refresh();
    }

    public void refresh() {
        edit = false;
        product = new Product();
        products = saleService.getProducts();
    }

    public String editProduct() {
        return StringUtils.EMPTY;
    }

    public String deleteProduct() {
        if (product != null) {
            try {
                boolean ok = saleService.delete(product);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("productDeleted");
                } else {
                    FacesUtils.errorMessage("productNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar producto: {}", e.toString(), e);
                FacesUtils.fatalMessage("productNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }
    
    public String procces(){
        if(product != null){
            try {
                Product save = saleService.save(product);
                if(save!=null){
                    refresh();
                    FacesUtils.infoMessage("productSaved");
                }else{
                    FacesUtils.infoMessage("productNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al grabar producto: {}", e.toString(), e);
                FacesUtils.fatalMessage("productNotSaved");
            }
        }
        return StringUtils.EMPTY;                
    }

    public SaleService getSaleService() {
        return saleService;
    }

    public void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
}