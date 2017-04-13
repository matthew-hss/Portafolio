package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.VoucherAmount;
import cl.duoc.portafolio.repository.VoucherAmountRepository;
import cl.duoc.portafolio.repository.VoucherRepository;
import cl.duoc.portafolio.service.VoucherService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author matthew
 */
@Service("voucherService")
public class VoucherServiceImpl implements VoucherService, Serializable {

    private static final long serialVersionUID = 7419845182456767534L;

    @Resource(name = "voucherRepository")
    private VoucherRepository voucherRepository;
    @Resource(name = "voucherAmountRepository")
    private VoucherAmountRepository voucherAmountRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherServiceImpl.class);

    @Override
    public Voucher getVoucher(Long id) {
        Voucher voucher = null;
        try {
            if (id != null && id > 0) {
                voucher = voucherRepository.findById(id);
            }
        } catch (Exception e) {
            voucher = null;
            LOGGER.error("Error al obtener vale: {}", e.toString());
            LOGGER.debug("Error al obtener vale: {}", e.toString());
        }
        return voucher;
    }

    @Override
    public List<Voucher> getVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            vouchers = voucherRepository.findAll();
        } catch (Exception e) {
            vouchers = new ArrayList<>();
            LOGGER.error("Error al obtener vales: {}", e.toString());
            LOGGER.debug("Error al obtener vales: {}", e.toString());
        }
        return vouchers;
    }

    @Override
    public List<Voucher> getVouchers(Functionary functionary) {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            if (functionary != null) {
                vouchers = voucherRepository.findByFunctionary(functionary);
            }
        } catch (Exception e) {
            vouchers = new ArrayList<>();
            LOGGER.error("Error al obtener vales: {}", e.toString());
            LOGGER.debug("Error al obtener vales: {}", e.toString());
        }
        return vouchers;
    }

    @Override
    public List<Voucher> getVouchers(Sale sale) {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            if (sale != null) {
                vouchers = voucherRepository.findBySale(sale);
            }
        } catch (Exception e) {
            vouchers = new ArrayList<>();
            LOGGER.error("Error al obtener vales: {}", e.toString());
            LOGGER.debug("Error al obtener vales: {}", e.toString());
        }
        return vouchers;
    }

    @Override
    @Transactional
    public Voucher save(Voucher voucher) {
        Voucher saved = null;
        try {
            if (voucher != null) {
                saved = voucherRepository.save(voucher);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar vale: {}", e.toString());
            LOGGER.debug("Error al guardar vale: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(Voucher voucher) {
        boolean deleted = false;
        try {
            if (voucher != null) {
                voucherRepository.delete(voucher);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar vale: {}", e.toString());
            LOGGER.debug("Error al eliminar vale: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public VoucherAmount getVoucherAmount(Long id) {
        VoucherAmount voucherAmount = null;
        try {
            if (id != null && id > 0) {
                voucherAmount = voucherAmountRepository.findById(id);
            }
        } catch (Exception e) {
            voucherAmount = null;
            LOGGER.error("Error al obtener monto de vale: {}", e.toString());
            LOGGER.debug("Error al obtener monto de vale: {}", e.toString());
        }
        return voucherAmount;
    }

    @Override
    public List<VoucherAmount> getVouchersAmount() {
        List<VoucherAmount> vouchersAmounts = new ArrayList<>();
        try {
            vouchersAmounts = voucherAmountRepository.findAll();
        } catch (Exception e) {
            vouchersAmounts = new ArrayList<>();
            LOGGER.error("Error al obtener montos de vales: {}", e.toString());
            LOGGER.debug("Error al obtener montos de vales: {}", e.toString());
        }
        return vouchersAmounts;
    }

    @Override
    public List<VoucherAmount> getVouchersAmount(JobTitle function) {
        List<VoucherAmount> vouchersAmounts = new ArrayList<>();
        try {
            if (function != null) {
                vouchersAmounts = voucherAmountRepository.findByFunction(function);
            }
        } catch (Exception e) {
            vouchersAmounts = new ArrayList<>();
            LOGGER.error("Error al obtener montos de vales: {}", e.toString());
            LOGGER.debug("Error al obtener montos de vales: {}", e.toString());
        }
        return vouchersAmounts;
    }

    @Override
    public List<VoucherAmount> getVouchersAmount(MealService service) {
        List<VoucherAmount> vouchersAmounts = new ArrayList<>();
        try {
            if (service != null) {
                vouchersAmounts = voucherAmountRepository.findByService(service);
            }
        } catch (Exception e) {
            vouchersAmounts = new ArrayList<>();
            LOGGER.error("Error al obtener montos de vales: {}", e.toString());
            LOGGER.debug("Error al obtener montos de vales: {}", e.toString());
        }
        return vouchersAmounts;
    }

    @Override
    @Transactional
    public VoucherAmount save(VoucherAmount voucherAmount) {
        VoucherAmount saved = null;
        try {
            if (voucherAmount != null) {
                saved = voucherAmountRepository.save(voucherAmount);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar monto de vale: {}", e.toString());
            LOGGER.debug("Error al guardar monto de vale: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(VoucherAmount voucherAmount) {
        boolean deleted = false;
        try {
            if (voucherAmount != null) {
                voucherAmountRepository.delete(voucherAmount);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar monto de vale: {}", e.toString());
            LOGGER.debug("Error al eliminar monto de vale: {}", e.toString());
        }
        return deleted;
    }
}
