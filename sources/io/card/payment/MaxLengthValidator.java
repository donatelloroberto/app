package io.card.payment;

class MaxLengthValidator extends NonEmptyValidator implements Validator {
    private int maxLength;

    MaxLengthValidator(int maxLength2) {
        this.maxLength = maxLength2;
    }

    public boolean isValid() {
        return super.isValid() && getValue().length() <= this.maxLength;
    }
}
