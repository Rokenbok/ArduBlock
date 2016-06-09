package com.ardublock.translator.block.rokenbok;


import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class setter_variable_byte extends TranslatorBlock
{	

	public setter_variable_byte(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock variableBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock valueBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		if (!(variableBlock instanceof variable_byte)) {
		}
		
		String variable = variableBlock.toCode();
		String value = valueBlock.toCode();
		
		
		String ret = variable + " = " + value + " ;\n";
		return ret;
	}

}
