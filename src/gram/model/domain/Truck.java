package gram.model.domain;

import java.io.InputStream;

public class Truck {
	
	private int user_num;
	private int truck_num;
	private String truck_name;
	private String truck_info;
	private float truck_rate;
	private InputStream truck_pic; 

	
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
	public InputStream getTruck_pic() {
		return truck_pic;
	}
	public void setTruck_pic(InputStream truck_pic) {
		this.truck_pic = truck_pic;
	}
	
	public Truck(){
		super();
	}
	
	public Truck(int user_num, int truck_num, String truck_name, String truck_info, InputStream truck_pic,float truck_rate){
		super();
		this.user_num = user_num;
		this.truck_name = truck_name;
		this.truck_info = truck_info;
		this.truck_num = truck_num;
		this.truck_pic = truck_pic;
		this.truck_rate = truck_rate;
	}
}
