package io.card.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Pair;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public enum CardType {
    AMEX("AmEx"),
    DINERSCLUB("DinersClub"),
    DISCOVER("Discover"),
    JCB("JCB"),
    MASTERCARD("MasterCard"),
    VISA("Visa"),
    MAESTRO("Maestro"),
    UNKNOWN("Unknown"),
    INSUFFICIENT_DIGITS("More digits required");
    
    private static HashMap<Pair<String, String>, CardType> intervalLookup;
    private static int minDigits;
    public final String name;

    static {
        minDigits = 1;
        intervalLookup = new HashMap<>();
        intervalLookup.put(getNewPair("2221", "2720"), MASTERCARD);
        intervalLookup.put(getNewPair("300", "305"), DINERSCLUB);
        intervalLookup.put(getNewPair("309", (String) null), DINERSCLUB);
        intervalLookup.put(getNewPair("34", (String) null), AMEX);
        intervalLookup.put(getNewPair("3528", "3589"), JCB);
        intervalLookup.put(getNewPair("36", (String) null), DINERSCLUB);
        intervalLookup.put(getNewPair("37", (String) null), AMEX);
        intervalLookup.put(getNewPair("38", "39"), DINERSCLUB);
        intervalLookup.put(getNewPair("4", (String) null), VISA);
        intervalLookup.put(getNewPair("50", (String) null), MAESTRO);
        intervalLookup.put(getNewPair("51", "55"), MASTERCARD);
        intervalLookup.put(getNewPair("56", "59"), MAESTRO);
        intervalLookup.put(getNewPair("6011", (String) null), DISCOVER);
        intervalLookup.put(getNewPair("61", (String) null), MAESTRO);
        intervalLookup.put(getNewPair("62", (String) null), DISCOVER);
        intervalLookup.put(getNewPair("63", (String) null), MAESTRO);
        intervalLookup.put(getNewPair("644", "649"), DISCOVER);
        intervalLookup.put(getNewPair("65", (String) null), DISCOVER);
        intervalLookup.put(getNewPair("66", "69"), MAESTRO);
        intervalLookup.put(getNewPair("88", (String) null), DISCOVER);
        for (Map.Entry<Pair<String, String>, CardType> entry : getIntervalLookup().entrySet()) {
            minDigits = Math.max(minDigits, ((String) entry.getKey().first).length());
            if (entry.getKey().second != null) {
                minDigits = Math.max(minDigits, ((String) entry.getKey().second).length());
            }
        }
    }

    private CardType(String name2) {
        this.name = name2;
    }

    public String toString() {
        return this.name;
    }

    public String getDisplayName(String languageOrLocale) {
        switch (this) {
            case AMEX:
                return LocalizedStrings.getString(StringKey.CARDTYPE_AMERICANEXPRESS, languageOrLocale);
            case DINERSCLUB:
            case DISCOVER:
                return LocalizedStrings.getString(StringKey.CARDTYPE_DISCOVER, languageOrLocale);
            case JCB:
                return LocalizedStrings.getString(StringKey.CARDTYPE_JCB, languageOrLocale);
            case MASTERCARD:
                return LocalizedStrings.getString(StringKey.CARDTYPE_MASTERCARD, languageOrLocale);
            case MAESTRO:
                return LocalizedStrings.getString(StringKey.CARDTYPE_MAESTRO, languageOrLocale);
            case VISA:
                return LocalizedStrings.getString(StringKey.CARDTYPE_VISA, languageOrLocale);
            default:
                return null;
        }
    }

    public int numberLength() {
        switch (this) {
            case AMEX:
                return 15;
            case DINERSCLUB:
                return 14;
            case DISCOVER:
            case JCB:
            case MASTERCARD:
            case MAESTRO:
            case VISA:
                return 16;
            case INSUFFICIENT_DIGITS:
                return minDigits;
            default:
                return -1;
        }
    }

    public int cvvLength() {
        switch (this) {
            case AMEX:
                return 4;
            case DINERSCLUB:
            case DISCOVER:
            case JCB:
            case MASTERCARD:
            case MAESTRO:
            case VISA:
                return 3;
            default:
                return -1;
        }
    }

    public Bitmap imageBitmap(Context context) {
        int cardImageResource = -1;
        switch (this) {
            case AMEX:
                cardImageResource = R.drawable.cio_ic_amex;
                break;
            case DINERSCLUB:
            case DISCOVER:
                cardImageResource = R.drawable.cio_ic_discover;
                break;
            case JCB:
                cardImageResource = R.drawable.cio_ic_jcb;
                break;
            case MASTERCARD:
                cardImageResource = R.drawable.cio_ic_mastercard;
                break;
            case VISA:
                cardImageResource = R.drawable.cio_ic_visa;
                break;
        }
        if (cardImageResource != -1) {
            return BitmapFactory.decodeResource(context.getResources(), cardImageResource);
        }
        return null;
    }

    private static boolean isNumberInInterval(String number, String intervalStart, String intervalEnd) {
        int numCompareStart = Math.min(number.length(), intervalStart.length());
        int numCompareEnd = Math.min(number.length(), intervalEnd.length());
        if (Integer.parseInt(number.substring(0, numCompareStart)) >= Integer.parseInt(intervalStart.substring(0, numCompareStart)) && Integer.parseInt(number.substring(0, numCompareEnd)) <= Integer.parseInt(intervalEnd.substring(0, numCompareEnd))) {
            return true;
        }
        return false;
    }

    private static HashMap<Pair<String, String>, CardType> getIntervalLookup() {
        return intervalLookup;
    }

    private static Pair<String, String> getNewPair(String intervalStart, String intervalEnd) {
        if (intervalEnd == null) {
            intervalEnd = intervalStart;
        }
        return new Pair<>(intervalStart, intervalEnd);
    }

    public static CardType fromString(String typeStr) {
        if (typeStr == null) {
            return UNKNOWN;
        }
        for (CardType type : values()) {
            if (type != UNKNOWN && type != INSUFFICIENT_DIGITS && typeStr.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static CardType fromCardNumber(String numStr) {
        if (TextUtils.isEmpty(numStr)) {
            return UNKNOWN;
        }
        HashSet<CardType> possibleCardTypes = new HashSet<>();
        for (Map.Entry<Pair<String, String>, CardType> entry : getIntervalLookup().entrySet()) {
            if (isNumberInInterval(numStr, (String) entry.getKey().first, (String) entry.getKey().second)) {
                possibleCardTypes.add(entry.getValue());
            }
        }
        if (possibleCardTypes.size() > 1) {
            return INSUFFICIENT_DIGITS;
        }
        if (possibleCardTypes.size() == 1) {
            return possibleCardTypes.iterator().next();
        }
        return UNKNOWN;
    }
}
