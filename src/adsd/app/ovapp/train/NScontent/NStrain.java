package adsd.app.ovapp.train.NScontent;

public class NStrain {
    public Trips trips[];
    public Errors errors[];

    private String message;

    private String scrollRequestBackwardContext;
    private String scrollRequestForwardContext;
    private int code;

    public Trips[] getTrips() {
        return trips;
    }

    public void setTrips(Trips[] trips) {
        this.trips = trips;
    }

    public Errors[] getErrors() {
        return errors;
    }

    public void setErrors(Errors[] errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
