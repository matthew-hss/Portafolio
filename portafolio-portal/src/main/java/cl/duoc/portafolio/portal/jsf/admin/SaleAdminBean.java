/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Place;
import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SaleItem;
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
    private List<SaleItem> saleItems = null;
    private List<Place> places = null;
    private List<Product> products = null;
    private Voucher voucher = null;
    private Sale sale = null;
    private SaleItem newSaleItem = null;
    private SaleItem selectedSaleItem = null;
    private SaleItem lastSaleItem = null;
    private String voucherCode = null;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SaleAdminBean.class);
    
    @PostConstruct
    public void init() {
        places = saleService.getPlaces();
        products = saleService.getProducts();
        refresh();
    }
    
    public void refresh() {
        sale = new Sale();
        sales = saleService.getSales();
        //saleItems = saleService.getSaleItems();
        saleItems = new ArrayList<>();
        newSaleItem = new SaleItem();
        newSaleItem.setSale(sale);
        saleItems.add(newSaleItem);
    }
    
    public String addItem() {
        lastSaleItem = saleItems.get(saleItems.size() - 1);
        if (lastSaleItem.getProduct() != null && lastSaleItem.getQuantity() > 0) {
            newSaleItem = new SaleItem();
            newSaleItem.setSale(sale);
            saleItems.add(newSaleItem);
        }
        return StringUtils.EMPTY;
    }
    
    public String removeItem() {
        saleItems.remove(selectedSaleItem);
        return StringUtils.EMPTY;
    }
    
    public String process() {
        if (sale != null) {
            try {
                lastSaleItem = saleItems.get(saleItems.size() - 1);
                if (lastSaleItem.getProduct() != null && lastSaleItem.getQuantity() > 0) {
                    Integer total = 0;
                    for (SaleItem s : saleItems) {
                        total += s.getQuantity() * s.getProduct().getPrice();
                    }
                    
                    if (!voucherCode.trim().equals("")) {
                        voucher = voucherService.getVoucher(voucherCode);
                        if (voucher != null && voucher.getSale() == null) {
                            sale.setVoucher(voucher);
                            total -= Integer.parseInt(voucher.getVoucherAmount().getAmount().toString());
                            if (total < 0) {
                                total = 0;
                            }
                            sale.setTotal(Long.parseLong(total.toString()));
                            Sale save = saleService.save(sale);
                            if (save != null) {
                                voucher.setSale(save);
                                voucher.setUsed(true);
                                Voucher voucherUpdated = voucherService.save(voucher);
                                if (voucherUpdated != null) {
                                    FacesUtils.infoMessage("voucherUsed");
                                }
                                SaleItem saveItem = null;
                                for (SaleItem s : saleItems) {
                                    s.setSale(save);
                                    saveItem = saleService.save(s);
                                }
                                refresh();
                                FacesUtils.infoMessage("saleSaved");
                            } else {
                                FacesUtils.errorMessage("saleNotSaved");
                            }
                        } else {
                            FacesUtils.errorMessage("voucherNotAvailable");
                        }
                    } else {
                        sale.setTotal(Long.parseLong(total.toString()));
                        Sale save = saleService.save(sale);
                        if (save != null) {
                            SaleItem saveItem = null;
                            for (SaleItem s : saleItems) {
                                s.setSale(save);
                                saveItem = saleService.save(s);
                            }
                            refresh();
                            FacesUtils.infoMessage("saleSaved");
                        } else {
                            FacesUtils.errorMessage("saleNotSaved");
                        }
                    }
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
    
    public List<Sale> getSales() {
        return sales;
    }
    
    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    
    public Sale getSale() {
        return sale;
    }
    
    public void setSale(Sale sale) {
        this.sale = sale;
    }
    
    public List<Place> getPlaces() {
        return places;
    }
    
    public void setPlaces(List<Place> places) {
        this.places = places;
    }
    
    public List<SaleItem> getSaleItems() {
        return saleItems;
    }
    
    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public SaleItem getSelectedSaleItem() {
        return selectedSaleItem;
    }
    
    public void setSelectedSaleItem(SaleItem selectedSaleItem) {
        this.selectedSaleItem = selectedSaleItem;
    }
    
    public SaleItem getNewSaleItem() {
        return newSaleItem;
    }
    
    public void setNewSaleItem(SaleItem newSaleItem) {
        this.newSaleItem = newSaleItem;
    }
    
    public SaleItem getLastSaleItem() {
        return lastSaleItem;
    }
    
    public void setLastSaleItem(SaleItem lastSaleItem) {
        this.lastSaleItem = lastSaleItem;
    }
    
    public String getVoucherCode() {
        return voucherCode;
    }
    
    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }
    
    public Voucher getVoucher() {
        return voucher;
    }
    
    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    
}
