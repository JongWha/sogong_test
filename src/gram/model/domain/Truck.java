package gram.model.domain;

public class Truck {
	
	private int user_num;
	private int truck_num;
	private String truck_name;
	private String truck_info;
	private float truck_rate;
	private String truck_pic_name; 
	private String truck_long;
	private String truck_lati;


	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getTruck_num() {
		return truck_num;
	}
	public void setTruck_num(int truck_num) {
		this.truck_num = truck_num;
	}
	public String getTruck_name() {
		return truck_name;
	}
	public void setTruck_name(String truck_name) {
		this.truck_name = truck_name;
	}
	public String getTruck_info() {
		return truck_info;
	}
	public void setTruck_info(String truck_info) {
		this.truck_info = truck_info;
	}
	public float getTruck_rate() {
		return truck_rate;
	}
	public void setTruck_rate(float truck_rate) {
		this.truck_rate = truck_rate;
	}
	public String getTruck_pic_name() {
		return truck_pic_name;
	}
	public void setTruck_pic_name(String truck_pic_name) {
		this.truck_pic_name = truck_pic_name;
	}
	
	public String getTruck_long() {
		return truck_long;
	}
	public void setTruck_long(String truck_long) {
		this.truck_long = truck_long;
	}
	public String getTruck_lati() {
		return truck_lati;
	}
	public void setTruck_lati(String truck_lati) {
		this.truck_lati = truck_lati;
	}
	

	
	public Truck(){
		super();
	}
	
	public Truck(int user_num, int truck_num, String truck_name, String truck_info, String truck_pic_name,float truck_rate, String truck_long, String truck_lati){
		super();
		this.user_num = user_num;
		this.truck_name = truck_name;
		this.truck_info = truck_info;
		this.truck_num = truck_num;
		this.truck_pic_name = truck_pic_name;
		this.truck_rate = truck_rate;
		this.truck_lati = truck_lati;
		this.truck_long = truck_long;
	}
	
	
}
