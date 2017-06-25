/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.application.jsf.functionary;

import cl.duoc.portafolio.application.utils.FacesUtils;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
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
@Qualifier("createSpecialVoucherBean")
public class CreateSpecialVoucherBean implements Serializable {

    private static final long serialVersionUID = 559864478748547445L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;

    private SpecialVoucher specialVoucher = null;
    private List<SpecialVoucher> specialVouchers = null;
    private Integer quantity = null;
    private Long amount = null;
    private Long lastAmount = null;
    private boolean showVouchers = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSpecialVoucherBean.class);

    @PostConstruct
    public void init() {
        specialVouchers = new ArrayList<>();
    }

    public void refresh() {
        quantity = null;
        amount = null;
    }

    public String process() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Functionary functionary = functionaryService.getFunctionary(99999999);
            for (int i = 1; i <= quantity; i++) {
                specialVoucher = new SpecialVoucher();
                String code = "777" + sdf.format(new Date()) + "00" + i;
                specialVoucher.setFunctionary(functionary);
                specialVoucher.setAmount(amount);
                specialVoucher.setCode(code);
                specialVoucher.setUsed(false);
                specialVoucher.setDateTime(new Date());

                SpecialVoucher save = voucherService.save(specialVoucher);
                if (save != null) {
                    specialVouchers.add(save);
                } else {
                    FacesUtils.errorMessage("specialVoucherNotSaved");
                }
            }
            if(!specialVouchers.isEmpty()){
                lastAmount = amount;
                refresh();
                showVouchers = true;
            }
            
        } catch (Exception e) {
            LOGGER.debug("Error al guardar vale especial: {}", e.toString(), e);
            FacesUtils.fatalMessage("specialVoucherNotSaved");
        }
        return StringUtils.EMPTY;
    }

    public List<SpecialVoucher> getSpecialVouchers() {
        return specialVouchers;
    }

    public void setSpecialVouchers(List<SpecialVoucher> specialVouchers) {
        this.specialVouchers = specialVouchers;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(Long lastAmount) {
        this.lastAmount = lastAmount;
    }

    public boolean isShowVouchers() {
        return showVouchers;
    }

    public void setShowVouchers(boolean showVouchers) {
        this.showVouchers = showVouchers;
    }
    
}
