package it.polito.tdp.extflightdelays.model;

public class Rotte {
	private Airport a1;
	private Airport a2;
	private float distanzaMedia;
	/**
	 * @param a1
	 * @param a2
	 * @param distanzaMedia
	 */
	public Rotte(Airport a1, Airport a2, float distanzaMedia) {
		this.a1 = a1;
		this.a2 = a2;
		this.distanzaMedia = distanzaMedia;
	}
	public Airport getA1() {
		return a1;
	}
	public void setA1(Airport a1) {
		this.a1 = a1;
	}
	public Airport getA2() {
		return a2;
	}
	public void setA2(Airport a2) {
		this.a2 = a2;
	}
	public float getDistanzaMedia() {
		return distanzaMedia;
	}
	public void setDistanzaMedia(int distanzaMedia) {
		this.distanzaMedia = distanzaMedia;
	}
	@Override
	public String toString() {
		return a1.getAirportName() + ", " + a2.getAirportName() + " distanzaMedia=" + distanzaMedia;
	}
	
	
		

}
