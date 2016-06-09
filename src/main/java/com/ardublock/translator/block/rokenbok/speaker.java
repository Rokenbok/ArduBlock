package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class speaker extends TranslatorBlock
{

	public speaker(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock NoteBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock DurationBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String Note = NoteBlock.toCode();
		String Duration = DurationBlock.toCode();
		
		
		if (speaker.isInteger(Note))
		{
			if (Integer.parseInt(Note) > 1023 || Integer.parseInt(Note) < 0) 
			{
				throw new BlockException(this.blockId, "the Note of the speaker must be 0 to 1023");
				//Note = "440";
			} 
		}

		if (speaker.isInteger(Duration))
		{
			if (Integer.parseInt(Duration) > 60000 || Integer.parseInt(Duration) < 0) 
			{
				throw new BlockException(this.blockId, "the Duration of the speaker must be 0 to 60000 milliseconds");
				//Duration = "500";
			} 
		}
		
		
		String ret = "rok.speakerWrite( " + Note + "," + Duration + " )" + "; // Play thisNote for noteDuration." + "\n";
		
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
	
	
