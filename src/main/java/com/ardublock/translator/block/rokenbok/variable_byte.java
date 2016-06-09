package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class variable_byte extends TranslatorBlock
{
	public variable_byte(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		String internalVariableName = translator.getNumberVariable(label);
		if (internalVariableName == null)
		{
			internalVariableName = translator.buildVariableName(label);
			translator.addNumberVariable(label, internalVariableName);
			translator.addDefinitionCommand("byte " + internalVariableName + ";");
//			translator.addSetupCommand(internalVariableName + " = \"\";");
		}
		return codePrefix + internalVariableName + codeSuffix;
	}

}
