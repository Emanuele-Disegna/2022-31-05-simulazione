package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	//Parametri
	private int N;
	private String quartiereDiPartenza;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<Distanza> distanze;
	
	//Output
	private int durata;
	private List<Integer> controllati;
	
	//Coda
	private Queue<Evento> coda;
	
	//Stato del mondo
	private List<Distanza> giaVisitati; 
	
	
	public Simulatore(Graph<String, DefaultWeightedEdge> grafo, List<Distanza> distanze) {
		this.grafo=grafo;
		this.distanze=distanze;
	}
	
	public void init(int N, String quartiereDiPartenza) {
		this.N=N;
		this.quartiereDiPartenza=quartiereDiPartenza;
		
		giaVisitati = new ArrayList<>();
		controllati = new ArrayList<>();
		
		for(int i=0; i<N; i++)
			controllati.add(i, null);
		
		
	}
	
	
	public void simula() {
		while(!coda.isEmpty()) {
			processaEvento(coda.poll());
		}
	}

	private void processaEvento(Evento poll) {
		
		
		
	}
	
}
