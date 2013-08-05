
package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeDonationEntity.NODE_NAME)
public class SourceforgeDonationEntity extends SourceforgeBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1973172910605444852L;

	public final static String NODE_NAME = "donation";
	
	@OneToMany(cascade=CascadeType.ALL)
   	private List<SourceforgeCharityEntity> charities;
   	
   	@Column(name = "comment")
   	private String comment;
   	
   	@Column(name = "donation_page")
   	private String donationPage;
   	
   	@Column(name = "status")
   	private String status;

	public List<SourceforgeCharityEntity> getCharities() {
		return charities;
	}

	public void setCharities(List<SourceforgeCharityEntity> charities) {
		this.charities = charities;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDonationPage() {
		return donationPage;
	}

	public void setDonationPage(String donationPage) {
		this.donationPage = donationPage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   	
   	
}
