/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SpecialVoucher;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.service.SaleService;
import cl.duoc.portafolio.service.VoucherService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Javier González M.
 */
@Component
@Scope("view")
@Qualifier("specialVoucherAdminBean")
public class SpecialVoucherAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748547445L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "saleService")
    private transient SaleService saleService;

    private SpecialVoucher specialVoucher = null;
    private List<SpecialVoucher> specialVouchers = null;
    private List<Functionary> functionaries = null;
    private List<Sale> sales = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialVoucherAdminBean.class);

    @PostConstruct
    public void init() {
        functionaries = functionaryService.getFunctionaries();
        sales = saleService.getSales();
        refresh();
    }

    public void refresh() {
        specialVouchers = voucherService.getSpecialVouchers();
        specialVoucher = new SpecialVoucher();
    }

    public String process() {
        if (specialVoucher != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                String code = "777" + sdf.format(new Date());
                specialVoucher.setCode(code);
                specialVoucher.setUsed(false);
                specialVoucher.setDateTime(new Date());

                SpecialVoucher save = voucherService.save(specialVoucher);
                
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("specialVoucherSaved");
                } else {
                    FacesUtils.errorMessage("specialVoucherNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al guardar vale especial: {}", e.toString(), e);
                FacesUtils.fatalMessage("specialVoucherNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public String delete() {
        if (specialVoucher != null) {
            try {
                boolean ok = voucherService.delete(specialVoucher);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("specialVoucherDeleted");
                } else {
                    FacesUtils.errorMessage("specialVoucherNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar vale especial: {}", e.toString(), e);
                FacesUtils.fatalMessage("specialVoucherNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public SpecialVoucher getSpecialVoucher() {
        return specialVoucher;
    }

    public void setSpecialVoucher(SpecialVoucher specialVoucher) {
        this.specialVoucher = specialVoucher;
    }

    public List<SpecialVoucher> getSpecialVouchers() {
        return specialVouchers;
    }

    public void setSpecialVouchers(List<SpecialVoucher> specialVouchers) {
        this.specialVouchers = specialVouchers;
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
}
