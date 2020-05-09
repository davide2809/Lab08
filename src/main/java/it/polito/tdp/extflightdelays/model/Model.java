package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer,Airport> idMap;
	
	public Model() {
		idMap=new HashMap<Integer,Airport>();
	}
	
	public void caricaGrafo(float x) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		ExtFlightDelaysDAO dao=new ExtFlightDelaysDAO();
		dao.caricaIdMap(idMap);
		
		Graphs.addAllVertices(grafo, idMap.values());
		
		for(Rotte r:dao.getRotte(idMap)) {
			if(r.getDistanzaMedia()>x) {
				if(!grafo.containsEdge(idMap.get(r.getA1().getId()), idMap.get(r.getA2().getId()))) {
					Graphs.addEdge(grafo, idMap.get(r.getA1().getId()), idMap.get(r.getA2().getId()), r.getDistanzaMedia());
				}
				else {
					DefaultWeightedEdge edge=grafo.getEdge(r.getA1(), r.getA2());
					float dist=(float) grafo.getEdgeWeight(edge);
					float newDist= (r.getDistanzaMedia()+dist);
					Graphs.addEdge(grafo, idMap.get(r.getA1().getId()), idMap.get(r.getA2().getId()), newDist);
				}
			}
		}	
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Rotte> ListaRotte() {
		List<Rotte> rotte=new ArrayList<Rotte>();
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			rotte.add(new Rotte(grafo.getEdgeSource(e),grafo.getEdgeTarget(e), (float) grafo.getEdgeWeight(e)));
		}
		return rotte;
	}
}

