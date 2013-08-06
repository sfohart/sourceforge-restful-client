package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeSingleValueDTO;
import br.ufba.dcc.mestrado.computacao.xstream.converters.SourceforgeSingleValueDTOConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias(value = SourceforgeDatabaseDTO.NODE_NAME)
@XStreamConverter(value = SourceforgeSingleValueDTOConverter.class)
public class SourceforgeDatabaseDTO extends SourceforgeSingleValueDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4135082791504685723L;
	
	public final static String NODE_NAME = "database";


}
