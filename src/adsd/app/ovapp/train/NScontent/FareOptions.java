package adsd.app.ovapp.train.NScontent;


public class FareOptions {
    public ReasonEticketNotBuyable reasonEticketNotBuyable;

    private String isInternationalBookable;
    private String isInternational;
    private String isEticketBuyable;
    private String isPossibleWithOvChipkaart;

    public ReasonEticketNotBuyable getReasonEticketNotBuyable() {
        return reasonEticketNotBuyable;
    }

    public void setReasonEticketNotBuyable(ReasonEticketNotBuyable reasonEticketNotBuyable) {
        this.reasonEticketNotBuyable = reasonEticketNotBuyable;
    }

    public String getIsInternationalBookable() {
        return isInternationalBookable;
    }

    public void setIsInternationalBookable(String isInternationalBookable) {
        this.isInternationalBookable = isInternationalBookable;
    }

    public String getIsInternational() {
        return isInternational;
    }

    public void setIsInternational(String isInternational) {
        this.isInternational = isInternational;
    }

    public String getIsEticketBuyable() {
        return isEticketBuyable;
    }

    public void setIsEticketBuyable(String isEticketBuyable) {
        this.isEticketBuyable = isEticketBuyable;
    }

    public String getIsPossibleWithOvChipkaart() {
        return isPossibleWithOvChipkaart;
    }

    public void setIsPossibleWithOvChipkaart(String isPossibleWithOvChipkaart) {
        this.isPossibleWithOvChipkaart = isPossibleWithOvChipkaart;
    }
}


