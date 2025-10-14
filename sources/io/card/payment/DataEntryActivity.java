package io.card.payment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DateKeyListener;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import io.card.payment.ui.ActivityHelper;
import io.card.payment.ui.Appearance;
import io.card.payment.ui.ViewUtil;

public final class DataEntryActivity extends Activity implements TextWatcher {
    private static final String TAG = DataEntryActivity.class.getSimpleName();
    private TextView activityTitleTextView;
    private boolean autoAcceptDone;
    private Button cancelBtn;
    private CreditCard capture;
    private ImageView cardView;
    private EditText cardholderNameEdit;
    private Validator cardholderNameValidator;
    private EditText cvvEdit;
    private Validator cvvValidator;
    private int defaultTextColor;
    private Button doneBtn;
    private int editTextIdCounter = 100;
    private EditText expiryEdit;
    private Validator expiryValidator;
    private String labelLeftPadding;
    private EditText numberEdit;
    private Validator numberValidator;
    private EditText postalCodeEdit;
    private Validator postalCodeValidator;
    private boolean useApplicationTheme;
    private int viewIdCounter = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() == null) {
            onBackPressed();
            return;
        }
        this.useApplicationTheme = getIntent().getBooleanExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, false);
        ActivityHelper.setActivityTheme(this, this.useApplicationTheme);
        this.defaultTextColor = new TextView(this).getTextColors().getDefaultColor();
        this.labelLeftPadding = ActivityHelper.holoSupported() ? "12dip" : "2dip";
        LocalizedStrings.setLanguage(getIntent());
        int paddingPx = ViewUtil.typedDimensionValueToPixelsInt("4dip", this);
        RelativeLayout container = new RelativeLayout(this);
        if (!this.useApplicationTheme) {
            container.setBackgroundColor(Appearance.DEFAULT_BACKGROUND_COLOR);
        }
        ScrollView scrollView = new ScrollView(this);
        int i = this.viewIdCounter;
        this.viewIdCounter = i + 1;
        scrollView.setId(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        container.addView(scrollView, layoutParams);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        scrollView.addView(linearLayout, -1, -1);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        this.capture = (CreditCard) getIntent().getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
        this.autoAcceptDone = getIntent().getBooleanExtra("debug_autoAcceptResult", false);
        if (this.capture != null) {
            this.numberValidator = new CardNumberValidator(this.capture.cardNumber);
            this.cardView = new ImageView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
            this.cardView.setPadding(0, 0, 0, paddingPx);
            cardParams.weight = 1.0f;
            this.cardView.setImageBitmap(CardIOActivity.markedCardImage);
            linearLayout2.addView(this.cardView, cardParams);
            ViewUtil.setMargins(this.cardView, (String) null, (String) null, (String) null, "8dip");
        } else {
            this.activityTitleTextView = new TextView(this);
            this.activityTitleTextView.setTextSize(24.0f);
            if (!this.useApplicationTheme) {
                this.activityTitleTextView.setTextColor(Appearance.PAY_BLUE_COLOR);
            }
            linearLayout2.addView(this.activityTitleTextView);
            ViewUtil.setPadding(this.activityTitleTextView, (String) null, (String) null, (String) null, "8dip");
            ViewUtil.setDimensions(this.activityTitleTextView, -2, -2);
            LinearLayout linearLayout3 = new LinearLayout(this);
            linearLayout3.setOrientation(1);
            ViewUtil.setPadding(linearLayout3, (String) null, "4dip", (String) null, "4dip");
            TextView textView = new TextView(this);
            ViewUtil.setPadding(textView, this.labelLeftPadding, (String) null, (String) null, (String) null);
            textView.setText(LocalizedStrings.getString(StringKey.ENTRY_CARD_NUMBER));
            if (!this.useApplicationTheme) {
                textView.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            linearLayout3.addView(textView, -2, -2);
            this.numberEdit = new EditText(this);
            EditText editText = this.numberEdit;
            int i2 = this.editTextIdCounter;
            this.editTextIdCounter = i2 + 1;
            editText.setId(i2);
            this.numberEdit.setMaxLines(1);
            this.numberEdit.setImeOptions(6);
            this.numberEdit.setTextAppearance(getApplicationContext(), 16842816);
            this.numberEdit.setInputType(3);
            this.numberEdit.setHint("1234 5678 1234 5678");
            if (!this.useApplicationTheme) {
                this.numberEdit.setHintTextColor(-3355444);
            }
            this.numberValidator = new CardNumberValidator();
            this.numberEdit.addTextChangedListener(this.numberValidator);
            this.numberEdit.addTextChangedListener(this);
            this.numberEdit.setFilters(new InputFilter[]{new DigitsKeyListener(), this.numberValidator});
            linearLayout3.addView(this.numberEdit, -1, -2);
            linearLayout2.addView(linearLayout3, -1, -1);
        }
        LinearLayout linearLayout4 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        ViewUtil.setPadding(linearLayout4, (String) null, "4dip", (String) null, "4dip");
        linearLayout4.setOrientation(0);
        boolean requireExpiry = getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false);
        boolean requireCVV = getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);
        boolean requirePostalCode = getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        if (requireExpiry) {
            LinearLayout expiryLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            expiryLayout.setOrientation(1);
            TextView expiryLabel = new TextView(this);
            if (!this.useApplicationTheme) {
                expiryLabel.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            expiryLabel.setText(LocalizedStrings.getString(StringKey.ENTRY_EXPIRES));
            ViewUtil.setPadding(expiryLabel, this.labelLeftPadding, (String) null, (String) null, (String) null);
            expiryLayout.addView(expiryLabel, -2, -2);
            this.expiryEdit = new EditText(this);
            EditText editText2 = this.expiryEdit;
            int i3 = this.editTextIdCounter;
            this.editTextIdCounter = i3 + 1;
            editText2.setId(i3);
            this.expiryEdit.setMaxLines(1);
            this.expiryEdit.setImeOptions(6);
            this.expiryEdit.setTextAppearance(getApplicationContext(), 16842816);
            this.expiryEdit.setInputType(3);
            this.expiryEdit.setHint(LocalizedStrings.getString(StringKey.EXPIRES_PLACEHOLDER));
            if (!this.useApplicationTheme) {
                this.expiryEdit.setHintTextColor(-3355444);
            }
            if (this.capture != null) {
                this.expiryValidator = new ExpiryValidator(this.capture.expiryMonth, this.capture.expiryYear);
            } else {
                this.expiryValidator = new ExpiryValidator();
            }
            if (this.expiryValidator.hasFullLength()) {
                this.expiryEdit.setText(this.expiryValidator.getValue());
            }
            this.expiryEdit.addTextChangedListener(this.expiryValidator);
            this.expiryEdit.addTextChangedListener(this);
            this.expiryEdit.setFilters(new InputFilter[]{new DateKeyListener(), this.expiryValidator});
            expiryLayout.addView(this.expiryEdit, -1, -2);
            linearLayout4.addView(expiryLayout, layoutParams4);
            ViewUtil.setMargins(expiryLayout, (String) null, (String) null, (requireCVV || requirePostalCode) ? "4dip" : null, (String) null);
        } else {
            this.expiryValidator = new AlwaysValid();
        }
        if (requireCVV) {
            LinearLayout cvvLayout = new LinearLayout(this);
            LinearLayout.LayoutParams cvvLayoutParam = new LinearLayout.LayoutParams(0, -1, 1.0f);
            cvvLayout.setOrientation(1);
            TextView cvvLabel = new TextView(this);
            if (!this.useApplicationTheme) {
                cvvLabel.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            ViewUtil.setPadding(cvvLabel, this.labelLeftPadding, (String) null, (String) null, (String) null);
            cvvLabel.setText(LocalizedStrings.getString(StringKey.ENTRY_CVV));
            cvvLayout.addView(cvvLabel, -2, -2);
            this.cvvEdit = new EditText(this);
            EditText editText3 = this.cvvEdit;
            int i4 = this.editTextIdCounter;
            this.editTextIdCounter = i4 + 1;
            editText3.setId(i4);
            this.cvvEdit.setMaxLines(1);
            this.cvvEdit.setImeOptions(6);
            this.cvvEdit.setTextAppearance(getApplicationContext(), 16842816);
            this.cvvEdit.setInputType(3);
            this.cvvEdit.setHint("123");
            if (!this.useApplicationTheme) {
                this.cvvEdit.setHintTextColor(-3355444);
            }
            int length = 4;
            if (this.capture != null) {
                length = CardType.fromCardNumber(this.numberValidator.getValue()).cvvLength();
            }
            this.cvvValidator = new FixedLengthValidator(length);
            this.cvvEdit.setFilters(new InputFilter[]{new DigitsKeyListener(), this.cvvValidator});
            this.cvvEdit.addTextChangedListener(this.cvvValidator);
            this.cvvEdit.addTextChangedListener(this);
            cvvLayout.addView(this.cvvEdit, -1, -2);
            linearLayout4.addView(cvvLayout, cvvLayoutParam);
            ViewUtil.setMargins(cvvLayout, requireExpiry ? "4dip" : null, (String) null, requirePostalCode ? "4dip" : null, (String) null);
        } else {
            this.cvvValidator = new AlwaysValid();
        }
        if (requirePostalCode) {
            LinearLayout linearLayout5 = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            linearLayout5.setOrientation(1);
            TextView textView2 = new TextView(this);
            if (!this.useApplicationTheme) {
                textView2.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            ViewUtil.setPadding(textView2, this.labelLeftPadding, (String) null, (String) null, (String) null);
            textView2.setText(LocalizedStrings.getString(StringKey.ENTRY_POSTAL_CODE));
            linearLayout5.addView(textView2, -2, -2);
            boolean postalCodeNumericOnly = getIntent().getBooleanExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, false);
            this.postalCodeEdit = new EditText(this);
            EditText editText4 = this.postalCodeEdit;
            int i5 = this.editTextIdCounter;
            this.editTextIdCounter = i5 + 1;
            editText4.setId(i5);
            this.postalCodeEdit.setMaxLines(1);
            this.postalCodeEdit.setImeOptions(6);
            this.postalCodeEdit.setTextAppearance(getApplicationContext(), 16842816);
            if (postalCodeNumericOnly) {
                this.postalCodeEdit.setInputType(3);
            } else {
                this.postalCodeEdit.setInputType(1);
            }
            if (!this.useApplicationTheme) {
                this.postalCodeEdit.setHintTextColor(-3355444);
            }
            this.postalCodeValidator = new MaxLengthValidator(20);
            this.postalCodeEdit.addTextChangedListener(this.postalCodeValidator);
            this.postalCodeEdit.addTextChangedListener(this);
            linearLayout5.addView(this.postalCodeEdit, -1, -2);
            linearLayout4.addView(linearLayout5, layoutParams5);
            ViewUtil.setMargins(linearLayout5, (requireExpiry || requireCVV) ? "4dip" : null, (String) null, (String) null, (String) null);
        } else {
            this.postalCodeValidator = new AlwaysValid();
        }
        linearLayout2.addView(linearLayout4, layoutParams3);
        addCardholderNameIfNeeded(linearLayout2);
        linearLayout.addView(linearLayout2, layoutParams2);
        ViewUtil.setMargins(linearLayout2, "16dip", "20dip", "16dip", "20dip");
        LinearLayout buttonLayout = new LinearLayout(this);
        int i6 = this.viewIdCounter;
        this.viewIdCounter = i6 + 1;
        buttonLayout.setId(i6);
        RelativeLayout.LayoutParams buttonLayoutParam = new RelativeLayout.LayoutParams(-1, -2);
        buttonLayoutParam.addRule(12);
        buttonLayout.setPadding(0, paddingPx, 0, 0);
        buttonLayout.setBackgroundColor(0);
        layoutParams.addRule(2, buttonLayout.getId());
        this.doneBtn = new Button(this);
        LinearLayout.LayoutParams doneParam = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        this.doneBtn.setText(LocalizedStrings.getString(StringKey.DONE));
        this.doneBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataEntryActivity.this.completed();
            }
        });
        this.doneBtn.setEnabled(false);
        buttonLayout.addView(this.doneBtn, doneParam);
        ViewUtil.styleAsButton(this.doneBtn, true, this, this.useApplicationTheme);
        ViewUtil.setPadding(this.doneBtn, "5dip", (String) null, "5dip", (String) null);
        ViewUtil.setMargins(this.doneBtn, "8dip", "8dip", "8dip", "8dip");
        if (!this.useApplicationTheme) {
            this.doneBtn.setTextSize(16.0f);
        }
        this.cancelBtn = new Button(this);
        LinearLayout.LayoutParams cancelParam = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        this.cancelBtn.setText(LocalizedStrings.getString(StringKey.CANCEL));
        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataEntryActivity.this.onBackPressed();
            }
        });
        buttonLayout.addView(this.cancelBtn, cancelParam);
        ViewUtil.styleAsButton(this.cancelBtn, false, this, this.useApplicationTheme);
        ViewUtil.setPadding(this.cancelBtn, "5dip", (String) null, "5dip", (String) null);
        ViewUtil.setMargins(this.cancelBtn, "4dip", "8dip", "8dip", "8dip");
        if (!this.useApplicationTheme) {
            this.cancelBtn.setTextSize(16.0f);
        }
        container.addView(buttonLayout, buttonLayoutParam);
        ActivityHelper.addActionBarIfSupported(this);
        setContentView(container);
        Drawable icon = null;
        if (getIntent().getBooleanExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, true)) {
            icon = getResources().getDrawable(R.drawable.cio_ic_paypal_monogram);
        }
        if (requireExpiry && this.expiryValidator.isValid()) {
            afterTextChanged(this.expiryEdit.getEditableText());
        }
        ActivityHelper.setupActionBarIfSupported(this, this.activityTitleTextView, LocalizedStrings.getString(StringKey.MANUAL_ENTRY_TITLE), "card.io - ", icon);
    }

    /* access modifiers changed from: private */
    public void completed() {
        if (this.capture == null) {
            this.capture = new CreditCard();
        }
        if (this.expiryEdit != null) {
            this.capture.expiryMonth = ((ExpiryValidator) this.expiryValidator).month;
            this.capture.expiryYear = ((ExpiryValidator) this.expiryValidator).year;
        }
        CreditCard result = new CreditCard(this.numberValidator.getValue(), this.capture.expiryMonth, this.capture.expiryYear, this.cvvValidator.getValue(), this.postalCodeValidator.getValue(), this.cardholderNameValidator.getValue());
        Intent dataIntent = new Intent();
        dataIntent.putExtra(CardIOActivity.EXTRA_SCAN_RESULT, result);
        if (getIntent().hasExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE)) {
            dataIntent.putExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE, getIntent().getByteArrayExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE));
        }
        setResult(CardIOActivity.RESULT_CARD_INFO, dataIntent);
        finish();
    }

    public void onBackPressed() {
        setResult(CardIOActivity.RESULT_ENTRY_CANCELED);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getWindow().setFlags(0, 1024);
        ActivityHelper.setFlagSecure(this);
        validateAndEnableDoneButtonIfValid();
        if (this.numberEdit != null || this.expiryEdit == null || this.expiryValidator.isValid()) {
            advanceToNextEmptyField();
        } else {
            this.expiryEdit.requestFocus();
        }
        if (this.numberEdit != null || this.expiryEdit != null || this.cvvEdit != null || this.postalCodeEdit != null || this.cardholderNameEdit != null) {
            getWindow().setSoftInputMode(5);
        }
    }

    private EditText advanceToNextEmptyField() {
        int viewId = 100;
        while (true) {
            int viewId2 = viewId + 1;
            EditText et = (EditText) findViewById(viewId);
            if (et == null) {
                return null;
            }
            if (et.getText().length() == 0 && et.requestFocus()) {
                return et;
            }
            viewId = viewId2;
        }
    }

    private void validateAndEnableDoneButtonIfValid() {
        this.doneBtn.setEnabled(this.numberValidator.isValid() && this.expiryValidator.isValid() && this.cvvValidator.isValid() && this.postalCodeValidator.isValid() && this.cardholderNameValidator.isValid());
        if (this.autoAcceptDone && this.numberValidator.isValid() && this.expiryValidator.isValid() && this.cvvValidator.isValid() && this.postalCodeValidator.isValid() && this.cardholderNameValidator.isValid()) {
            completed();
        }
    }

    public void afterTextChanged(Editable et) {
        String str;
        if (this.numberEdit != null && et == this.numberEdit.getText()) {
            if (!this.numberValidator.hasFullLength()) {
                setDefaultColor(this.numberEdit);
            } else if (!this.numberValidator.isValid()) {
                this.numberEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
            } else {
                setDefaultColor(this.numberEdit);
                advanceToNextEmptyField();
            }
            if (this.cvvEdit != null) {
                int length = CardType.fromCardNumber(this.numberValidator.getValue().toString()).cvvLength();
                ((FixedLengthValidator) this.cvvValidator).requiredLength = length;
                EditText editText = this.cvvEdit;
                if (length == 4) {
                    str = "1234";
                } else {
                    str = "123";
                }
                editText.setHint(str);
            }
        } else if (this.expiryEdit == null || et != this.expiryEdit.getText()) {
            if (this.cvvEdit == null || et != this.cvvEdit.getText()) {
                if (this.postalCodeEdit == null || et != this.postalCodeEdit.getText()) {
                    if (this.cardholderNameEdit != null && et == this.cardholderNameEdit.getText()) {
                        if (!this.cardholderNameValidator.hasFullLength()) {
                            setDefaultColor(this.cardholderNameEdit);
                        } else if (!this.cardholderNameValidator.isValid()) {
                            this.cardholderNameEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                        } else {
                            setDefaultColor(this.cardholderNameEdit);
                        }
                    }
                } else if (!this.postalCodeValidator.hasFullLength()) {
                    setDefaultColor(this.postalCodeEdit);
                } else if (!this.postalCodeValidator.isValid()) {
                    this.postalCodeEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                } else {
                    setDefaultColor(this.postalCodeEdit);
                }
            } else if (!this.cvvValidator.hasFullLength()) {
                setDefaultColor(this.cvvEdit);
            } else if (!this.cvvValidator.isValid()) {
                this.cvvEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
            } else {
                setDefaultColor(this.cvvEdit);
                advanceToNextEmptyField();
            }
        } else if (!this.expiryValidator.hasFullLength()) {
            setDefaultColor(this.expiryEdit);
        } else if (!this.expiryValidator.isValid()) {
            this.expiryEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
        } else {
            setDefaultColor(this.expiryEdit);
            advanceToNextEmptyField();
        }
        validateAndEnableDoneButtonIfValid();
    }

    private void setDefaultColor(EditText editText) {
        if (this.useApplicationTheme) {
            editText.setTextColor(this.defaultTextColor);
        } else {
            editText.setTextColor(-12303292);
        }
    }

    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }

    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }

    private void addCardholderNameIfNeeded(ViewGroup mainLayout) {
        if (getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false)) {
            LinearLayout cardholderNameLayout = new LinearLayout(this);
            ViewUtil.setPadding(cardholderNameLayout, (String) null, "4dip", (String) null, (String) null);
            cardholderNameLayout.setOrientation(1);
            TextView cardholderNameLabel = new TextView(this);
            if (!this.useApplicationTheme) {
                cardholderNameLabel.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            ViewUtil.setPadding(cardholderNameLabel, this.labelLeftPadding, (String) null, (String) null, (String) null);
            cardholderNameLabel.setText(LocalizedStrings.getString(StringKey.ENTRY_CARDHOLDER_NAME));
            cardholderNameLayout.addView(cardholderNameLabel, -2, -2);
            this.cardholderNameEdit = new EditText(this);
            EditText editText = this.cardholderNameEdit;
            int i = this.editTextIdCounter;
            this.editTextIdCounter = i + 1;
            editText.setId(i);
            this.cardholderNameEdit.setMaxLines(1);
            this.cardholderNameEdit.setImeOptions(6);
            this.cardholderNameEdit.setTextAppearance(getApplicationContext(), 16842816);
            this.cardholderNameEdit.setInputType(1);
            if (!this.useApplicationTheme) {
                this.cardholderNameEdit.setHintTextColor(-3355444);
            }
            this.cardholderNameValidator = new MaxLengthValidator(175);
            this.cardholderNameEdit.addTextChangedListener(this.cardholderNameValidator);
            this.cardholderNameEdit.addTextChangedListener(this);
            cardholderNameLayout.addView(this.cardholderNameEdit, -1, -2);
            mainLayout.addView(cardholderNameLayout, -1, -2);
            return;
        }
        this.cardholderNameValidator = new AlwaysValid();
    }
}
