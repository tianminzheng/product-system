package com.tianyalan.product.integration.util;

public class UniqueSequenceGenerator {

	private static UniqueSequenceGenerator instance = new UniqueSequenceGenerator();
	
	public String getUniqueSequence(){
		return "test_unique_sequence_for_demo_only";
	}
	
	public static UniqueSequenceGenerator getInstance(){
		return instance;
	}
}
