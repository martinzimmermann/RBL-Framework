// Generated from C:\Users\mzimmerm\IdeaProjects\rule-based-compiler\src\main\RuleGrammar.g4 by ANTLR 4.7
package at.tugraz.ist.compiler.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RuleGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NUMBER=10, ID=11, WS=12;
	public static final int
		RULE_program = 0, RULE_memory = 1, RULE_predicate = 2, RULE_r_rules = 3, 
		RULE_r_rule = 4, RULE_predicates = 5, RULE_goal = 6, RULE_alist = 7, RULE_alistentry = 8, 
		RULE_action = 9;
	public static final String[] ruleNames = {
		"program", "memory", "predicate", "r_rules", "r_rule", "predicates", "goal", 
		"alist", "alistentry", "action"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'->'", "'+'", "'!'", "'-'", "','", "'('", "')'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "NUMBER", 
		"ID", "WS"
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

	@Override
	public String getGrammarFileName() { return "RuleGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RuleGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public R_rulesContext r_rules() {
			return getRuleContext(R_rulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RuleGrammarParser.EOF, 0); }
		public MemoryContext memory() {
			return getRuleContext(MemoryContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(20);
				memory();
				}
				break;
			}
			setState(23);
			r_rules();
			setState(24);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemoryContext extends ParserRuleContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public MemoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterMemory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitMemory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitMemory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemoryContext memory() throws RecognitionException {
		MemoryContext _localctx = new MemoryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_memory);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(26);
					predicate();
					setState(27);
					match(T__0);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(31); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RuleGrammarParser.ID, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class R_rulesContext extends ParserRuleContext {
		public List<R_ruleContext> r_rule() {
			return getRuleContexts(R_ruleContext.class);
		}
		public R_ruleContext r_rule(int i) {
			return getRuleContext(R_ruleContext.class,i);
		}
		public R_rulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterR_rules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitR_rules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitR_rules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final R_rulesContext r_rules() throws RecognitionException {
		R_rulesContext _localctx = new R_rulesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_r_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				r_rule();
				setState(36);
				match(T__0);
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class R_ruleContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public AlistContext alist() {
			return getRuleContext(AlistContext.class,0);
		}
		public GoalContext goal() {
			return getRuleContext(GoalContext.class,0);
		}
		public PredicatesContext predicates() {
			return getRuleContext(PredicatesContext.class,0);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public R_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterR_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitR_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitR_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final R_ruleContext r_rule() throws RecognitionException {
		R_ruleContext _localctx = new R_ruleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_r_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(42);
				predicates();
				}
			}

			setState(45);
			match(T__1);
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(46);
				match(T__2);
				setState(47);
				predicate();
				}
				break;
			case T__3:
				{
				setState(48);
				match(T__3);
				setState(49);
				predicate();
				}
				break;
			case T__4:
			case ID:
				break;
			default:
				break;
			}
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(52);
				match(T__4);
				setState(53);
				predicate();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			action();
			setState(60);
			alist();
			setState(61);
			goal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicatesContext extends ParserRuleContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public PredicatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterPredicates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitPredicates(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitPredicates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicatesContext predicates() throws RecognitionException {
		PredicatesContext _localctx = new PredicatesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_predicates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			predicate();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(64);
				match(T__5);
				setState(65);
				predicate();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GoalContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitGoal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_goal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlistContext extends ParserRuleContext {
		public List<AlistentryContext> alistentry() {
			return getRuleContexts(AlistentryContext.class);
		}
		public AlistentryContext alistentry(int i) {
			return getRuleContext(AlistentryContext.class,i);
		}
		public AlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterAlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitAlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitAlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlistContext alist() throws RecognitionException {
		AlistContext _localctx = new AlistContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__6);
			setState(74);
			alistentry();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(75);
				match(T__5);
				setState(76);
				alistentry();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlistentryContext extends ParserRuleContext {
		public List<TerminalNode> NUMBER() { return getTokens(RuleGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RuleGrammarParser.NUMBER, i);
		}
		public AlistentryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alistentry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterAlistentry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitAlistentry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitAlistentry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlistentryContext alistentry() throws RecognitionException {
		AlistentryContext _localctx = new AlistentryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_alistentry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(NUMBER);
			setState(85);
			match(T__4);
			setState(86);
			match(NUMBER);
			setState(87);
			match(T__8);
			setState(88);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RuleGrammarParser.ID, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16_\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\5\2\30\n\2\3\2\3\2\3\2\3\3\3\3\3\3\6\3 \n\3\r\3\16\3!\3\4\3\4\3\5\3"+
		"\5\3\5\6\5)\n\5\r\5\16\5*\3\6\5\6.\n\6\3\6\3\6\3\6\3\6\3\6\5\6\65\n\6"+
		"\3\6\3\6\7\69\n\6\f\6\16\6<\13\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7E\n\7"+
		"\f\7\16\7H\13\7\3\b\3\b\3\t\3\t\3\t\3\t\7\tP\n\t\f\t\16\tS\13\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24"+
		"\2\2\2]\2\27\3\2\2\2\4\37\3\2\2\2\6#\3\2\2\2\b(\3\2\2\2\n-\3\2\2\2\fA"+
		"\3\2\2\2\16I\3\2\2\2\20K\3\2\2\2\22V\3\2\2\2\24\\\3\2\2\2\26\30\5\4\3"+
		"\2\27\26\3\2\2\2\27\30\3\2\2\2\30\31\3\2\2\2\31\32\5\b\5\2\32\33\7\2\2"+
		"\3\33\3\3\2\2\2\34\35\5\6\4\2\35\36\7\3\2\2\36 \3\2\2\2\37\34\3\2\2\2"+
		" !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\5\3\2\2\2#$\7\r\2\2$\7\3\2\2\2%&\5"+
		"\n\6\2&\'\7\3\2\2\')\3\2\2\2(%\3\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+"+
		"\t\3\2\2\2,.\5\f\7\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\64\7\4\2\2\60\61\7"+
		"\5\2\2\61\65\5\6\4\2\62\63\7\6\2\2\63\65\5\6\4\2\64\60\3\2\2\2\64\62\3"+
		"\2\2\2\64\65\3\2\2\2\65:\3\2\2\2\66\67\7\7\2\2\679\5\6\4\28\66\3\2\2\2"+
		"9<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=>\5\24\13\2>?\5\20"+
		"\t\2?@\5\16\b\2@\13\3\2\2\2AF\5\6\4\2BC\7\b\2\2CE\5\6\4\2DB\3\2\2\2EH"+
		"\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\r\3\2\2\2HF\3\2\2\2IJ\7\f\2\2J\17\3\2\2"+
		"\2KL\7\t\2\2LQ\5\22\n\2MN\7\b\2\2NP\5\22\n\2OM\3\2\2\2PS\3\2\2\2QO\3\2"+
		"\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\n\2\2U\21\3\2\2\2VW\7\f\2\2WX\7"+
		"\7\2\2XY\7\f\2\2YZ\7\13\2\2Z[\7\f\2\2[\23\3\2\2\2\\]\7\r\2\2]\25\3\2\2"+
		"\2\n\27!*-\64:FQ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}