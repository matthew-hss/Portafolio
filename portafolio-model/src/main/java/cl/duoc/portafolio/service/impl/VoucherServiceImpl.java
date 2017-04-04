/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.model.Service;
import cl.duoc.portafolio.model.VoucherAmount;
import cl.duoc.portafolio.service.VoucherService;
import java.util.List;

/**
 *
 * @author matthew
 */
@org.springframework.stereotype.Service("functionaryService")
public class VoucherServiceImpl implements VoucherService{

    @Override
    public Voucher getVoucher(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Voucher> getVouchers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Voucher> getVouchers(Functionary functionary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Voucher> getVouchers(Sale sale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voucher save(Voucher voucher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Voucher voucher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VoucherAmount getVoucherAmount(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VoucherAmount> getVouchersAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VoucherAmount> getVouchersAmount(Function function) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VoucherAmount> getVouchersAmount(Service service) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VoucherAmount save(VoucherAmount voucherAmount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(VoucherAmount voucherAmount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
