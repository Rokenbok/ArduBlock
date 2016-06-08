package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class light extends TranslatorBlock
{

	public light(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock ChannelBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock BrightnessBlock = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock ColorBlock = this.getRequiredTranslatorBlockAtSocket(2);
		
		String ChannelNumber = ChannelBlock.toCode();
		String Brightness = BrightnessBlock.toCode();
		String Color = ColorBlock.toCode();
		
		
		if (light.isInteger(ChannelNumber))
		{
	 		if (Integer.parseInt(ChannelNumber) > 4 || Integer.parseInt(ChannelNumber) < 1) 
			{
				throw new BlockException(this.blockId, "the Channel# of light must be 1,2,3 or 4");
				//ChannelNumber = "4";
			}
		}
		
		if (light.isInteger(Brightness))
		{
			if (Integer.parseInt(Brightness) > 1023 || Integer.parseInt(Brightness) < 0) 
			{
				throw new BlockException(this.blockId, "the Brightness of light must be 0(stop) to 1023(full speed)");
				//Brightness = "1023";
			} 
		}

		if (!Color.equals("RED")  && !Color.equals("GREEN") ) 
		{
			throw new BlockException(this.blockId, "the color of light must be RED or GREEN");
			//Color = "REDE";
		}
		
		
		String lightName = "lightModule_" + ChannelNumber;
		
		//lightWrite(lightModule, brightness, color);
		
		String ret = "rok.lightWrite( " + ChannelNumber + "," + Brightness + "," + Color + " )" + ";\n";
		
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
	
	
