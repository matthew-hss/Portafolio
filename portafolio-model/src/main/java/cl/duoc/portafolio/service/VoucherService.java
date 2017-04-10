package cl.duoc.portafolio.service;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Service;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.model.VoucherAmount;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface VoucherService {
    
    public Voucher getVoucher(final Long id);
    
    public List<Voucher> getVouchers();
    
    public List<Voucher> getVouchers(final Functionary functionary);
    
    public List<Voucher> getVouchers(final Sale sale);
    
    public Voucher save(final Voucher voucher);
    
    public boolean delete(final Voucher voucher);
    
    public VoucherAmount getVoucherAmount(final Long id);
    
    public List<VoucherAmount> getVouchersAmount();
    
    public List<VoucherAmount> getVouchersAmount(final Function function);
    
    public List<VoucherAmount> getVouchersAmount(final Service service);
    
    public VoucherAmount save(final VoucherAmount voucherAmount);
    
    public boolean delete(final VoucherAmount voucherAmount);
}