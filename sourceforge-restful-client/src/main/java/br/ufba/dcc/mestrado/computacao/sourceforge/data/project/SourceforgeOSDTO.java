package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeSingleValueDTO;
import br.ufba.dcc.mestrado.computacao.xstream.converters.SourceforgeSingleValueDTOConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias(value = SourceforgeOSDTO.NODE_NAME)
@XStreamConverter(value = SourceforgeSingleValueDTOConverter.class)
public class SourceforgeOSDTO extends SourceforgeSingleValueDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -948597498634267724L;
	
	public final static String NODE_NAME = "os";
	

}
