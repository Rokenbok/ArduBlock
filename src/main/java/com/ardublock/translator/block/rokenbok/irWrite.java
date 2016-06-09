package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class irWrite extends TranslatorBlock
{

	public irWrite(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock commTXBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock commandBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String commTX = commTXBlock.toCode();
		String command = commandBlock.toCode();
		
		
		if (irWrite.isInteger(commTX))
		{
	 		if (Integer.parseInt(commTX) > 8 || Integer.parseInt(commTX) < 1) 
			{
				throw new BlockException(this.blockId, "the Port# of TX Module must be 1-8");
				//commTX = "6";
			}
		}
		
		
		String ret = "rok.irWrite( " + commTX + "," + command + " )" + ";\n";
		
		translator.addHeaderFile("ROKduino.h");
		translator.addDefinitionCommand("ROKduino rok;");
		
		return codePrefix + ret + codeSuffix;
		}

		
		
		public static boolean isInteger( String input )
		{
			try 
			{
				Integer.parseInt( input );
				return true;
			}
			catch( Exception e )
			{
				return false;
			}
		}
	}
	
	
