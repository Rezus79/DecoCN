package fr.DecoCN.bo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Fiche {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String support;
	
	private String dimension; 
	
	private String nomDeFichier;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 10485760)
	private byte[] contenu; 
	
	private boolean validation;
	
	private boolean refus;
	
	
	
	public Fiche() {}



	public Fiche(Long id, String email, String support, String dimension, String nomDeFichier, byte[] contenu,
			boolean validation, boolean refus) {
		this.id = id;
		this.email = email;
		this.support = support;
		this.dimension = dimension;
		this.nomDeFichier = nomDeFichier;
		this.contenu = contenu;
		this.validation = false;
		this.refus = false;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSupport() {
		return support;
	}



	public void setSupport(String support) {
		this.support = support;
	}



	public String getDimension() {
		return dimension;
	}



	public void setDimension(String dimension) {
		this.dimension = dimension;
	}



	public String getNomDeFichier() {
		return nomDeFichier;
	}



	public void setNomDeFichier(String nomDeFichier) {
		this.nomDeFichier = nomDeFichier;
	}



	public byte[] getContenu() {
		return contenu;
	}



	public void setContenu(byte[] contenu) {
		this.contenu = contenu;
	}



	public boolean isValidation() {
		return validation;
	}



	public void setValidation(boolean validation) {
		this.validation = validation;
	}



	public boolean isRefus() {
		return refus;
	}



	public void setRefus(boolean refus) {
		this.refus = refus;
	}
	
	
}
