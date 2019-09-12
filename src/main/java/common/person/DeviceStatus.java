package common.person;

import java.util.List;

public class DeviceStatus {

	private double cpuusage;
	private int cpucore;
	
	private double totalmemory;
	private double freememory;
	
	private List<StorageStatus> storagestatus;
	
	public DeviceStatus() {
		// TODO Auto-generated constructor stub
	}
	
	public DeviceStatus(int cpucore, double cpuusage, long totalmemory, long freememory, List<StorageStatus> storagestatus) {
		super();
		this.cpucore = cpucore;
		this.cpuusage = cpuusage;
		this.totalmemory = totalmemory;
		this.freememory = freememory;
		this.storagestatus = storagestatus;
	}
	
	public int getCpucore() {
		return cpucore;
	}

	public void setCpucore(int cpucore) {
		this.cpucore = cpucore;
	}

	public double getCpuusage() {
		return cpuusage;
	}

	public void setCpuusage(double cpuusage) {
		this.cpuusage = cpuusage;
	}

	public double getTotalmemory() {
		return totalmemory;
	}

	public void setTotalmemory(double totalmemory) {
		this.totalmemory = totalmemory;
	}

	public double getFreememory() {
		return freememory;
	}

	public void setFreememory(double freememory) {
		this.freememory = freememory;
	}

	public List<StorageStatus> getStoragestatus() {
		return storagestatus;
	}

	public void setStoragestatus(List<StorageStatus> storagestatus) {
		this.storagestatus = storagestatus;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DeviceStatus [cpuCore="+ cpucore+", cpuusage="+ cpuusage + ", freememory="+freememory
					+ ", totalmemory="+ totalmemory +" storagestatus="+storagestatus+"]";
	}
}
