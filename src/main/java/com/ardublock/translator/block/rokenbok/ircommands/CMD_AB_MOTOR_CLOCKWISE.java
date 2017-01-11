package com.ardublock.translator.block.rokenbok.ircommands;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class CMD_AB_MOTOR_CLOCKWISE extends TranslatorBlock
{

	public CMD_AB_MOTOR_CLOCKWISE(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		return codePrefix + "CMD_AB_MOTOR_CLOCKWISE" + codeSuffix;
	}

}
