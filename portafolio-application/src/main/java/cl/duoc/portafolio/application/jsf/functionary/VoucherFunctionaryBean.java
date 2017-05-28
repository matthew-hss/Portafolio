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
import cl.duoc.portafolio.model.VoucherAmount;
import cl.duoc.portafolio.model.Workshift;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.service.SaleService;
import cl.duoc.portafolio.service.VoucherService;
import cl.duoc.portafolio.utils.CodeUtils;
import java.io.Serializable;
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
@Qualifier("voucherAdminBean")
public class VoucherFunctionaryBean implements Serializable {

    private static final long serialVersionUID = 559864478748547445L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "saleService")
    private transient SaleService saleService;
    @Resource(name = "sessionBean")
    private transient SessionBean sessionBean;

    private MealService mealService1 = null;
    private MealService mealService2 = null;

    private Functionary functionary = null;
    private WsAssignment wsAssignment = null;
    private Workshift workshift = null;
    private VoucherAmount voucherAmount1 = null;
    private VoucherAmount voucherAmount2 = null;
    private List<Voucher> vouchers = null;
    private Voucher voucher1 = null;
    private Voucher voucher2 = null;
    private Date today = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherFunctionaryBean.class);

    @PostConstruct
    public void init() {
        today = new Date();
        vouchers = new ArrayList<>();
        loadVouchers();
    }

    public void loadMealServices() {
//        Calendar startTime = Calendar.getInstance();
//        Calendar endTime = Calendar.getInstance();
//        startTime.setTime(workshift.getStartTime());
//        endTime.setTime(workshift.getEndTime());
//        int start = startTime.get(Calendar.HOUR_OF_DAY);
//        int end = endTime.get(Calendar.HOUR_OF_DAY);
        if (workshift.getId() == 1) {
            mealService1 = saleService.getMealService(Long.parseLong("1"));
            mealService2 = saleService.getMealService(Long.parseLong("2"));
        } else if (workshift.getId() == 2) {
            mealService1 = saleService.getMealService(Long.parseLong("3"));
            mealService2 = saleService.getMealService(Long.parseLong("4"));
        } else if (workshift.getId() == 3) {
            mealService1 = saleService.getMealService(Long.parseLong("5"));
            mealService2 = saleService.getMealService(Long.parseLong("1"));
        }
    }

    public void loadVouchers() {
        functionary = sessionBean.getFunctionary();
        List<Voucher> lista = voucherService.getVouchers(today);
        if (!vouchers.isEmpty()) {
            for (Voucher voucher : lista) {
                if (voucher.getFunctionary() == functionary) {
                    vouchers.add(voucher);
                }
            }
        } else {
            wsAssignment = functionaryService.getWsAssignment(functionary);
            workshift = functionaryService.getWorkshift(wsAssignment.getWorkshift().getId());
            loadMealServices();
            voucherAmount1 = voucherService.getVoucherAmount(functionary.getJobTitle(), mealService1);
            voucherAmount2 = voucherService.getVoucherAmount(functionary.getJobTitle(), mealService2);
            voucher1 = new Voucher();
            voucher1.setVoucherAmount(voucherAmount1);
            voucher2 = new Voucher();
            voucher2.setVoucherAmount(voucherAmount2);
        }
    }

    public String process(Voucher voucher) {
        if (voucher != null) {
            try {
                voucher.setCode(CodeUtils.generateCode(6));
                voucher.setFunctionary(functionary);
                voucher.setUsed(true);
                voucher.setSale(null);
                voucher.setDateTime(new Date());

                Voucher save = voucherService.save(voucher);
                if (save != null) {
                    FacesUtils.infoMessage("voucherPrinted");
                } else {
                    FacesUtils.errorMessage("voucherNotPrinted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al imprimir vale: {}", e.toString(), e);
                FacesUtils.fatalMessage("voucherNotPrinted");
            }
        }
        return StringUtils.EMPTY;
    }

    public VoucherService getVoucherService() {
        return voucherService;
    }

    public void setVoucherService(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    public FunctionaryService getFunctionaryService() {
        return functionaryService;
    }

    public void setFunctionaryService(FunctionaryService functionaryService) {
        this.functionaryService = functionaryService;
    }

    public SaleService getSaleService() {
        return saleService;
    }

    public void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
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

    public VoucherAmount getVoucherAmount1() {
        return voucherAmount1;
    }

    public void setVoucherAmount1(VoucherAmount voucherAmount1) {
        this.voucherAmount1 = voucherAmount1;
    }

    public VoucherAmount getVoucherAmount2() {
        return voucherAmount2;
    }

    public void setVoucherAmount2(VoucherAmount voucherAmount2) {
        this.voucherAmount2 = voucherAmount2;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Voucher getVoucher1() {
        return voucher1;
    }

    public void setVoucher1(Voucher voucher1) {
        this.voucher1 = voucher1;
    }

    public Voucher getVoucher2() {
        return voucher2;
    }

    public void setVoucher2(Voucher voucher2) {
        this.voucher2 = voucher2;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    
}
