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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NUMBER=9, 
		ID=10, WS=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "NUMBER", 
		"DIGIT", "ID", "LETTER", "UPPERCASE", "LOWERCASE", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'->'", "'+'", "'-'", "','", "'('", "')'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "NUMBER", "ID", 
		"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\rN\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\6\n\64\n\n\r\n\16"+
		"\n\65\3\13\3\13\3\f\3\f\3\f\3\f\7\f>\n\f\f\f\16\fA\13\f\3\r\3\r\5\rE\n"+
		"\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\2\27\f\31\2\33\2\35\2\37\r\3\2\3\5\2\13\f\17\17"+
		"\"\"\2N\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2\2\2\37\3"+
		"\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7&\3\2\2\2\t(\3\2\2\2\13*\3\2\2\2\r,\3\2"+
		"\2\2\17.\3\2\2\2\21\60\3\2\2\2\23\63\3\2\2\2\25\67\3\2\2\2\279\3\2\2\2"+
		"\31D\3\2\2\2\33F\3\2\2\2\35H\3\2\2\2\37J\3\2\2\2!\"\7\60\2\2\"\4\3\2\2"+
		"\2#$\7/\2\2$%\7@\2\2%\6\3\2\2\2&\'\7-\2\2\'\b\3\2\2\2()\7/\2\2)\n\3\2"+
		"\2\2*+\7.\2\2+\f\3\2\2\2,-\7*\2\2-\16\3\2\2\2./\7+\2\2/\20\3\2\2\2\60"+
		"\61\7<\2\2\61\22\3\2\2\2\62\64\5\25\13\2\63\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\63\3\2\2\2\65\66\3\2\2\2\66\24\3\2\2\2\678\4\62;\28\26\3\2\2\29?\5\31"+
		"\r\2:>\5\31\r\2;>\5\23\n\2<>\7a\2\2=:\3\2\2\2=;\3\2\2\2=<\3\2\2\2>A\3"+
		"\2\2\2?=\3\2\2\2?@\3\2\2\2@\30\3\2\2\2A?\3\2\2\2BE\5\35\17\2CE\5\33\16"+
		"\2DB\3\2\2\2DC\3\2\2\2E\32\3\2\2\2FG\4C\\\2G\34\3\2\2\2HI\4c|\2I\36\3"+
		"\2\2\2JK\t\2\2\2KL\3\2\2\2LM\b\20\2\2M \3\2\2\2\7\2\65=?D\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}