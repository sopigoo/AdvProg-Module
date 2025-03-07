package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        paymentData.put("voucherCode2", "ESHOP8765WXYZ4321");
    }

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = new Payment("550e8400-e29b-41d4-a716-446655440000", "VOUCHER", PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals("550e8400-e29b-41d4-a716-446655440000", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithMultiplePaymentData() {
        paymentData.put("discountCode", "DISCOUNT2025");

        Payment payment = new Payment("550e8400-e29b-41d4-a716-446655440000", "VOUCHER", PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals(3, payment.getPaymentData().size());
        assertEquals("DISCOUNT2025", payment.getPaymentData().get("discountCode"));
    }

    @Test
    void testSetStatusToFailed() {
        Payment payment = new Payment("550e8400-e29b-41d4-a716-446655440000", "VOUCHER", PaymentStatus.PENDING.getValue(), paymentData);
        payment.setStatus(PaymentStatus.FAILED.getValue());
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("550e8400-e29b-41d4-a716-446655440000", "VOUCHER", PaymentStatus.SUCCESS.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("550e8400-e29b-41d4-a716-446655440000", "VOUCHER", paymentData);
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }
}
