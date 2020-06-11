package adsd.app.ovapp.train.NScontent;

public class Trips {

    public Legs legs[];
    public Fares fares[];
    public ProductFare productFare;
    public FareOptions fareOptions;
    public ShareUrl shareUrl;

    private String uid;
    private String plannedDurationInMinutes;
    private String transfers;
    private String status;
    private String overviewPolyLine[];
    private String checksum;
    private String crowdForecast;
    private String ctxRecon;
    private String actualDurationInMinutes;
    private String idx;
    private String optimal;
    private String type;
    private String realtime;
    private String routeId;
    private String punctuality;

    public Legs[] getLegs() {
        return legs;
    }

    public void setLegs(Legs[] legs) {
        this.legs = legs;
    }

    public Fares[] getFares() {
        return fares;
    }

    public void setFares(Fares[] fares) {
        this.fares = fares;
    }

    public ProductFare getProductFare() {
        return productFare ;
    }

    public void setProductFare(ProductFare productFare) {
        this.productFare = productFare;
    }

    public FareOptions getFareOptions() {
        return fareOptions;
    }

    public void setFareOptions(FareOptions fareOptions) {
        this.fareOptions = fareOptions;
    }

    public ShareUrl getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(ShareUrl shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlannedDurationInMinutes() {
        return plannedDurationInMinutes;
    }

    public void setPlannedDurationInMinutes(String plannedDurationInMinutes) {
        this.plannedDurationInMinutes = plannedDurationInMinutes;
    }

    public String getTransfers() {
        return transfers;
    }

    public void setTransfers(String transfers) {
        this.transfers = transfers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getOverviewPolyLine() {
        return overviewPolyLine;
    }

    public void setOverviewPolyLine(String[] overviewPolyLine) {
        this.overviewPolyLine = overviewPolyLine;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getCrowdForecast() {
        return crowdForecast;
    }

    public void setCrowdForecast(String crowdForecast) {
        this.crowdForecast = crowdForecast;
    }

    public String getCtxRecon() {
        return ctxRecon;
    }

    public void setCtxRecon(String ctxRecon) {
        this.ctxRecon = ctxRecon;
    }

    public String getActualDurationInMinutes() {
        return actualDurationInMinutes;
    }

    public void setActualDurationInMinutes(String actualDurationInMinutes) {
        this.actualDurationInMinutes = actualDurationInMinutes;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getOptimal() {
        return optimal;
    }

    public void setOptimal(String optimal) {
        this.optimal = optimal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(String punctuality) {
        this.punctuality = punctuality;
    }
}
