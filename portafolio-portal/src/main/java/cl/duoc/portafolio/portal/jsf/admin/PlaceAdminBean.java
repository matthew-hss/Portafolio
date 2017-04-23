/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Place;
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
 * @author Javier González M.
 */
@Component
@Scope("view")
@Qualifier("placeAdminBean")
public class PlaceAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748547255L;

    @Resource(name = "saleService")
    private transient SaleService saleService;

    private Place place = null;
    private List<Place> places = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceAdminBean.class);

    @PostConstruct
    public void init() {
        refresh();
    }

    public void refresh() {
        edit = false;
        place = new Place();
        places = saleService.getPlaces();
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String delete() {
        if (place != null) {
            try {
                boolean ok = saleService.delete(place);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("placeDeleted");
                } else {
                    FacesUtils.errorMessage("placeNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar lugar: {}", e.toString(), e);
                FacesUtils.fatalMessage("placeNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public String process() {
        if (place != null) {
            try {
                Place save = saleService.save(place);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("placeSaved");
                } else {
                    FacesUtils.infoMessage("placeNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al guardar lugar: {}", e.toString(), e);
                FacesUtils.fatalMessage("placeNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
