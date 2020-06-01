package adsd.app.ovapp.train.NScontent;

public class NStrain {
    public Trips trips[];
    private String scrollRequestBackwardContext;
    private String scrollRequestForwardContext;

    public Trips[] getTrips() {
        return trips;
    }

    public void setTrips(Trips[] trips) {
        this.trips = trips;
    }

    public String getScrollRequestBackwardContext() {
        return scrollRequestBackwardContext;
    }

    public void setScrollRequestBackwardContext(String scrollRequestBackwardContext) {
        this.scrollRequestBackwardContext = scrollRequestBackwardContext;
    }

    public String getScrollRequestForwardContext() {
        return scrollRequestForwardContext;
    }

    public void setScrollRequestForwardContext(String scrollRequestForwardContext) {
        this.scrollRequestForwardContext = scrollRequestForwardContext;
    }
}
