package net.jumpwire.android.eruditer.models;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(name=("dictionary"))
public class Dictionary {
	
	@Attribute(name="totalresults")
	public int totalResults;
	
	@ElementList(name="entry",inline=true)
	public List<Entry> entries;
}
