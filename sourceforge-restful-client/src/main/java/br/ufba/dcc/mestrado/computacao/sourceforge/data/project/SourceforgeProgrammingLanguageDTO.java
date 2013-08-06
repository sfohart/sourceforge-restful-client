package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeSingleValueDTO;
import br.ufba.dcc.mestrado.computacao.xstream.converters.SourceforgeSingleValueDTOConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias(value = SourceforgeProgrammingLanguageDTO.NODE_NAME)
@XStreamConverter(value = SourceforgeSingleValueDTOConverter.class)
public class SourceforgeProgrammingLanguageDTO extends SourceforgeSingleValueDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5365194620687614284L;
	
	public final static String NODE_NAME = "programming-language";
	


}
