package net.jumpwire.android.wordsage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "database";
    
    public static final String DICTIONARY_TABLE_NAME = "dictionary";
    
    private static final String KEY_ID = "_id";
    private static final String KEY_WORD = "word";
    private static final String KEY_DEFINITION = "definition";
    
    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" + KEY_ID +" INTEGER PRIMARY KEY autoincrement, " + 
                KEY_WORD + " TEXT, " +
                KEY_DEFINITION + " TEXT);";

    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	Log.d("TAG", DICTIONARY_TABLE_CREATE);
        db.execSQL(DICTIONARY_TABLE_CREATE);
        
        insertWord(db, "Erudite", "having or showing great knowledge or learning.");
        insertWord(db, "Quotidian", "of or occurring every day.");
        insertWord(db, "Convalescent", "a person recovering from an illness or operation");
        insertWord(db, "Emend", "alter something in such a way as to correct it.");
        insertWord(db, "Vestal", "pure or virgin");
        insertWord(db, "Trucent", "eager or quick to argue or fight");
        insertWord(db, "Bourgeois", "of or characteristic of the middle class, typically with reference to its perceived materialistic values or conventional attitudes");
        insertWord(db, "Demagogue", "a political leader who seeks support by appealing to popular desires and prejudices rather than by using rational argument");
        insertWord(db, "Locution", "a person's style of speech");
        insertWord(db, "Punk", "a worthless person");
        insertWord(db, "Superannuate", "to set aside or discard as old-fashioned or obsolete.");
        insertWord(db, "Turbid", "heavy, dark, or dense, as smoke or fog.");
        insertWord(db, "Diaphonous", "light, delicate, and translucent");
        insertWord(db, "Truculent", "eager or quick to argue or fight; aggressively defiant.");
        insertWord(db, "Tonsure", "a part of a monk's or priest's head left bare on top by shaving off the hair.");
        insertWord(db, "Compunction", "a feeling of guilt or moral scruple that follows the doing of something bad");
        insertWord(db, "Rusticate", "to send to the country");
        insertWord(db, "Felicity", "a cause or source of happiness.");
        insertWord(db, "Superfoetation", "the fertilization of an ovum in a female mammal already pregnant");
        insertWord(db, "Paramour", "a lover, esp. the illicit partner of a married person.");
        insertWord(db, "Gulosity", "excessive appetite; greediness; voracity; gluttony. ");
        insertWord(db, "Glaucous", "of a pale grayish or bluish green");
        insertWord(db, "Misanthrope", "a generalized dislike, distrust, disgust, contempt or hatred of the human species or human nature");
        insertWord(db, "Efface", "make oneself appear insignificant or inconspicuous.");
        insertWord(db, "Pyrrhonism", "named after the Greek skeptic Pyrrho, an extreme form of philosophical skepticism");
        insertWord(db, "Slake", "quench or satisfy (one's thirst)");
        insertWord(db, "Portentous", "done in a pompously or overly solemn manner");
        insertWord(db, "Solicitude", "care or concern for someone or something");
        insertWord(db, "Virile", "having or characterized by strength and energy.");
        insertWord(db, "Languid", "showing little or no spirit or animation; listless:");
        insertWord(db, "Mollify", "to calm in temper or feeling; soothe.");
        insertWord(db, "Atavistic", "the tendency to revert to ancestral type");
        insertWord(db, "Q.E.D.", "quod erat demonstrandum (which was to be demonstrated)");
        insertWord(db, "Polemical", "a controversial argument, especially one refuting or attacking a specific opinion or doctrine");
        insertWord(db, "Prodigal", "rashly or wastefully extravagant");
        insertWord(db, "Pernicious", "having a harmful effect, esp. in a gradual or subtle way.");
        insertWord(db, "Equanimity", "mental calmness, composure, and evenness of temper, esp. in a difficult situation.");
        insertWord(db, "Supervene", "occur later than a specified or implied event or action, typically in such a way as to change the situation");
        insertWord(db, "Lubricity", "feeling morbid sexual desire or a propensity to lewdness.");
        insertWord(db, "Ardour", "a feeling of strong eagerness (usually in favor of a person or cause)");
        insertWord(db, "Providential", "involving divine foresight or intervention");
        insertWord(db, "Visceral", "relating to deep inward feelings rather than to the intellect");
        insertWord(db, "Facetious", "treating serious issues with deliberately inappropriate humor; flippant.");
        insertWord(db, "Waggish", "humorous in a playful, mischievous, or facetious manner");
        insertWord(db, "Coleoptera", "an order of insects that comprises the beetles (including weevils), forming the largest order of animals on the earth");
        insertWord(db, "Oracular", "of or relating to an oracle.");
        insertWord(db, "Acolyte", "a devoted follower or attendant.");
        insertWord(db, "Surplice", "a loose white linen vestment varying from hip-length to calf-length, worn over a cassock by clergy, acolytes, and choristers at Christian church services.");
        insertWord(db, "Coruscate", "to give forth flashes of light; sparkle and glitter");
        insertWord(db, "Epigram", "a pithy saying or remark expressing an idea in a clever and amusing way");
        insertWord(db, "Ruminate", "think deeply about something");
        insertWord(db, "Dun", "a dull grayish-brown color.");
        insertWord(db, "Avarice", "extreme greed for wealth or material gain.");
        insertWord(db, "Serried", "pressed or crowded together, especially in rows");
        insertWord(db, "Peremptory", "insisting on immediate attention or obedience");
        insertWord(db, "Magniloquent", "lofty and extravagant in speech");
        insertWord(db, "Heredity", "the passing on of physical or mental characteristics genetically from one generation to another");
        insertWord(db, "Sibilant", "sounded with a hissing effect, for example s, sh");
        insertWord(db, "Recrudesce", "to break out anew or come into renewed activity, as after a period of quiescence.");
        insertWord(db, "Charwoman", "a woman hired to do cleaning or similar work, usually in a large building.");
        insertWord(db, "Saprophytic", "an organism, especially a fungus or bacterium, that grows on and derives its nourishment from dead or decaying organic matter.");
        insertWord(db, "Capricious", "characterized by or subject to whim; impulsive and unpredictable.");
        insertWord(db, "Jape", "a joke or quip.");
        insertWord(db, "Positivism", "a philosophical system recognizing only that which can be scientifically verified or which is capable of logical or mathematical proof, and therefore rejecting metaphysics and theism.");
        insertWord(db, "Lurid", "presented in vividly shocking or sensational terms");
        insertWord(db, "Purblind", "dim-witted.");
        insertWord(db, "Ravening", "extremely hungry and hunting for prey.");
        insertWord(db, "Lascivious", "given to or expressing lust; lecherous.");
        insertWord(db, "Pedagogue", "one who instructs in a pedantic or dogmatic manner");
        insertWord(db, "Expurgate", "to remove erroneous, vulgar, obscene, or otherwise objectionable material");
        
    }
    
    private void insertWord(SQLiteDatabase db, String word, String definition)
    {
    	ContentValues cv = new ContentValues();
    	cv.put("word", word);
        cv.put("definition", definition);
        db.insert(DICTIONARY_TABLE_NAME, null, cv);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("TAG", "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME);
        onCreate(db);
		
	}
	

}
