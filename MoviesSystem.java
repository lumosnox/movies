/*******************************************************************************
 * Copyright (c) 2014 Gabriel Skantze.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Gabriel Skantze - initial API and implementation
 ******************************************************************************/
package iristk.app.movies;

import iristk.situated.SituatedDialogSystem;
import iristk.situated.SystemAgentFlow;
import iristk.speech.Voice.Gender;
import iristk.speech.windows.WindowsRecognizerFactory;
import iristk.speech.windows.WindowsSynthesizer;
import iristk.util.Language;
import iristk.flow.FlowModule;
import iristk.kinect.KinectRecognizerFactory;

public class MoviesSystem {
		
	public MoviesSystem() throws Exception {
		SituatedDialogSystem system = new SituatedDialogSystem(this.getClass());
		SystemAgentFlow systemAgentFlow = system.addSystemAgent();
		
		system.setLanguage(Language.ENGLISH_US);
	
		//system.setupLogging(new File("c:/iristk_logging"), true);
		
		system.setupGUI();
		
		//system.setupKinect();
		
		//system.setupMonoMicrophone(new WindowsRecognizerFactory());
		system.setupStereoMicrophones(new WindowsRecognizerFactory());
		//system.setupKinectMicrophone(new KinectRecognizerFactory());
				
		//system.connectToBroker("furhat", "127.0.0.1");
		system.setupFace(new WindowsSynthesizer(), Gender.FEMALE);
		
		system.addModule(new FlowModule(new MoviesFlow(systemAgentFlow)));
		system.loadSpeechGrammar("default", getClass().getResource("MoviesGrammar.xml").toURI());
		
		system.loadPositions(system.getDataFile("situation.properties"));		
		system.sendStartSignal();
	}

	public static void main(String[] args) throws Exception {
		new MoviesSystem();
	}

}
