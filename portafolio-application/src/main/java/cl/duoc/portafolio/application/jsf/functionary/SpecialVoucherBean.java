/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.application.jsf.functionary;

import cl.duoc.portafolio.application.jsf.SessionBean;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.application.utils.FacesUtils;
import cl.duoc.portafolio.model.SpecialVoucher;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.service.SaleService;
import cl.duoc.portafolio.service.VoucherService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@Qualifier("specialVoucherBean")
public class SpecialVoucherBean implements Serializable {

    private static final long serialVersionUID = 559864478748547445L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "saleService")
    private transient SaleService saleService;
    @Resource(name = "sessionBean")
    private transient SessionBean sessionBean;

    private Functionary functionary = null;
    private SpecialVoucher selectedSpecialVoucher = null;
    private List<SpecialVoucher> specialVouchers = null;
    private boolean showVoucher = false;
    private boolean hasSpecialVouchers = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialVoucherBean.class);

    @PostConstruct
    public void init() {
        functionary = sessionBean.getFunctionary();
        specialVouchers = new ArrayList<>();
        refresh();
    }

    public void refresh() {
        specialVouchers = voucherService.getSpecialVouchers(functionary, false);        
        if (!specialVouchers.isEmpty()) {
            hasSpecialVouchers = true;
        }
    }

    public String process() {
        try {
            if (selectedSpecialVoucher != null) {
                showVoucher = true;
                refresh();
                //FacesUtils.infoMessage("voucherPrinted");
            } else {
                FacesUtils.errorMessage("voucherNotPrinted");
            }
        } catch (Exception e) {
            LOGGER.debug("Error al imprimir vale: {}", e.toString(), e);
            FacesUtils.fatalMessage("voucherNotPrinted");
        }

        return StringUtils.EMPTY;
    }

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }

    public SpecialVoucher getSelectedSpecialVoucher() {
        return selectedSpecialVoucher;
    }

    public void setSelectedSpecialVoucher(SpecialVoucher selectedSpecialVoucher) {
        this.selectedSpecialVoucher = selectedSpecialVoucher;
    }

    public List<SpecialVoucher> getSpecialVouchers() {
        return specialVouchers;
    }

    public void setSpecialVouchers(List<SpecialVoucher> specialVouchers) {
        this.specialVouchers = specialVouchers;
    }

    public boolean isHasSpecialVouchers() {
        return hasSpecialVouchers;
    }

    public void setHasSpecialVouchers(boolean hasSpecialVouchers) {
        this.hasSpecialVouchers = hasSpecialVouchers;
    }

    public boolean isShowVoucher() {
        return showVoucher;
    }

    public void setShowVoucher(boolean showVoucher) {
        this.showVoucher = showVoucher;
    }

}
