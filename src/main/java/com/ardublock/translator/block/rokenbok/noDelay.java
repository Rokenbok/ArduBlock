package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class noDelay extends TranslatorBlock
{

	public noDelay(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}
	
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock variableBlock = this.getRequiredTranslatorBlockAtSocket(0);	//interval value
		
		String interval = variableBlock.toCode();

		
		String intervalVariableName = interval;
		//if (intervalVariableName == null)
		{
			intervalVariableName = translator.buildVariableName("Interval");
			translator.addNumberVariable("Interval", intervalVariableName);
			translator.addDefinitionCommand("long " + intervalVariableName + " = "+interval+";");
			//translator.addSetupCommand(intervalVariableName + " = \"\";");
		}
		
		String previousVariableName = "prevMillis";
		//if (previousVariableName == null)
		{
			previousVariableName = translator.buildVariableName("previousMillis");
			translator.addNumberVariable("previousMillis", previousVariableName);
			translator.addDefinitionCommand("unsigned long " + previousVariableName + " = 0;");
			//translator.addSetupCommand(intervalVariableName + " = \"\";");
		}
		
		String currentVariableName = "currentMillis";
		//if (currentVariableName == null)
		{
			currentVariableName = translator.buildVariableName("currentMillis");
			translator.addNumberVariable("currentMillis", currentVariableName);
			//translator.addDefinitionCommand("unsigned long " + currentVariableName + " = millis();");
			//translator.addSetupCommand(intervalVariableName + " = \"\";");
		}
		
		
		
		
		String ret = "unsigned long " + currentVariableName + " = millis();\n\n";
		
		ret = ret + "if ("+currentVariableName+" - "+previousVariableName+" >= "+intervalVariableName+") {\n";
		ret = ret + "\t"+previousVariableName+" = "+currentVariableName+";\n\n";
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		while (translatorBlock != null)
		{
			ret = ret + "\t"+translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		ret = ret + "\t}\n\n";
		return ret;
	}

}
