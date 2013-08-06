package br.ufba.dcc.mestrado.computacao.xstream.converters;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeSingleValueDTO;

import com.thoughtworks.xstream.converters.basic.StringConverter;

public class SourceforgeSingleValueDTOConverter extends StringConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(final Class type) {
		if (SourceforgeSingleValueDTO.class.isAssignableFrom(type)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SourceforgeSingleValueDTO fromString(String str) {
		SourceforgeSingleValueDTO sourceforgeSingleValueDTO = new SourceforgeSingleValueDTO();
		sourceforgeSingleValueDTO.setName(str);
		return sourceforgeSingleValueDTO;
	}

	@Override
	public String toString(Object obj) {
		
		if (obj instanceof SourceforgeSingleValueDTO) {
			return ((SourceforgeSingleValueDTO) obj).getName();
		} else {
			return null;
		}
	}
}
