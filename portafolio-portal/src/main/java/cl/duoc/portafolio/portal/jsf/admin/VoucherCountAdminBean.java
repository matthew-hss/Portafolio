package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.VoucherAmount;
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
 * @author Javier González M.
 */
@Component
@Scope("view")
@Qualifier("voucherAmountAdminBean")
public class VoucherCountAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478547451255L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "saleService")
    private transient SaleService saleService;

    private List<MealService> mealServices = null;
    private MealService selectedMealService = null;
    private Integer count;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherCountAdminBean.class);

    @PostConstruct
    public void init() {
        mealServices = saleService.getMealServices();
        refresh();
    }

    public void refresh() {
        count = 0;
        selectedMealService = null;
    }

    public String process() {
        try {
            count = voucherService.getVoucherCount(selectedMealService);
        } catch (Exception e) {
            LOGGER.debug("Error al contar registros: {}", e.toString(), e);
            FacesUtils.fatalMessage("voucherCountNotFound");
        }

        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public List<MealService> getMealServices() {
        return mealServices;
    }

    public void setMealServices(List<MealService> mealServices) {
        this.mealServices = mealServices;
    }

    public MealService getSelectedMealService() {
        return selectedMealService;
    }

    public void setSelectedMealService(MealService selectedMealService) {
        this.selectedMealService = selectedMealService;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
