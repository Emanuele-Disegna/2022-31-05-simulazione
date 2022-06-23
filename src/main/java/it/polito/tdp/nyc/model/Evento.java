package it.polito.tdp.nyc.model;

public class Evento {
	private int tecnico;
	private Hotspot hotspot;
	private int tempo;
	private Event_type tipo;
	
	public enum Event_type {
		INIZIO,
		FINE,
		NUOVO_QUARTIERE
	}

	public Evento(int tecnico, Hotspot hotspot, int tempo, Event_type tipo) {
		super();
		this.tecnico = tecnico;
		this.hotspot = hotspot;
		this.tempo = tempo;
		this.tipo = tipo;
	}

	public int getTecnico() {
		return tecnico;
	}

	public void setTecnico(int tecnico) {
		this.tecnico = tecnico;
	}

	public Hotspot getHotspot() {
		return hotspot;
	}

	public void setHotspot(Hotspot hotspot) {
		this.hotspot = hotspot;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public Event_type getTipo() {
		return tipo;
	}

	public void setTipo(Event_type tipo) {
		this.tipo = tipo;
	}
	
	
	
}
