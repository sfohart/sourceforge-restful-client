package br.ufba.dcc.mestrado.computacao.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class PROJECT {
	
	public static final String DC_URI = "http://dublincore.org/documents/dcmi-namespace/";
	public static final String DOAP_URI = "http://usefulinc.com/ns/doap#";
	public static final String SF_URI = "http://sourceforge.net/api/sfelements.rdf#";
	public static final String FOAF_URI = "http://xmlns.com/foaf/0.1/";
	public static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String RDFS_URI = "http://www.w3.org/2000/01/rdf-schema#";
	
	private static final Model m  = ModelFactory.createDefaultModel();
	
	public static final Property name = m.createProperty(DOAP_URI , "name");
	
}