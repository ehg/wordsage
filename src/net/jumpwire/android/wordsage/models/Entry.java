package net.jumpwire.android.wordsage.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="entry")
public class Entry {
	
	@ElementList(name="partofspeech", inline=true)
	public List<PartOfSpeech> partOfSpeeches;
}
