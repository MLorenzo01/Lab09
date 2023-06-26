package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private BordersDAO dao;
	private Graph<Country, DefaultWeightedEdge> graph;
	private Map<Integer, Country > nodes;
	private Map<String, Integer> nomi;

	public Model() {
		dao = new BordersDAO();
		graph = new SimpleGraph<>(DefaultWeightedEdge.class);
		nodes = new HashMap<>();
		nomi = new HashMap<>();
	}
	public void getAllCountry(){
		if(this.graph.vertexSet().size() != 0)
			this.graph = new SimpleGraph<>(DefaultWeightedEdge.class);
		List<Country> lista = dao.loadAllCountries();
		for(Country c: lista) {
			nodes.put(c.getCode(), c);
			nomi.put(c.getNome(), c.getCode());
		}
	}
	public List<String> getAbb(){
		return this.dao.getAbb();
	}
	public Integer buildGraph(int anno) {
		getAllCountry();
		Graphs.addAllVertices(this.graph, nodes.values());
		
		List<Border> allEdges = this.dao.getCountryPairs(anno);
		for(Border edgeI: allEdges) {
//			if(nodes.get(edgeI.getCodeA()) == null)
//				System.out.println("A è null");
//			else
//				System.out.println(nodes.get(edgeI.getCodeA()));
//			if(nodes.get(edgeI.getCodeB()) == null)
//				System.out.println("B è null" + edgeI.toString());
//			else
//				System.out.println(nodes.get(edgeI.getCodeB()));

			Graphs.addEdgeWithVertices(this.graph, nodes.get(edgeI.getCodeA()), nodes.get(edgeI.getCodeB()));
		}
		return this.graph.vertexSet().size();
	}
	public HashMap<String, Integer> calcolaConfini(){
		HashMap<String, Integer> nomi = new HashMap<>();
		ConnectivityInspector<Country, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(this.graph);
		for(Country c: this.graph.vertexSet()) {
			Set<Country> conn = inspector.connectedSetOf(c);
			nomi.put(c.getNome(), conn.size());
		}
		return nomi;
	}
	public ArrayList<Country> connessi(String nome){
		Country c = nodes.get(nomi.get(nome));
		ConnectivityInspector<Country, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(this.graph);
		Set<Country> conn = inspector.connectedSetOf(c);
		ArrayList<Country> lista = new ArrayList<>();
		for(Country co: conn) {
			lista.add(co);
		}
		return lista;

		
	}
}
