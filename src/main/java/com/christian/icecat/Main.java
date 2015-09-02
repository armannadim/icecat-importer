package com.christian.icecat;

import com.christian.icecat.view.CommandLineView;

/**
 * Main class that will run the icecat importer
 * from the command line.
 *  
 * @author christian
 * Modified by Nadim
 *
 */
public class Main {
	
	public static void main(String... args){
		CommandLineView view = new CommandLineView();
                configProperties.init();
		view.run();
	}
	
}
