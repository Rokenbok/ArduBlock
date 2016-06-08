package com.ardublock.translator.block.rokenbok.ircommands;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class CMD_SPIN_RIGHT extends TranslatorBlock
{

	public CMD_SPIN_RIGHT(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		return codePrefix + "CMD_SPIN_RIGHT" + codeSuffix;
	}

}
