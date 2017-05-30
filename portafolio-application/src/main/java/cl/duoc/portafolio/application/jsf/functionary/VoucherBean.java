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
import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.Workshift;
import cl.duoc.portafolio.model.WsAssignment;
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
 * @author matthew
 */
@Component
@Scope("view")
@Qualifier("voucherBean")
public class VoucherBean implements Serializable {

    private static final long serialVersionUID = 559864478748547445L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "saleService")
    private transient SaleService saleService;
    @Resource(name = "sessionBean")
    private transient SessionBean sessionBean;

    private List<MealService> mealServices = null;
    private List<MealService> availableMealServices = null;
    private MealService selectedMealService = null;

    private MealService mealService1 = null;
    private MealService mealService2 = null;
    private boolean disableMS1 = false;
    private boolean disableMS2 = false;

    private Functionary functionary = null;
    private WsAssignment wsAssignment = null;
    private Workshift workshift = null;
    private Date today = null;

    private List<Voucher> vouchers = null;
    private Voucher generatedVoucher = null;
    private boolean showVoucher = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherBean.class);

    @PostConstruct
    public void init() {
        functionary = sessionBean.getFunctionary();
        wsAssignment = functionaryService.getWsAssignment(functionary);
        workshift = wsAssignment.getWorkshift();
        //workshift = functionaryService.getWorkshift(Long.parseLong("2"));
        mealServices = new ArrayList<>();
        availableMealServices = new ArrayList<>();
        vouchers = new ArrayList<>();
        loadMealServices();
        refresh();
    }

    public void loadMealServices() {
        mealServices = saleService.getMealServices();
        if (!mealServices.isEmpty()) {
            for (MealService ms : mealServices) {
                if ((ms.getStartTime().compareTo(workshift.getStartTime()) >= 0
                        && ms.getStartTime().compareTo(workshift.getEndTime()) < 0)
                        || (ms.getEndTime().compareTo(workshift.getStartTime()) > 0
                        && ms.getEndTime().compareTo(workshift.getEndTime()) <= 0)) {
                    availableMealServices.add(ms);
                }
            }
        }
        mealService1 = availableMealServices.get(0);
        mealService2 = availableMealServices.get(1);
    }

    public void refresh() {
        today = new Date();
        vouchers = voucherService.getVouchers(today);
        for (Voucher v : vouchers) {
            if (v.getFunctionary().equals(functionary)) {
                if (v.getCode().substring(10).equals(mealService1.getId().toString()+functionary.getId())) {
                    disableMS1 = true;
                } else if (v.getCode().substring(10).equals(mealService2.getId().toString()+functionary.getId())) {
                    disableMS2 = true;
                }
            }
        }
    }

    public String process() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Voucher voucher = new Voucher();

        try {
            String code = sdf.format(today) + "00" + selectedMealService.getId() + functionary.getId();
            voucher.setCode(code);
            voucher.setFunctionary(functionary);
            voucher.setVoucherAmount(voucherService.getVoucherAmount(functionary.getJobTitle(), selectedMealService));
            voucher.setUsed(true);
            voucher.setDateTime(new Date());

            generatedVoucher = voucherService.save(voucher);

            if (generatedVoucher != null) {
                refresh();
                showVoucher = true;
                FacesUtils.infoMessage("voucherPrinted");
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

    public WsAssignment getWsAssignment() {
        return wsAssignment;
    }

    public void setWsAssignment(WsAssignment wsAssignment) {
        this.wsAssignment = wsAssignment;
    }

    public Workshift getWorkshift() {
        return workshift;
    }

    public void setWorkshift(Workshift workshift) {
        this.workshift = workshift;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public List<MealService> getAvailableMealServices() {
        return availableMealServices;
    }

    public void setAvailableMealServices(List<MealService> availableMealServices) {
        this.availableMealServices = availableMealServices;
    }

    public MealService getSelectedMealService() {
        return selectedMealService;
    }

    public void setSelectedMealService(MealService selectedMealService) {
        this.selectedMealService = selectedMealService;
    }

    public MealService getMealService1() {
        return mealService1;
    }

    public void setMealService1(MealService mealService1) {
        this.mealService1 = mealService1;
    }

    public MealService getMealService2() {
        return mealService2;
    }

    public void setMealService2(MealService mealService2) {
        this.mealService2 = mealService2;
    }

    public boolean isDisableMS1() {
        return disableMS1;
    }

    public void setDisableMS1(boolean disableMS1) {
        this.disableMS1 = disableMS1;
    }

    public boolean isDisableMS2() {
        return disableMS2;
    }

    public void setDisableMS2(boolean disableMS2) {
        this.disableMS2 = disableMS2;
    }

    public Voucher getGeneratedVoucher() {
        return generatedVoucher;
    }

    public void setGeneratedVoucher(Voucher generatedVoucher) {
        this.generatedVoucher = generatedVoucher;
    }

    public boolean isShowVoucher() {
        return showVoucher;
    }

    public void setShowVoucher(boolean showVoucher) {
        this.showVoucher = showVoucher;
    }

}
