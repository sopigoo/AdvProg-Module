package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class BankTransfer extends Payment {

    public BankTransfer(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
        validateBankTransfer(paymentData);
    }

    public BankTransfer(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
        validateBankTransfer(paymentData);
    }

    private void validateBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            setStatus(PaymentStatus.FAILED.getValue());
        } else {
            setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}