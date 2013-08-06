package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeSingleValueDTO;
import br.ufba.dcc.mestrado.computacao.xstream.converters.SourceforgeSingleValueDTOConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias(value = SourceforgeAudienceDTO.NODE_NAME)
@XStreamConverter(value = SourceforgeSingleValueDTOConverter.class)
public class SourceforgeAudienceDTO extends SourceforgeSingleValueDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4131662416963755668L;
	
	public final static String NODE_NAME = "audience";
	

}
