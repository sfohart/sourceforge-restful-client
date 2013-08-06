package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeSingleValueDTO;
import br.ufba.dcc.mestrado.computacao.xstream.converters.SourceforgeSingleValueDTOConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias(value = SourceforgeCategoryDTO.NODE_NAME)
@XStreamConverter(value = SourceforgeSingleValueDTOConverter.class)
public class SourceforgeCategoryDTO extends SourceforgeSingleValueDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5262923642364370418L;
	
	public final static String NODE_NAME = "category";

}
