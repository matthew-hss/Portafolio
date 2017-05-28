package cl.duoc.portafolio.service;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.model.VoucherAmount;
import java.util.Date;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface VoucherService {
    
    public Voucher getVoucher(final Long id);
    
    public List<Voucher> getVouchers(final Date date);
    
    public List<Voucher> getVouchers();
    
    public List<Voucher> getVouchers(final Functionary functionary);
    
    public List<Voucher> getVouchers(final Sale sale);
    
    public Voucher save(final Voucher voucher);
    
    public boolean delete(final Voucher voucher);
    
    public VoucherAmount getVoucherAmount(final Long id);
    
    public VoucherAmount getVoucherAmount(final JobTitle jobTitle, final MealService mealService);
    
    public List<VoucherAmount> getVoucherAmounts();
    
    public List<VoucherAmount> getVoucherAmounts(final JobTitle jobTitle);
    
    public List<VoucherAmount> getVoucherAmounts(final MealService mealService);
    
    public VoucherAmount save(final VoucherAmount voucherAmount);
    
    public boolean delete(final VoucherAmount voucherAmount);
}