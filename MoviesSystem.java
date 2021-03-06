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

import java.io.File;

import iristk.situated.SituatedDialogSystem;
import iristk.situated.SystemAgentFlow;
import iristk.speech.SpeechGrammarContext;
import iristk.speech.Voice.Gender;
import iristk.speech.windows.WindowsRecognizerFactory;
import iristk.speech.windows.WindowsSynthesizer;
import iristk.util.Language;
import iristk.cfg.SRGSGrammar;
import iristk.flow.FlowModule;
import iristk.kinect.KinectRecognizerFactory;

public class MoviesSystem {
		
	public MoviesSystem() throws Exception {
		SituatedDialogSystem system = new SituatedDialogSystem(this.getClass());
		SystemAgentFlow systemAgentFlow = system.addSystemAgent();
	
		system.setLanguage(Language.ENGLISH_US);
	
		system.setupLogging(new File("C:/Users/Yvaine/IrisTK/app/movies/src/iristk/app/movies/Logging"), true);
		
		system.setupGUI();
		
		system.setupKinect();
		
		//system.setupMonoMicrophone(new WindowsRecognizerFactory());
		//system.setupStereoMicrophones(new WindowsRecognizerFactory());
		system.setupKinectMicrophone(new KinectRecognizerFactory());
				
		////system.connectToBroker("furhat", "130.209.247.76");
		//system.connectToBroker("furhat", "172.20.89.80");
		system.setupFace(new WindowsSynthesizer(), Gender.MALE);
		
		system.addModule(new FlowModule(new MoviesFlow(systemAgentFlow)));
		system.loadContext("default", new SpeechGrammarContext(new SRGSGrammar(getClass().getResource("MoviesGrammar.xml").toURI())));
		
		system.loadPositions(system.getFile("situation.properties"));		
		system.sendStartSignal();
	}

	public static void main(String[] args) throws Exception {
		new MoviesSystem();
	}

}
