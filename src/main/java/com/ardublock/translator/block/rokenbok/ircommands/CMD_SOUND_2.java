package com.ardublock.translator.block.rokenbok.ircommands;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class CMD_SOUND_2 extends TranslatorBlock
{

	public CMD_SOUND_2(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		return codePrefix + "CMD_SOUND_2" + codeSuffix;
	}

}
