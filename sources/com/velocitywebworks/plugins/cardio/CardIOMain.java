package com.velocitywebworks.plugins.cardio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardIOMain extends Activity {
    private static int MY_SCAN_REQUEST_CODE = 10;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent scanIntent = new Intent(this, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, CardIOPlugin.expiry);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, CardIOPlugin.cvv);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, CardIOPlugin.zip);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, CardIOPlugin.confirm);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, CardIOPlugin.suppressManual);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, CardIOPlugin.showLogo);
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_SCAN_REQUEST_CODE && data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard scanResult = (CreditCard) data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
            JSONArray carddata = new JSONArray();
            JSONObject j = new JSONObject();
            try {
                j.put("card_number", scanResult.cardNumber);
                j.put("redacted_card_number", scanResult.getRedactedCardNumber());
                if (scanResult.isExpiryValid()) {
                    j.put("expiry_month", scanResult.expiryMonth);
                    j.put("expiry_year", scanResult.expiryYear);
                }
                if (scanResult.cvv != null) {
                    j.put("cvv", scanResult.cvv);
                }
                if (scanResult.postalCode != null) {
                    j.put("zip", scanResult.postalCode);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            carddata.put(j);
            CardIOPlugin.mCreditcardNumber = carddata;
        }
        finish();
    }
}
