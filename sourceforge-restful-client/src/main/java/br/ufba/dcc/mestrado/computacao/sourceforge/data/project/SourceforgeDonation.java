
package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import java.util.List;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(SourceforgeDonation.NODE_NAME)
public class SourceforgeDonation implements SourceforgeDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247001100858037885L;

	public final static String NODE_NAME = "donation";
	
	@XStreamImplicit(itemFieldName = "charities")
   	private List<SourceforgeCharity> charities;
   	
   	@XStreamAlias("comment")
   	private String comment;
   	
   	@XStreamAlias("donation-page")
   	private String donationPage;
   	
   	@XStreamAlias("status")
   	private String status;

	public List<SourceforgeCharity> getCharities() {
		return charities;
	}

	public void setCharities(List<SourceforgeCharity> charities) {
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
