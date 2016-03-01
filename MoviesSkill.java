package iristk.app.movies;

import iristk.cfg.SRGSGrammar;
import iristk.flow.Flow;
import iristk.situated.Skill;
import iristk.situated.SkillRequirements;
import iristk.speech.SpeechGrammarContext;
import iristk.util.Language;

public class MoviesSkill extends Skill {

	@Override
	public Flow init() throws Exception {
		loadContext("default", new SpeechGrammarContext(new SRGSGrammar(getClass().getResource("MoviesGrammar.xml").toURI())));
		setDefaultContext("default");
		return new MoviesFlow(getSystemAgentFlow());
	}

	@Override
	public String getName() {
		return "Movies";
	}

	@Override
	public SkillRequirements getRequirements() {
		SkillRequirements requirements = super.getRequirements();
		requirements.requireLanguage(Language.ENGLISH_US);
		requirements.requireSpeechGrammar(true);
		return requirements;
	}
}
