package net.jumpwire.android.wordsage.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="partofspeech")
public class PartOfSpeech {
	
	@ElementList(name="defset")
	public List<String> definitionSet; 
	
}
