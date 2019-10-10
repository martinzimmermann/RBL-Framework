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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, NUMBER=7, ID=8, LT=9, 
		LTE=10, LINEEND=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "NUMBER", "DIGIT", "ID", 
		"LETTER", "UPPERCASE", "LOWERCASE", "LT", "LTE", "LINEEND", "DOT", "NEWLINE", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'#'", "','", "'+'", "'-'", "'.'", null, null, "'<'", "'<='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "NUMBER", "ID", "LT", "LTE", 
		"LINEEND", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16i\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\6\b"+
		"\66\n\b\r\b\16\b\67\3\b\3\b\6\b<\n\b\r\b\16\b=\5\b@\n\b\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\7\nH\n\n\f\n\16\nK\13\n\3\13\3\13\5\13O\n\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\5\20]\n\20\3\21\3\21\3\22\3"+
		"\22\3\22\5\22d\n\22\3\23\3\23\3\23\3\23\2\2\24\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\2\23\n\25\2\27\2\31\2\33\13\35\f\37\r!\2#\2%\16\3\2\4\4\2\f"+
		"\f\17\17\5\2\13\f\17\17\"\"\2k\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\23\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5*\3\2\2\2\7,\3\2"+
		"\2\2\t.\3\2\2\2\13\60\3\2\2\2\r\62\3\2\2\2\17\65\3\2\2\2\21A\3\2\2\2\23"+
		"C\3\2\2\2\25N\3\2\2\2\27P\3\2\2\2\31R\3\2\2\2\33T\3\2\2\2\35V\3\2\2\2"+
		"\37Y\3\2\2\2!^\3\2\2\2#c\3\2\2\2%e\3\2\2\2\'(\7/\2\2()\7@\2\2)\4\3\2\2"+
		"\2*+\7%\2\2+\6\3\2\2\2,-\7.\2\2-\b\3\2\2\2./\7-\2\2/\n\3\2\2\2\60\61\7"+
		"/\2\2\61\f\3\2\2\2\62\63\7\60\2\2\63\16\3\2\2\2\64\66\5\21\t\2\65\64\3"+
		"\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28?\3\2\2\29;\7\60\2\2:<"+
		"\5\21\t\2;:\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3\2\2\2?9\3\2\2\2"+
		"?@\3\2\2\2@\20\3\2\2\2AB\4\62;\2B\22\3\2\2\2CI\5\25\13\2DH\5\25\13\2E"+
		"H\5\21\t\2FH\7a\2\2GD\3\2\2\2GE\3\2\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2"+
		"IJ\3\2\2\2J\24\3\2\2\2KI\3\2\2\2LO\5\31\r\2MO\5\27\f\2NL\3\2\2\2NM\3\2"+
		"\2\2O\26\3\2\2\2PQ\4C\\\2Q\30\3\2\2\2RS\4c|\2S\32\3\2\2\2TU\7>\2\2U\34"+
		"\3\2\2\2VW\7>\2\2WX\7?\2\2X\36\3\2\2\2Y\\\5!\21\2Z]\5#\22\2[]\7\2\2\3"+
		"\\Z\3\2\2\2\\[\3\2\2\2] \3\2\2\2^_\7\60\2\2_\"\3\2\2\2`a\7\17\2\2ad\7"+
		"\f\2\2bd\t\2\2\2c`\3\2\2\2cb\3\2\2\2d$\3\2\2\2ef\t\3\2\2fg\3\2\2\2gh\b"+
		"\23\2\2h&\3\2\2\2\13\2\67=?GIN\\c\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}