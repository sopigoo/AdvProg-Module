package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class BankTransferTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank AdvProg");
        paymentData.put("referenceCode", "REF123456789");
    }

    @Test
    void testCreateBankTransferPaymentSuccess() {
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedWithEmptyBankName() {
        paymentData.put("bankName", "");
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedWithNullBankName() {
        paymentData.put("bankName", null);
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedWithEmptyReferenceCode() {
        paymentData.put("referenceCode", "");
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedWithNullReferenceCode() {
        paymentData.put("referenceCode", null);
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToFailed() {
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", PaymentStatus.PENDING.getValue(), paymentData);
        payment.setStatus(PaymentStatus.FAILED.getValue());
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", PaymentStatus.SUCCESS.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testCreateBankTransferPaymentDefaultStatus() {
        BankTransfer payment = new BankTransfer("d27b5e74-4f33-4a56-b2b4-31f915a634e3", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }
}
