package io.card.payment;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;
import kotlin.text.Typography;

public class CreditCard implements Parcelable {
    public static final Parcelable.Creator<CreditCard> CREATOR = new Parcelable.Creator<CreditCard>() {
        public CreditCard createFromParcel(Parcel source) {
            return new CreditCard(source);
        }

        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };
    public static final int EXPIRY_MAX_FUTURE_YEARS = 15;
    private static final String TAG = CreditCard.class.getSimpleName();
    public String cardNumber;
    public String cardholderName;
    public String cvv;
    public int expiryMonth;
    public int expiryYear;
    boolean flipped;
    public String postalCode;
    String scanId;
    int[] xoff;
    int yoff;

    public CreditCard() {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.xoff = new int[16];
        this.scanId = UUID.randomUUID().toString();
    }

    public CreditCard(String number, int month, int year, String code, String postalCode2, String cardholderName2) {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.cardNumber = number;
        this.expiryMonth = month;
        this.expiryYear = year;
        this.cvv = code;
        this.postalCode = postalCode2;
        this.cardholderName = cardholderName2;
    }

    private CreditCard(Parcel src) {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.cardNumber = src.readString();
        this.expiryMonth = src.readInt();
        this.expiryYear = src.readInt();
        this.cvv = src.readString();
        this.postalCode = src.readString();
        this.cardholderName = src.readString();
        this.scanId = src.readString();
        this.yoff = src.readInt();
        this.xoff = src.createIntArray();
    }

    public int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardNumber);
        dest.writeInt(this.expiryMonth);
        dest.writeInt(this.expiryYear);
        dest.writeString(this.cvv);
        dest.writeString(this.postalCode);
        dest.writeString(this.cardholderName);
        dest.writeString(this.scanId);
        dest.writeInt(this.yoff);
        dest.writeIntArray(this.xoff);
    }

    public String getLastFourDigitsOfCardNumber() {
        if (this.cardNumber == null) {
            return "";
        }
        return this.cardNumber.substring(this.cardNumber.length() - Math.min(4, this.cardNumber.length()));
    }

    public String getRedactedCardNumber() {
        if (this.cardNumber == null) {
            return "";
        }
        String redacted = "";
        if (this.cardNumber.length() > 4) {
            redacted = redacted + String.format("%" + (this.cardNumber.length() - 4) + "s", new Object[]{""}).replace(' ', Typography.bullet);
        }
        return CreditCardNumber.formatString(redacted + getLastFourDigitsOfCardNumber(), false, CardType.fromCardNumber(this.cardNumber));
    }

    public CardType getCardType() {
        return CardType.fromCardNumber(this.cardNumber);
    }

    public String getFormattedCardNumber() {
        return CreditCardNumber.formatString(this.cardNumber);
    }

    public boolean isExpiryValid() {
        return CreditCardNumber.isDateValid(this.expiryMonth, this.expiryYear);
    }

    public String toString() {
        String s = "{" + getCardType() + ": " + getRedactedCardNumber();
        if (this.expiryMonth > 0 || this.expiryYear > 0) {
            s = s + "  expiry:" + this.expiryMonth + "/" + this.expiryYear;
        }
        if (this.postalCode != null) {
            s = s + "  postalCode:" + this.postalCode;
        }
        if (this.cardholderName != null) {
            s = s + "  cardholderName:" + this.cardholderName;
        }
        if (this.cvv != null) {
            s = s + "  cvvLength:" + (this.cvv != null ? this.cvv.length() : 0);
        }
        return s + "}";
    }
}
