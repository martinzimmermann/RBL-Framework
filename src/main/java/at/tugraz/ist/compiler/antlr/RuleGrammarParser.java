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
		T__9=10, T__10=11, T__11=12, NUMBER=13, ID=14, LT=15, LTE=16, WS=17;
	public static final int
		RULE_program = 0, RULE_memory = 1, RULE_predicate = 2, RULE_r_rules = 3, 
		RULE_r_rule = 4, RULE_preconditions = 5, RULE_worldDeletions = 6, RULE_rule_goal = 7, 
		RULE_alist = 8, RULE_alistentry = 9, RULE_action = 10, RULE_expr = 11, 
		RULE_sign = 12, RULE_mulop = 13, RULE_value = 14;
	public static final String[] ruleNames = {
		"program", "memory", "predicate", "r_rules", "r_rule", "preconditions", 
		"worldDeletions", "rule_goal", "alist", "alistentry", "action", "expr", 
		"sign", "mulop", "value"
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
			setState(31);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(30);
				memory();
				}
				break;
			}
			setState(33);
			r_rules();
			setState(34);
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
			setState(39); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(36);
					predicate();
					setState(37);
					match(T__0);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(41); 
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
			setState(43);
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
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				r_rule();
				setState(46);
				match(T__0);
				}
				}
				setState(50); 
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
		public PredicateContext WorldAddtion;
		public PredicateContext Goal;
		public WorldDeletionsContext worldDeletions() {
			return getRuleContext(WorldDeletionsContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public PreconditionsContext preconditions() {
			return getRuleContext(PreconditionsContext.class,0);
		}
		public AlistContext alist() {
			return getRuleContext(AlistContext.class,0);
		}
		public Rule_goalContext rule_goal() {
			return getRuleContext(Rule_goalContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
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
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(52);
				preconditions();
				}
			}

			setState(55);
			match(T__1);
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(56);
				match(T__2);
				setState(57);
				((R_ruleContext)_localctx).WorldAddtion = predicate();
				}
				break;
			case T__3:
				{
				setState(58);
				match(T__3);
				setState(59);
				((R_ruleContext)_localctx).Goal = predicate();
				}
				break;
			case T__5:
			case ID:
				break;
			default:
				break;
			}
			setState(62);
			worldDeletions();
			setState(63);
			action();
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(64);
				alist();
				}
			}

			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(67);
				rule_goal();
				}
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

	public static class PreconditionsContext extends ParserRuleContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public PreconditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preconditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterPreconditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitPreconditions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitPreconditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreconditionsContext preconditions() throws RecognitionException {
		PreconditionsContext _localctx = new PreconditionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_preconditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			predicate();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(71);
				match(T__4);
				setState(72);
				predicate();
				}
				}
				setState(77);
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

	public static class WorldDeletionsContext extends ParserRuleContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public WorldDeletionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_worldDeletions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterWorldDeletions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitWorldDeletions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitWorldDeletions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WorldDeletionsContext worldDeletions() throws RecognitionException {
		WorldDeletionsContext _localctx = new WorldDeletionsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_worldDeletions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(78);
				match(T__5);
				setState(79);
				predicate();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Rule_goalContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public Rule_goalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_goal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterRule_goal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitRule_goal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitRule_goal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rule_goalContext rule_goal() throws RecognitionException {
		Rule_goalContext _localctx = new Rule_goalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_rule_goal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
		enterRule(_localctx, 16, RULE_alist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__6);
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				{
				setState(88);
				alistentry();
				setState(93);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(89);
						match(T__4);
						setState(90);
						alistentry();
						}
						} 
					}
					setState(95);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(96);
					match(T__4);
					setState(97);
					expr(0);
					}
				}

				}
				}
				break;
			case 2:
				{
				setState(100);
				expr(0);
				}
				break;
			}
			setState(103);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> LT() { return getTokens(RuleGrammarParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(RuleGrammarParser.LT, i);
		}
		public List<TerminalNode> LTE() { return getTokens(RuleGrammarParser.LTE); }
		public TerminalNode LTE(int i) {
			return getToken(RuleGrammarParser.LTE, i);
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
		enterRule(_localctx, 18, RULE_alistentry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(NUMBER);
			setState(106);
			_la = _input.LA(1);
			if ( !(_la==LT || _la==LTE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(107);
			match(T__8);
			setState(108);
			_la = _input.LA(1);
			if ( !(_la==LT || _la==LTE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(109);
			match(NUMBER);
			setState(110);
			match(T__9);
			setState(111);
			expr(0);
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
		public List<TerminalNode> ID() { return getTokens(RuleGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RuleGrammarParser.ID, i);
		}
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
		enterRule(_localctx, 20, RULE_action);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(ID);
			setState(118);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(114);
					match(T__0);
					setState(115);
					match(ID);
					}
					} 
				}
				setState(120);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class ExprContext extends ParserRuleContext {
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulopContext mulop() {
			return getRuleContext(MulopContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__5:
				{
				setState(122);
				sign();
				setState(123);
				value();
				}
				break;
			case T__6:
			case T__8:
			case NUMBER:
				{
				setState(125);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(136);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(128);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(129);
						mulop();
						setState(130);
						expr(4);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(132);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(133);
						sign();
						setState(134);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SignContext extends ParserRuleContext {
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MulopContext extends ParserRuleContext {
		public MulopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterMulop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitMulop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitMulop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulopContext mulop() throws RecognitionException {
		MulopContext _localctx = new MulopContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_mulop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !(_la==T__10 || _la==T__11) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_value);
		try {
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(T__8);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				match(NUMBER);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				match(T__6);
				setState(148);
				expr(0);
				setState(149);
				match(T__7);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23\u009c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\5\2\"\n\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\5\3\5\3\5\6\5\63\n\5"+
		"\r\5\16\5\64\3\6\5\68\n\6\3\6\3\6\3\6\3\6\3\6\5\6?\n\6\3\6\3\6\3\6\5\6"+
		"D\n\6\3\6\5\6G\n\6\3\7\3\7\3\7\7\7L\n\7\f\7\16\7O\13\7\3\b\3\b\7\bS\n"+
		"\b\f\b\16\bV\13\b\3\t\3\t\3\n\3\n\3\n\3\n\7\n^\n\n\f\n\16\na\13\n\3\n"+
		"\3\n\5\ne\n\n\3\n\5\nh\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\7\fw\n\f\f\f\16\fz\13\f\3\r\3\r\3\r\3\r\3\r\5\r\u0081"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u008b\n\r\f\r\16\r\u008e\13\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u009a\n\20\3\20"+
		"\2\3\30\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\5\3\2\21\22\4\2\5"+
		"\5\b\b\3\2\r\16\2\u009f\2!\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b\62\3\2\2\2"+
		"\n\67\3\2\2\2\fH\3\2\2\2\16T\3\2\2\2\20W\3\2\2\2\22Y\3\2\2\2\24k\3\2\2"+
		"\2\26s\3\2\2\2\30\u0080\3\2\2\2\32\u008f\3\2\2\2\34\u0091\3\2\2\2\36\u0099"+
		"\3\2\2\2 \"\5\4\3\2! \3\2\2\2!\"\3\2\2\2\"#\3\2\2\2#$\5\b\5\2$%\7\2\2"+
		"\3%\3\3\2\2\2&\'\5\6\4\2\'(\7\3\2\2(*\3\2\2\2)&\3\2\2\2*+\3\2\2\2+)\3"+
		"\2\2\2+,\3\2\2\2,\5\3\2\2\2-.\7\20\2\2.\7\3\2\2\2/\60\5\n\6\2\60\61\7"+
		"\3\2\2\61\63\3\2\2\2\62/\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2"+
		"\2\2\65\t\3\2\2\2\668\5\f\7\2\67\66\3\2\2\2\678\3\2\2\289\3\2\2\29>\7"+
		"\4\2\2:;\7\5\2\2;?\5\6\4\2<=\7\6\2\2=?\5\6\4\2>:\3\2\2\2><\3\2\2\2>?\3"+
		"\2\2\2?@\3\2\2\2@A\5\16\b\2AC\5\26\f\2BD\5\22\n\2CB\3\2\2\2CD\3\2\2\2"+
		"DF\3\2\2\2EG\5\20\t\2FE\3\2\2\2FG\3\2\2\2G\13\3\2\2\2HM\5\6\4\2IJ\7\7"+
		"\2\2JL\5\6\4\2KI\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\r\3\2\2\2OM\3"+
		"\2\2\2PQ\7\b\2\2QS\5\6\4\2RP\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\17"+
		"\3\2\2\2VT\3\2\2\2WX\7\17\2\2X\21\3\2\2\2Yg\7\t\2\2Z_\5\24\13\2[\\\7\7"+
		"\2\2\\^\5\24\13\2][\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`d\3\2\2\2a_"+
		"\3\2\2\2bc\7\7\2\2ce\5\30\r\2db\3\2\2\2de\3\2\2\2eh\3\2\2\2fh\5\30\r\2"+
		"gZ\3\2\2\2gf\3\2\2\2hi\3\2\2\2ij\7\n\2\2j\23\3\2\2\2kl\7\17\2\2lm\t\2"+
		"\2\2mn\7\13\2\2no\t\2\2\2op\7\17\2\2pq\7\f\2\2qr\5\30\r\2r\25\3\2\2\2"+
		"sx\7\20\2\2tu\7\3\2\2uw\7\20\2\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2"+
		"\2y\27\3\2\2\2zx\3\2\2\2{|\b\r\1\2|}\5\32\16\2}~\5\36\20\2~\u0081\3\2"+
		"\2\2\177\u0081\5\36\20\2\u0080{\3\2\2\2\u0080\177\3\2\2\2\u0081\u008c"+
		"\3\2\2\2\u0082\u0083\f\5\2\2\u0083\u0084\5\34\17\2\u0084\u0085\5\30\r"+
		"\6\u0085\u008b\3\2\2\2\u0086\u0087\f\4\2\2\u0087\u0088\5\32\16\2\u0088"+
		"\u0089\5\30\r\5\u0089\u008b\3\2\2\2\u008a\u0082\3\2\2\2\u008a\u0086\3"+
		"\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\31\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\t\3\2\2\u0090\33\3\2\2\2\u0091"+
		"\u0092\t\4\2\2\u0092\35\3\2\2\2\u0093\u009a\7\13\2\2\u0094\u009a\7\17"+
		"\2\2\u0095\u0096\7\t\2\2\u0096\u0097\5\30\r\2\u0097\u0098\7\n\2\2\u0098"+
		"\u009a\3\2\2\2\u0099\u0093\3\2\2\2\u0099\u0094\3\2\2\2\u0099\u0095\3\2"+
		"\2\2\u009a\37\3\2\2\2\23!+\64\67>CFMT_dgx\u0080\u008a\u008c\u0099";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}