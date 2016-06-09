package com.ardublock.translator.block.rokenbok.ircommands;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class CMD_MOTOR_3_BACKWARD extends TranslatorBlock
{

	public CMD_MOTOR_3_BACKWARD(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		return codePrefix + "CMD_MOTOR_3_BACKWARD" + codeSuffix;
	}

}
