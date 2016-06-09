package com.ardublock.translator.block.rokenbok;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class motor extends TranslatorBlock
{

	public motor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
				
		TranslatorBlock ChannelBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock SpeedBlock = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock DirectionBlock = this.getRequiredTranslatorBlockAtSocket(2);
		
		String ChannelNumber = ChannelBlock.toCode();
		String Speed = SpeedBlock.toCode();
		String Direction = DirectionBlock.toCode();
		
		
		if (motor.isInteger(ChannelNumber))
		{
	 		if (Integer.parseInt(ChannelNumber) > 4 || Integer.parseInt(ChannelNumber) < 1) 
			{
				throw new BlockException(this.blockId, "the Channel# of DC Motor must be 1,2,3 or 4");
				//ChannelNumber = "4";
			}
		}
		
		if (motor.isInteger(Speed))
		{
			if (Integer.parseInt(Speed) > 1023 || Integer.parseInt(Speed) < 0) 
			{
				throw new BlockException(this.blockId, "the Speed of DC Motor must be 0(stop) to 1023(full speed)");
				//Speed = "1023";
			} 
		}

		if (!Direction.equals("CLOCKWISE")  && !Direction.equals("COUNTER_CLOCKWISE") ) 
		{
			throw new BlockException(this.blockId, "the Direction of DC Motor must be CLOCKWISE or COUNTER_CLOCKWISE");
			//Direction = "CLOCKWISE";
		}
		
		
		String MotorName = "motorModule_" + ChannelNumber;
		
		String ret = "rok.motorWrite( " + ChannelNumber + "," + Speed + "," + Direction + " )" + ";\n";
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
	
	
