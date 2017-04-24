package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.SaleService;
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
@Qualifier("mealServiceAdminBean")
public class MealServiceAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748451255L;

    @Resource(name = "saleService")
    private transient SaleService saleService;

    private MealService mealService = null;
    private List<MealService> mealServices = null;
    private List<Product> products = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(MealServiceAdminBean.class);

    @PostConstruct
    public void init() {
        products = saleService.getProducts();
        refresh();
    }

    public void refresh() {
        mealService = new MealService();
        edit = false;
        mealServices = saleService.getMealServices();
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String process() {
        if (mealService != null) {
            try {
                MealService save = saleService.save(mealService);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("mealServiceSaved");
                } else {
                    FacesUtils.errorMessage("mealServiceNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al agregar servicio de alimento: {}", e.toString(), e);
                FacesUtils.fatalMessage("mealServiceNotSaved");
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
        if (mealService != null) {
            try {
                ok = saleService.delete(mealService);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("mealServiceDeleted");
                } else {
                    FacesUtils.errorMessage("mealServiceNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar servicio de alimento: {}", e.toString(), e);
                FacesUtils.fatalMessage("mealServiceNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public SaleService getSaleService() {
        return saleService;
    }

    public void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }

    public MealService getMealService() {
        return mealService;
    }

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public List<MealService> getMealServices() {
        return mealServices;
    }

    public void setMealServices(List<MealService> mealServices) {
        this.mealServices = mealServices;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
