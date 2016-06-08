package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class gateIR extends TranslatorBlock
{

	public gateIR(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock proxTXBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock proxRXBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String proxTX = proxTXBlock.toCode();
		String proxRX = proxRXBlock.toCode();
		
		
		if (gateIR.isInteger(proxTX))
		{
	 		if (Integer.parseInt(proxTX) > 8 || Integer.parseInt(proxTX) < 1) 
			{
				throw new BlockException(this.blockId, "the Port# of TX Module must be 1-8");
				//proxTX = "5";
			}
		}
		
		if (gateIR.isInteger(proxRX))
		{
	 		if (Integer.parseInt(proxRX) > 8 || Integer.parseInt(proxRX) < 1) 
			{
				throw new BlockException(this.blockId, "the Port# of RX Module must be 1-8");
				//proxRX = "8";
			}
		}		
		
		String ret = "rok.proximityRead( " + proxTX + "," + proxRX +" )";
		
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
	
	
