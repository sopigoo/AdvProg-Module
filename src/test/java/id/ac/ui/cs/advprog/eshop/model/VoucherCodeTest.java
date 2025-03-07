package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VoucherCodeTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreateVoucherPaymentSuccess() {
        VoucherCode payment = new VoucherCode("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "VOUCHER", paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentRejected() {
        paymentData.put("voucherCode", "INVALIDVOUCHER");
        VoucherCode payment = new VoucherCode("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "VOUCHER", paymentData);
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToFailed() {
        VoucherCode payment = new VoucherCode("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "VOUCHER", PaymentStatus.PENDING.getValue(), paymentData);
        payment.setStatus(PaymentStatus.FAILED.getValue());
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        VoucherCode payment = new VoucherCode("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "VOUCHER", PaymentStatus.SUCCESS.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testCreateVoucherPaymentDefaultStatus() {
        VoucherCode payment = new VoucherCode("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "VOUCHER", paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }
}