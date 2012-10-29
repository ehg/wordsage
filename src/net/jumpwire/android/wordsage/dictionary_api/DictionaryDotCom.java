package net.jumpwire.android.wordsage.dictionary_api;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import net.jumpwire.android.wordsage.models.Dictionary;
import net.jumpwire.android.wordsage.models.Entry;
import net.jumpwire.android.wordsage.models.PartOfSpeech;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.util.Log;

public class DictionaryDotCom implements OnlineDictionary {
	
	private static final String API_KEY = ""; 
	private static final String API_URL = "http://api-pub.dictionary.com/v001"; 
	
	private DefaultHttpClient client = new DefaultHttpClient();

	
	@Override
	public ArrayList<String> getWordDefinitions(String word) {
		String url = API_URL + "?vid=" + API_KEY + "&q=" + word + "&type=define&site=dictionary";
		Log.d("BLAH", url);
		String xmlData = retrieve(url);
        Serializer serializer = new Persister();        
        
        Reader reader = new StringReader(xmlData);
        try {
			Dictionary osd = 
			    serializer.read(Dictionary.class, reader, false);
			
			if (osd.totalResults == 0) return null;
			
			ArrayList<String> definitions = new ArrayList<String>();
			
			for (Entry e : osd.entries)
			{
				for (PartOfSpeech p : e.partOfSpeeches)
				{
					for (String d : p.definitionSet)
					{
						definitions.add(d);
					}
				}
			}
			
			Log.d("PART", osd.entries.get(0).toString());
			return definitions;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
	
	public String retrieve(String url) {

        HttpGet getRequest = new HttpGet(url);

        try {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();

            if (getResponseEntity != null) {
            	String text = EntityUtils.toString(getResponseEntity);
            	Log.d("BLHA",  text );
                return text;
            }

        } 
        catch (IOException e) {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }

        return null;

    }

}
