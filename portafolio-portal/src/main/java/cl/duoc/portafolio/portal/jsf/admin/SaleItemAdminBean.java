/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SaleItem;
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
@Qualifier("saleItemAdminBean")
public class SaleItemAdminBean implements Serializable {

    private static final long serialVersionUID = 559864455448547445L;

    @Resource(name = "saleService")
    private transient SaleService saleService;

    private SaleItem saleItem = null;
    private List<SaleItem> saleItems = null;
    private List<Sale> sales = null;
    private List<Product> products = null;
    private boolean edit = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleItemAdminBean.class);

    @PostConstruct
    public void init() {
        sales = saleService.getSales();
        products = saleService.getProducts();
        refresh();
    }

    public void refresh() {
        edit = false;
        saleItem = new SaleItem();
        saleItems = saleService.getSaleItems();
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String process() {
        if (saleItem != null) {
            try {
                SaleItem save = saleService.save(saleItem);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("saleItemSaved");
                }else{
                    FacesUtils.errorMessage("saleItemNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al guardar item de venta: {}", e.toString(), e);
                FacesUtils.fatalMessage("saleItemNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }
    
    public String delete(){
        if(saleItem!=null){
            try {
                boolean ok = saleService.delete(saleItem);
                if(ok){
                    refresh();
                    FacesUtils.infoMessage("saleItemDeleted");
                }else{
                    FacesUtils.errorMessage("saleItemNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar item de venta: {}", e.toString(), e);
                FacesUtils.fatalMessage("saleItemNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public SaleItem getSaleItem() {
        return saleItem;
    }

    public void setSaleItem(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
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
