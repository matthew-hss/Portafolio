/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Place;
import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.SaleService;
import cl.duoc.portafolio.service.VoucherService;
import java.io.Serializable;
import java.util.ArrayList;
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
@Qualifier("saleAdminBean")
public class SaleAdminBean implements Serializable {
    
    private static final long serialVersionUID = 559864478745473255L;
    
    @Resource(name = "saleService")
    private transient SaleService saleService;
    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    
    private List<Sale> sales = null;
    private List<Place> places = null;
    private List<Voucher> vouchers = null;
    private Sale sale = null;
    private boolean edit = false;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SaleAdminBean.class);
    
    @PostConstruct
    public void init() {
        places = saleService.getPlaces();
        vouchers = voucherService.getVouchers();
        refresh();
    }
    
    public void refresh() {
        sale = new Sale();
        sales = saleService.getSales();
        edit = false;
    }
    
    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }
    
    public String process() {
        if (sale != null) {
            try {
                Sale save = saleService.save(sale);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("saleSaved");                    
                } else {
                    FacesUtils.errorMessage("saleNotSaved");
                }                
            } catch (Exception e) {
                LOGGER.debug("Error al guardar venta: {}", e.toString(), e);
                FacesUtils.fatalMessage("saleNotSaved");
            }            
        }
        return StringUtils.EMPTY;
    }
    
    public String delete(){
        if(sale != null){
            try {
                boolean ok = saleService.delete(sale);
                if(ok){
                    refresh();
                    FacesUtils.infoMessage("saleDeleted");                    
                }else
                    FacesUtils.errorMessage("saleNotDeleted");
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar venta: {}", e.toString(), e);
                FacesUtils.fatalMessage("saleNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }   

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
    
    
}
