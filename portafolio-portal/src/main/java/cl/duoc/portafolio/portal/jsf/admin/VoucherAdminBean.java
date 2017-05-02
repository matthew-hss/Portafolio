/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.service.SaleService;
import cl.duoc.portafolio.service.VoucherService;
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
@Qualifier("voucherAdminBean")
public class VoucherAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748547445L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "saleService")
    private transient SaleService saleService;

    private Voucher voucher = null;
    private List<Voucher> vouchers = null;
    private List<Functionary> functionaries = null;
    private List<Sale> sales = null;
    private boolean edit = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherAdminBean.class);

    @PostConstruct
    public void init() {
        functionaries = functionaryService.getFunctionaries();
        sales = saleService.getSales();
        refresh();
    }

    public void refresh() {
        vouchers = voucherService.getVouchers();
        voucher = new Voucher();
        edit = false;
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String process() {
        if (voucher != null) {
            try {
                Voucher save = voucherService.save(voucher);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("voucherSaved");
                } else {
                    FacesUtils.errorMessage("voucherNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al guardar vale: {}", e.toString(), e);
                FacesUtils.fatalMessage("voucherNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public String delete() {
        if (voucher != null) {
            try {
                boolean ok = voucherService.delete(voucher);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("voucherDeleted");
                } else {
                    FacesUtils.errorMessage("voucherNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar vale: {}", e.toString(), e);
                FacesUtils.fatalMessage("voucherNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public List<Functionary> getFunctionaries() {
        return functionaries;
    }

    public void setFunctionaries(List<Functionary> functionaries) {
        this.functionaries = functionaries;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
