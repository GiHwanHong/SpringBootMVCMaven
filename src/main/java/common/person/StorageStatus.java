package common.person;

public class StorageStatus {

	private long totalstorage;
	private long usagestorage;
	//private long idlestorage;
	//private int usagepercent;
	//private int idlepercent_hdd;
	
	public StorageStatus() {
		// TODO Auto-generated constructor stub
	}
	
	public StorageStatus(long usagestorage, long totalstorage) {
		super();
		this.usagestorage = usagestorage;
		this.totalstorage = totalstorage;
	}

	public long getTotalstorage() {
		return totalstorage;
	}

	public void setTotalstorage(long totalstorage) {
		this.totalstorage = totalstorage;
	}

	public long getUsagestorage() {
		return usagestorage;
	}

	public void setUsagestorage(long usagestorage) {
		this.usagestorage = usagestorage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "StroageStatus [usagestorage="+usagestorage+" totalstorage=" + totalstorage+"]";
	}
}