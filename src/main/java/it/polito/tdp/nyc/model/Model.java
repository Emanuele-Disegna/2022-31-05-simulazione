package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	private Graph<String, DefaultWeightedEdge> grafo;
	private NYCDao dao;
	private List<Adiacenze> adiacenze;
	
	public Model() {
		dao = new NYCDao();
	}
	
	public List<String> getProviders(){
		return dao.getProviders();
	}
	
	public void creaGrafo(String provider) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, dao.getNodi(provider));
		
		adiacenze = new ArrayList<>(dao.getAdiacenze(provider));
		for(Adiacenze a : adiacenze) {
			Graphs.addEdge(grafo, a.getQ1(), a.getQ2(), a.getPeso());
		}
		
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
	}
	
	public Set<String> getQuartieri() {
		return grafo.vertexSet();
	}
	
	public List<Distanza> getDistanze(String quartiere) {
		List<Distanza> distanze = new ArrayList<Distanza>();
		
		for(String s : Graphs.neighborListOf(grafo, quartiere)) {
			distanze.add(new Distanza(s, grafo.getEdgeWeight(grafo.getEdge(quartiere, s))));
		}
		
		Collections.sort(distanze, Comparator.comparing(Distanza::getDis));
		
		return distanze;
	}
	
}
