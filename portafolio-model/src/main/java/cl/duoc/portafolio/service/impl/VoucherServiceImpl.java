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
import java.util.Calendar;
import java.util.Date;
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
                voucher = voucherRepository.findOne(id);
            }
        } catch (Exception e) {
            voucher = null;
            LOGGER.error("Error al obtener vale: {}", e.toString());
            LOGGER.debug("Error al obtener vale: {}", e.toString());
        }
        return voucher;
    }

    @Override
    public List<Voucher> getVouchers(Date date) {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            if (date != null) {
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                start.setTime(date);
                end.setTime(date);

                start.setTime(date);
                start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE), 0, 0, 0);                
                end.setTime(date);
                end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE), 23, 59, 59);

                vouchers = voucherRepository.findByDateTimeBetween(start.getTime(), end.getTime());
            }
        } catch (Exception e) {
            vouchers = new ArrayList<>();
            LOGGER.error("Error al obtener vale: {}", e.toString());
            LOGGER.debug("Error al obtener vale: {}", e.toString());
        }
        return vouchers;
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
                voucherAmount = voucherAmountRepository.findOne(id);
            }
        } catch (Exception e) {
            voucherAmount = null;
            LOGGER.error("Error al obtener monto de vale: {}", e.toString());
            LOGGER.debug("Error al obtener monto de vale: {}", e.toString());
        }
        return voucherAmount;
    }

    @Override
    public List<VoucherAmount> getVoucherAmounts() {
        List<VoucherAmount> voucherAmounts = new ArrayList<>();
        try {
            voucherAmounts = voucherAmountRepository.findAll();
        } catch (Exception e) {
            voucherAmounts = new ArrayList<>();
            LOGGER.error("Error al obtener montos de vales: {}", e.toString());
            LOGGER.debug("Error al obtener montos de vales: {}", e.toString());
        }
        return voucherAmounts;
    }

    @Override
    public VoucherAmount getVoucherAmount(JobTitle jobTitle, MealService mealService) {
        VoucherAmount voucherAmount = null;
        try {
            if (jobTitle != null && mealService != null) {
                voucherAmount = voucherAmountRepository.findByJobTitleAndMealService(jobTitle, mealService);
            }
        } catch (Exception e) {
            voucherAmount = null;
            LOGGER.error("Error al obtener monto de vale: {}", e.toString());
            LOGGER.debug("Error al obtener monto de vale: {}", e.toString());
        }
        return voucherAmount;
    }

    @Override
    public List<VoucherAmount> getVoucherAmounts(MealService mealService) {
        List<VoucherAmount> voucherAmounts = new ArrayList<>();
        try {
            if (mealService != null) {
                voucherAmounts = voucherAmountRepository.findByMealService(mealService);
            }
        } catch (Exception e) {
            voucherAmounts = new ArrayList<>();
            LOGGER.error("Error al obtener montos de vales: {}", e.toString());
            LOGGER.debug("Error al obtener montos de vales: {}", e.toString());
        }
        return voucherAmounts;
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

    @Override
    public List<VoucherAmount> getVoucherAmounts(JobTitle jobTitle) {
        List<VoucherAmount> voucherAmounts = new ArrayList<>();
        try {
            if (jobTitle != null) {
                voucherAmounts = voucherAmountRepository.findByJobTitle(jobTitle);
            }
        } catch (Exception e) {
            voucherAmounts = new ArrayList<>();
            LOGGER.error("Error al obtener montos de vales: {}", e.toString());
            LOGGER.debug("Error al obtener montos de vales: {}", e.toString());
        }
        return voucherAmounts;
    }

}
