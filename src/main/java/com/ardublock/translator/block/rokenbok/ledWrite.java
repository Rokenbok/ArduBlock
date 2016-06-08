package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class ledWrite extends TranslatorBlock
{

	public ledWrite(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock whichBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock modeBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String which = whichBlock.toCode();
		String mode = modeBlock.toCode();
		

		if (!which.equals("LED_LEFT")  && !which.equals("LED_RIGHT") && !which.equals("LED_BOTH") ) 
		{
			throw new BlockException(this.blockId, "Which LED is selected must be LED_LEFT, LED_RIGHT, or LED_BOTH"); 
		}
		
		
		if (!mode.equals("LED_OFF")  && !mode.equals("LED_ON")  && !mode.equals("LED_TOGGLE") ) 
		{
			throw new BlockException(this.blockId, "the mode of led must be LED_OFF, LED_ON, or LED_TOGGLE");
		}
		
		
		//ledWrite(which(byte), mode(byte));
		
		String ret = "rok.ledWrite( " + which + "," + mode + " )" + ";\n";
		
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
	
	
