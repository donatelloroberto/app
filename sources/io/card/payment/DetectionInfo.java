package io.card.payment;

class DetectionInfo {
    public boolean bottomEdge;
    public boolean complete = false;
    public CreditCard detectedCard;
    public int expiry_month;
    public int expiry_year;
    public float focusScore;
    public boolean leftEdge;
    public int[] prediction = new int[16];
    public boolean rightEdge;
    public boolean topEdge;

    public DetectionInfo() {
        this.prediction[0] = -1;
        this.prediction[15] = -1;
        this.detectedCard = new CreditCard();
    }

    /* access modifiers changed from: package-private */
    public boolean sameEdgesAs(DetectionInfo other) {
        return other.topEdge == this.topEdge && other.bottomEdge == this.bottomEdge && other.leftEdge == this.leftEdge && other.rightEdge == this.rightEdge;
    }

    /* access modifiers changed from: package-private */
    public boolean detected() {
        return this.topEdge && this.bottomEdge && this.rightEdge && this.leftEdge;
    }

    /* access modifiers changed from: package-private */
    public boolean predicted() {
        return this.complete;
    }

    /* access modifiers changed from: package-private */
    public CreditCard creditCard() {
        String numberStr = new String();
        int i = 0;
        while (i < 16 && this.prediction[i] >= 0 && this.prediction[i] < 10) {
            numberStr = numberStr + String.valueOf(this.prediction[i]);
            i++;
        }
        this.detectedCard.cardNumber = numberStr;
        this.detectedCard.expiryMonth = this.expiry_month;
        this.detectedCard.expiryYear = this.expiry_year;
        return this.detectedCard;
    }

    /* access modifiers changed from: package-private */
    public int numVisibleEdges() {
        int i = 1;
        int i2 = (this.leftEdge ? 1 : 0) + (this.bottomEdge ? 1 : 0) + (this.topEdge ? 1 : 0);
        if (!this.rightEdge) {
            i = 0;
        }
        return i2 + i;
    }
}
