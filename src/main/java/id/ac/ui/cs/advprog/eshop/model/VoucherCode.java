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
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            setStatus(PaymentStatus.FAILED.getValue());
            return;
        }

        String suffix = voucherCode.substring(5); // Get characters from index 5 to 15
        int digitCount = 0;
        for (char c : suffix.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        if (digitCount == 8) {
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            setStatus(PaymentStatus.FAILED.getValue());
        }
    }
}