package adsd.app.ovapp.train;

public class TrainTime {
	
	//instance variables
		private String arrivalTime;
		private String departureTime;
		private String platForm;
		private String stationName;
		private String destination;
		private String route;
	
	public TrainTime() {
		
		this(null, null, null,null, null, null);
	}
	
	public TrainTime(String arrivaltime, String departuretime, String platform, String stationname, String destination, String route) { 
		
		this.arrivalTime = arrivaltime;
		this.departureTime = departuretime;
		this.platForm = platform;
		this.stationName = stationname;
		this.destination = destination;
		this.route = route;
		
	}

	public String getArrivalTime() {
		
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivaltime) {
		
		this.arrivalTime = arrivaltime;
	}
	
	public String getDepartureTime() {
		
		return departureTime;
	}
	
	public void setDepartureTime(String departuretime){
		
		this.departureTime = departuretime;
	}
	
	public String getPlatForm() {
		
		return platForm;
	}
	
	public void setPlatForm(String platform){
		
		this.platForm = platform;
	}
	
	public String getStationName() {
		
		return stationName;
	}
	
	public void setStationName(String stationname){
		
		this.stationName = stationname;
	}
	
	public String getDestination() {
		
		return destination;
	}
	
	public void setDestination(String destination){
		
		this.destination = destination;
	}
	
	public String getRoute() {
		
		return route;
	}
	
	public void setRoute(String route){
		
		this.route = route;
	}
}
