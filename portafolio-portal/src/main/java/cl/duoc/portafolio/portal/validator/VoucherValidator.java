/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.validator;

import cl.duoc.portafolio.model.SpecialVoucher;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.VoucherService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matthew
 */
@Component
@Scope("view")
public class VoucherValidator implements Validator {

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;

    @Override
    public void validate(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String voucherCode = (String) value;
        if (!StringUtils.isBlank(voucherCode)) {
            if (!voucherExist(voucherCode)) {
                String msgError = FacesUtils.getMessage("voucherCodeValidatorError");
                FacesMessage msg = new FacesMessage(msgError);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            } else if (voucherUsed(voucherCode)) {
                String msgError = FacesUtils.getMessage("voucherCodeUsedError");
                FacesMessage msg = new FacesMessage(msgError);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            } else {
                try {
                    if (voucherOutOfDate(voucherCode)) {
                        String msgError = FacesUtils.getMessage("voucherCodeOutOfDateError");
                        FacesMessage msg = new FacesMessage(msgError);
                        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                        throw new ValidatorException(msg);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(VoucherValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private boolean voucherExist(String voucherCode) {
        String voucherType = voucherCode.substring(0, Math.min(voucherCode.length(), 3));
        boolean flag = false;
        if (voucherType.equals("111")) {
            Voucher voucher = voucherService.getVoucher(voucherCode);
            if (voucher != null) {
                flag = true;
            }
        } else {
            SpecialVoucher specialVoucher = voucherService.getSpecialVoucher(voucherCode);
            if (specialVoucher != null) {
                flag = true;
            }
        }
        return flag;
    }

    private boolean voucherOutOfDate(String voucherCode) throws ParseException {
        String voucherType = voucherCode.substring(0, Math.min(voucherCode.length(), 3));
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date todayWithZeroTime = formatter.parse(formatter.format(new Date()));
        Date voucherDateWithZeroTime = new Date();

        if (voucherType.equals("111")) {
            Voucher voucher = (Voucher) voucherService.getVoucher(voucherCode);
            voucherDateWithZeroTime = formatter.parse(formatter.format(voucher.getDateTime()));

        } else if (voucherType.equals("777")) {
            SpecialVoucher specialVoucher = (SpecialVoucher) voucherService.getSpecialVoucher(voucherCode);
            voucherDateWithZeroTime = formatter.parse(formatter.format(specialVoucher.getDateTime()));
        }

        if (!voucherDateWithZeroTime.equals(todayWithZeroTime)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean voucherUsed(String voucherCode) {
        String voucherType = voucherCode.substring(0, Math.min(voucherCode.length(), 3));
        boolean flag = false;
        if (voucherType.equals("111")) {
            Voucher voucher = (Voucher) voucherService.getVoucher(voucherCode);
            flag = voucher.isUsed();
        } else if (voucherType.equals("777")) {
            SpecialVoucher specialVoucher = (SpecialVoucher) voucherService.getSpecialVoucher(voucherCode);
            flag = specialVoucher.isUsed();
        }

        return flag;
    }
}
