package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABCD5678");
        paymentData1.put("voucherCode2", "ESHOP8765WXYZ4321");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP1234ABCD5678");
        paymentData2.put("voucherCode2", "ESHOP8765WXYZ4321");

        Map<String, String> paymentData3 = new HashMap<>();
        paymentData3.put("voucherCode", "ESHOP1234ABCD5678");
        paymentData3.put("voucherCode2", "ESHOP8765WXYZ4321");

        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher", paymentData1);
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078", "bank transfer", paymentData2);
        Payment payment3 = new Payment("e334ef40-9eff-4da8-9487-8ee697ecbf1e", "voucher", paymentData3);

        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("voucherCode", "ESHOP1234ABCD5678");
        newPaymentData.put("voucherCode2", "ESHOP8765WXYZ4322");

        Payment updatedPayment = new Payment(payment.getId(), payment.getMethod(), "SUCCESS", newPaymentData);
        Payment result = paymentRepository.save(updatedPayment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals("SUCCESS", findResult.getStatus());
        assertEquals(newPaymentData, findResult.getPaymentData());
        assertEquals(payment.getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(payments.size(), allPayments.size());
        assertTrue(allPayments.containsAll(payments));
    }
}
