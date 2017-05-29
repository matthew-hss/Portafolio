package cl.duoc.portafolio.application.jsf.functionary;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.VoucherAmount;
import cl.duoc.portafolio.application.utils.FacesUtils;
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
 * @author Javier González M.
 */
@Component
@Scope("view")
@Qualifier("voucherAmountFunctionaryBean")
public class VoucherAmountFunctionaryBean implements Serializable {

    private static final long serialVersionUID = 559864478748451255L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "saleService")
    private transient SaleService saleService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;

    private VoucherAmount voucherAmount = null;
    private List<VoucherAmount> voucherAmounts = null;
    private List<JobTitle> jobTitles = null;
    private List<MealService> mealServices = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherAmountFunctionaryBean.class);

    @PostConstruct
    public void init() {
        jobTitles = functionaryService.getJobTitles();
        mealServices = saleService.getMealServices();
        refresh();
    }

    public void refresh() {
        voucherAmount = new VoucherAmount();
        voucherAmounts = voucherService.getVoucherAmounts();
        edit = false;
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String process() {
        if (voucherAmount != null) {
            try {
                VoucherAmount save = voucherService.save(voucherAmount);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("voucherAmountSaved");
                } else {
                    FacesUtils.errorMessage("voucherAmountNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al agregar valor: {}", e.toString(), e);
                FacesUtils.fatalMessage("voucherAmountNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public String delete() {
        boolean ok = false;
        if (voucherAmount != null) {
            try {
                ok = voucherService.delete(voucherAmount);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("voucherAmountDeleted");
                } else {
                    FacesUtils.errorMessage("voucherAmountNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar valor: {}", e.toString(), e);
                FacesUtils.fatalMessage("voucherAmountNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public VoucherAmount getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(VoucherAmount voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public List<VoucherAmount> getVoucherAmounts() {
        return voucherAmounts;
    }

    public void setVoucherAmounts(List<VoucherAmount> voucherAmounts) {
        this.voucherAmounts = voucherAmounts;
    }

    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(List<JobTitle> jobTitles) {
        this.jobTitles = jobTitles;
    }

    public List<MealService> getMealServices() {
        return mealServices;
    }

    public void setMealServices(List<MealService> mealServices) {
        this.mealServices = mealServices;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
