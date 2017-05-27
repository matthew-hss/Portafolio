/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.application.jsf.functionary;

import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.service.SaleService;
import cl.duoc.portafolio.service.VoucherService;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matthew
 */
@Component
@Scope("view")
@Qualifier("voucherBean")
public class VoucherBean implements Serializable {

    private static final long serialVersionUID = 559864478748451145L;

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "saleService")
    private transient SaleService saleService;
    
    
    
}
