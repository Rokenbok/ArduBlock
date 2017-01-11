package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class angle extends TranslatorBlock
{

	public angle(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock ChannelBlock = this.getRequiredTranslatorBlockAtSocket(0);
		
		String ChannelNumber = ChannelBlock.toCode();
		
		
		if (angle.isInteger(ChannelNumber))
		{
	 		if (Integer.parseInt(ChannelNumber) > 7 || Integer.parseInt(ChannelNumber) < 1)
			{
				throw new BlockException(this.blockId, "the Port# of a sensor must be 1-7");
				//ChannelNumber = "8";
			}
		}
	
		
		
		String angleName = "angle_" + ChannelNumber;
		
		String ret = "rok.sensorRead( " + ChannelNumber + " )";
		
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
	
	
