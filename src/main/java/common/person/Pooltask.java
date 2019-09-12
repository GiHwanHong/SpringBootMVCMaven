package common.person;

public class Pooltask {
	
	private int poolsize;
	private int activecount;
	private int corepoolsize ;
	private int keepaliveseconds;
	private int maxpoolsize;
	
	public int getPoolsize() {
		return poolsize;
	}
	public void setPoolsize(int poolsize) {
		this.poolsize = poolsize;
	}
	public int getActivecount() {
		return activecount;
	}
	public void setActivecount(int activecount) {
		this.activecount = activecount;
	}
	public int getCorepoolsize() {
		return corepoolsize;
	}
	public void setCorepoolsize(int corepoolsize) {
		this.corepoolsize = corepoolsize;
	}
	public int getKeepaliveseconds() {
		return keepaliveseconds;
	}
	public void setKeepaliveseconds(int keepaliveseconds) {
		this.keepaliveseconds = keepaliveseconds;
	}
	public int getMaxpoolsize() {
		return maxpoolsize;
	}
	public void setMaxpoolsize(int maxpoolsize) {
		this.maxpoolsize = maxpoolsize;
	}
	
	
}
