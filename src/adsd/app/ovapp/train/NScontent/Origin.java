package adsd.app.ovapp.train.NScontent;

public class Origin{
    public Notes notes[];
    private String name;
    private String lng;
    private String lat;
    private String countryCode;
    private String uicCode;
    private String type;
    private String prognosisType;
    private String plannedTimeZoneOffset;
    private String actualTimeZoneOffset;
    private String actualDateTime;
    private String plannedDateTime;
    private String plannedTrack;
    private String checkinStatus;

    public Notes[] getNotes() {
        return notes;
    }

    public void setNotes(Notes[] notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUicCode() {
        return uicCode;
    }

    public void setUicCode(String uicCode) {
        this.uicCode = uicCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrognosisType() {
        return prognosisType;
    }

    public void setPrognosisType(String prognosisType) {
        this.prognosisType = prognosisType;
    }

    public String getPlannedTimeZoneOffset() {
        return plannedTimeZoneOffset;
    }

    public void setPlannedTimeZoneOffset(String plannedTimeZoneOffset) {
        this.plannedTimeZoneOffset = plannedTimeZoneOffset;
    }

    public String getActualTimeZoneOffset() {
        return actualTimeZoneOffset;
    }

    public void setActualTimeZoneOffset(String actualTimeZoneOffset) {
        this.actualTimeZoneOffset = actualTimeZoneOffset;
    }

    public String getActualDateTime() {
        return actualDateTime;
    }

    public void setActualDateTime(String actualDateTime) {
        this.actualDateTime = actualDateTime;
    }

    public String getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(String plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public String getPlannedTrack() {
        return plannedTrack;
    }

    public void setPlannedTrack(String plannedTrack) {
        this.plannedTrack = plannedTrack;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
    }
}