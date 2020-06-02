package adsd.app.ovapp.train.NScontent;

public class ProductFare {
    private String priceInCents;
    private String product;
    private String travelClass;
    private String priceInCentsExcludingSupplement;
    private String discountType;

    public String getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(String priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public String getPriceInCentsExcludingSupplement() {
        return priceInCentsExcludingSupplement;
    }

    public void setPriceInCentsExcludingSupplement(String priceInCentsExcludingSupplement) {
        this.priceInCentsExcludingSupplement = priceInCentsExcludingSupplement;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}


