package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class VoucherCode extends Payment {

    public VoucherCode(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
        validateVoucher(paymentData);
    }

    public VoucherCode(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
        validateVoucher(paymentData);
    }

    private void validateVoucher(Map<String, String> paymentData) {
    }
}