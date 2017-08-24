// Generated from C:\Users\mzimmerm\IdeaProjects\rule-based-compiler\src\main\RuleGrammar.g4 by ANTLR 4.7
package at.tugraz.ist.compiler.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RuleGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, NUMBER=13, ID=14, LT=15, LTE=16, WS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "NUMBER", "DIGIT", "ID", "LETTER", "UPPERCASE", 
		"LOWERCASE", "LT", "LTE", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'->'", "'+'", "'#'", "','", "'-'", "'('", "')'", "'a'", 
		"':'", "'*'", "'/'", null, null, "'<'", "'<='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "NUMBER", "ID", "LT", "LTE", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public RuleGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RuleGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23o\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\6\16H\n\16\r\16\16\16I\3\16\3\16\6\16N\n\16\r\16\16\16O\5\16R"+
		"\n\16\3\17\3\17\3\20\3\20\3\20\3\20\7\20Z\n\20\f\20\16\20]\13\20\3\21"+
		"\3\21\5\21a\n\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\2\37\20!\2#\2%\2\'\21)\22+\23\3\2\3\5\2\13\f\17\17\"\"\2"+
		"q\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\37\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\3-\3\2\2\2\5/\3\2\2\2\7\62\3\2\2\2\t\64\3\2\2\2\13\66\3\2\2\2\r8"+
		"\3\2\2\2\17:\3\2\2\2\21<\3\2\2\2\23>\3\2\2\2\25@\3\2\2\2\27B\3\2\2\2\31"+
		"D\3\2\2\2\33G\3\2\2\2\35S\3\2\2\2\37U\3\2\2\2!`\3\2\2\2#b\3\2\2\2%d\3"+
		"\2\2\2\'f\3\2\2\2)h\3\2\2\2+k\3\2\2\2-.\7\60\2\2.\4\3\2\2\2/\60\7/\2\2"+
		"\60\61\7@\2\2\61\6\3\2\2\2\62\63\7-\2\2\63\b\3\2\2\2\64\65\7%\2\2\65\n"+
		"\3\2\2\2\66\67\7.\2\2\67\f\3\2\2\289\7/\2\29\16\3\2\2\2:;\7*\2\2;\20\3"+
		"\2\2\2<=\7+\2\2=\22\3\2\2\2>?\7c\2\2?\24\3\2\2\2@A\7<\2\2A\26\3\2\2\2"+
		"BC\7,\2\2C\30\3\2\2\2DE\7\61\2\2E\32\3\2\2\2FH\5\35\17\2GF\3\2\2\2HI\3"+
		"\2\2\2IG\3\2\2\2IJ\3\2\2\2JQ\3\2\2\2KM\7\60\2\2LN\5\35\17\2ML\3\2\2\2"+
		"NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QK\3\2\2\2QR\3\2\2\2R\34\3\2\2"+
		"\2ST\4\62;\2T\36\3\2\2\2U[\5!\21\2VZ\5!\21\2WZ\5\35\17\2XZ\7a\2\2YV\3"+
		"\2\2\2YW\3\2\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\ \3\2\2\2]"+
		"[\3\2\2\2^a\5%\23\2_a\5#\22\2`^\3\2\2\2`_\3\2\2\2a\"\3\2\2\2bc\4C\\\2"+
		"c$\3\2\2\2de\4c|\2e&\3\2\2\2fg\7>\2\2g(\3\2\2\2hi\7>\2\2ij\7?\2\2j*\3"+
		"\2\2\2kl\t\2\2\2lm\3\2\2\2mn\b\26\2\2n,\3\2\2\2\t\2IOQY[`\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}