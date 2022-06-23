package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Adiacenze {
	private String q1;
	private String q2;
	private LatLng latlng1;
	private LatLng latlng2;
	private double peso;
	
	public Adiacenze(String q1, String q2, LatLng latlng1, LatLng latlng2) {
		super();
		this.q1 = q1;
		this.q2 = q2;
		this.latlng1 = latlng1;
		this.latlng2 = latlng2;
		
		peso = LatLngTool.distance(latlng1, latlng2, LengthUnit.KILOMETER);
	}

	public String getQ1() {
		return q1;
	}

	public String getQ2() {
		return q2;
	}

	public LatLng getLatlng1() {
		return latlng1;
	}

	public LatLng getLatlng2() {
		return latlng2;
	}

	public double getPeso() {
		return peso;
	}
	
	
	
}
