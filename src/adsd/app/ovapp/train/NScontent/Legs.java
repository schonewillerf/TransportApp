package adsd.app.ovapp.train.NScontent;



public class Legs {

    public Origin origin;
    public Destination destination;
    public Product product;
    public Notes Notes[];
    public Messages messages[];
    public Stops stops[];
    public Steps steps[];
    public JourneyDetail journeyDetail[];

    private String idx;
    public String name;
    private String travelType;
    private String direction;
    private String cancelled;
    private String changePossible;
    private String alternativeTransport;
    private String journeyDetailRef;
    private String shorterStock;
    private String reachable;
    private String punctuality;

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public adsd.app.ovapp.train.NScontent.Notes[] getNotes() {
        return Notes;
    }

    public void setNotes(adsd.app.ovapp.train.NScontent.Notes[] notes) {
        Notes = notes;
    }

    public Messages[] getMessages() {
        return messages;
    }

    public void setMessages(Messages[] messages) {
        this.messages = messages;
    }

    public Stops[] getStops() {
        return stops;
    }

    public void setStops(Stops[] stops) {
        this.stops = stops;
    }

    public Steps[] getSteps() {
        return steps;
    }

    public void setSteps(Steps[] steps) {
        this.steps = steps;
    }

    public JourneyDetail[] getJourneyDetail() {
        return journeyDetail;
    }

    public void setJourneyDetail(JourneyDetail[] journeyDetail) {
        this.journeyDetail = journeyDetail;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCancelled() {
        return cancelled;
    }

    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }

    public String getChangePossible() {
        return changePossible;
    }

    public void setChangePossible(String changePossible) {
        this.changePossible = changePossible;
    }

    public String getAlternativeTransport() {
        return alternativeTransport;
    }

    public void setAlternativeTransport(String alternativeTransport) {
        this.alternativeTransport = alternativeTransport;
    }

    public String getJourneyDetailRef() {
        return journeyDetailRef;
    }

    public void setJourneyDetailRef(String journeyDetailRef) {
        this.journeyDetailRef = journeyDetailRef;
    }

    public String getShorterStock() {
        return shorterStock;
    }

    public void setShorterStock(String shorterStock) {
        this.shorterStock = shorterStock;
    }

    public String getReachable() {
        return reachable;
    }

    public void setReachable(String reachable) {
        this.reachable = reachable;
    }

    public String getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(String punctuality) {
        this.punctuality = punctuality;
    }
}