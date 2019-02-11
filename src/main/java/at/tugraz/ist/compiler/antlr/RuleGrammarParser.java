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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, NUMBER=16, 
		ID=17, LT=18, LTE=19, WS=20;
	public static final int
		RULE_program = 0, RULE_memory = 1, RULE_predicate = 2, RULE_r_rules = 3, 
		RULE_r_rule = 4, RULE_preconditions = 5, RULE_worldDeletions = 6, RULE_rule_goal = 7, 
		RULE_damping = 8, RULE_agingTarget = 9, RULE_alist = 10, RULE_alistentry = 11, 
		RULE_action = 12, RULE_expr = 13, RULE_sign = 14, RULE_mulop = 15, RULE_value = 16;
	public static final String[] ruleNames = {
		"program", "memory", "predicate", "r_rules", "r_rule", "preconditions", 
		"worldDeletions", "rule_goal", "damping", "agingTarget", "alist", "alistentry", 
		"action", "expr", "sign", "mulop", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'->'", "'+'", "'#'", "','", "'-'", "'['", "']'", "'|'", 
		"'('", "')'", "'a'", "':'", "'*'", "'/'", null, null, "'<'", "'<='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "NUMBER", "ID", "LT", "LTE", "WS"
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
			setState(35);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(34);
				memory();
				}
				break;
			}
			setState(37);
			r_rules();
			setState(38);
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
			setState(43); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(40);
					predicate();
					setState(41);
					match(T__0);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(45); 
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
			setState(47);
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
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				r_rule();
				setState(50);
				match(T__0);
				}
				}
				setState(54); 
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
		public DampingContext damping() {
			return getRuleContext(DampingContext.class,0);
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
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(56);
				preconditions();
				}
			}

			setState(59);
			match(T__1);
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(60);
				match(T__2);
				setState(61);
				((R_ruleContext)_localctx).WorldAddtion = predicate();
				}
				break;
			case T__3:
				{
				setState(62);
				match(T__3);
				setState(63);
				((R_ruleContext)_localctx).Goal = predicate();
				}
				break;
			case T__5:
			case ID:
				break;
			default:
				break;
			}
			setState(66);
			worldDeletions();
			setState(67);
			action();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(68);
				alist();
				}
			}

			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(71);
				rule_goal();
				}
			}

			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(74);
				damping();
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
			setState(77);
			predicate();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(78);
				match(T__4);
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
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(85);
				match(T__5);
				setState(86);
				predicate();
				}
				}
				setState(91);
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
			setState(92);
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

	public static class DampingContext extends ParserRuleContext {
		public Token Damping;
		public Token Aging;
		public Token ActivityScaling;
		public Token DampingScaling;
		public AgingTargetContext agingTarget() {
			return getRuleContext(AgingTargetContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RuleGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RuleGrammarParser.NUMBER, i);
		}
		public DampingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_damping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterDamping(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitDamping(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitDamping(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DampingContext damping() throws RecognitionException {
		DampingContext _localctx = new DampingContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_damping);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(T__6);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(95);
				((DampingContext)_localctx).Damping = match(NUMBER);
				}
			}

			setState(98);
			match(T__4);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(99);
				((DampingContext)_localctx).Aging = match(NUMBER);
				}
			}

			setState(102);
			match(T__4);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8 || _la==NUMBER) {
				{
				setState(103);
				agingTarget();
				}
			}

			setState(106);
			match(T__4);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(107);
				((DampingContext)_localctx).ActivityScaling = match(NUMBER);
				}
			}

			setState(110);
			match(T__4);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(111);
				((DampingContext)_localctx).DampingScaling = match(NUMBER);
				}
			}

			setState(114);
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

	public static class AgingTargetContext extends ParserRuleContext {
		public AgingTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agingTarget; }
	 
		public AgingTargetContext() { }
		public void copyFrom(AgingTargetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AgingNoBoundContext extends AgingTargetContext {
		public Token MaxAging;
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public AgingNoBoundContext(AgingTargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterAgingNoBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitAgingNoBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitAgingNoBound(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AgingLowerBoundContext extends AgingTargetContext {
		public Token MaxAging;
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public AgingLowerBoundContext(AgingTargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterAgingLowerBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitAgingLowerBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitAgingLowerBound(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AgingUpperBoundContext extends AgingTargetContext {
		public Token MaxAging;
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public AgingUpperBoundContext(AgingTargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterAgingUpperBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitAgingUpperBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitAgingUpperBound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AgingTargetContext agingTarget() throws RecognitionException {
		AgingTargetContext _localctx = new AgingTargetContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_agingTarget);
		try {
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new AgingNoBoundContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((AgingNoBoundContext)_localctx).MaxAging = match(NUMBER);
				}
				break;
			case 2:
				_localctx = new AgingLowerBoundContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(T__8);
				setState(118);
				((AgingLowerBoundContext)_localctx).MaxAging = match(NUMBER);
				}
				break;
			case 3:
				_localctx = new AgingUpperBoundContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				((AgingUpperBoundContext)_localctx).MaxAging = match(NUMBER);
				setState(120);
				match(T__8);
				}
				break;
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
		enterRule(_localctx, 20, RULE_alist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__9);
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				{
				setState(124);
				alistentry();
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(125);
						match(T__4);
						setState(126);
						alistentry();
						}
						} 
					}
					setState(131);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(132);
					match(T__4);
					setState(133);
					expr(0);
					}
				}

				}
				}
				break;
			case 2:
				{
				setState(136);
				expr(0);
				}
				break;
			}
			setState(139);
			match(T__10);
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
		enterRule(_localctx, 22, RULE_alistentry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(NUMBER);
			setState(142);
			_la = _input.LA(1);
			if ( !(_la==LT || _la==LTE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(143);
			match(T__11);
			setState(144);
			_la = _input.LA(1);
			if ( !(_la==LT || _la==LTE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(145);
			match(NUMBER);
			setState(146);
			match(T__12);
			setState(147);
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
		enterRule(_localctx, 24, RULE_action);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(ID);
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(150);
					match(T__0);
					setState(151);
					match(ID);
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SignExprContext extends ExprContext {
		public ExprContext LHS;
		public ExprContext RHS;
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterSignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitSignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitSignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueExprContext extends ExprContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ValueExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterValueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitValueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitValueExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnarySignExprContext extends ExprContext {
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UnarySignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterUnarySignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitUnarySignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitUnarySignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulopExprContext extends ExprContext {
		public ExprContext LHS;
		public ExprContext RHS;
		public MulopContext mulop() {
			return getRuleContext(MulopContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulopExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterMulopExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitMulopExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitMulopExpr(this);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__5:
				{
				_localctx = new UnarySignExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(158);
				sign();
				setState(159);
				value();
				}
				break;
			case T__9:
			case T__11:
			case NUMBER:
				{
				_localctx = new ValueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(172);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new MulopExprContext(new ExprContext(_parentctx, _parentState));
						((MulopExprContext)_localctx).LHS = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(165);
						mulop();
						setState(166);
						((MulopExprContext)_localctx).RHS = expr(4);
						}
						break;
					case 2:
						{
						_localctx = new SignExprContext(new ExprContext(_parentctx, _parentState));
						((SignExprContext)_localctx).LHS = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(168);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(169);
						sign();
						setState(170);
						((SignExprContext)_localctx).RHS = expr(3);
						}
						break;
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 28, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
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
		enterRule(_localctx, 30, RULE_mulop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !(_la==T__13 || _la==T__14) ) {
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
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NumValueContext extends ValueContext {
		public TerminalNode NUMBER() { return getToken(RuleGrammarParser.NUMBER, 0); }
		public NumValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterNumValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitNumValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitNumValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BraceValueContext extends ValueContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BraceValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterBraceValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitBraceValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitBraceValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarValueContext extends ValueContext {
		public VarValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).enterVarValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleGrammarListener ) ((RuleGrammarListener)listener).exitVarValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleGrammarVisitor ) return ((RuleGrammarVisitor<? extends T>)visitor).visitVarValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_value);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				_localctx = new VarValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(T__11);
				}
				break;
			case NUMBER:
				_localctx = new NumValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(NUMBER);
				}
				break;
			case T__9:
				_localctx = new BraceValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				match(T__9);
				setState(184);
				expr(0);
				setState(185);
				match(T__10);
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
		case 13:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u00c0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\5\2&\n\2\3\2\3\2\3\2\3\3\3\3\3\3\6\3.\n\3\r\3\16\3/\3\4\3\4\3\5\3"+
		"\5\3\5\6\5\67\n\5\r\5\16\58\3\6\5\6<\n\6\3\6\3\6\3\6\3\6\3\6\5\6C\n\6"+
		"\3\6\3\6\3\6\5\6H\n\6\3\6\5\6K\n\6\3\6\5\6N\n\6\3\7\3\7\3\7\7\7S\n\7\f"+
		"\7\16\7V\13\7\3\b\3\b\7\bZ\n\b\f\b\16\b]\13\b\3\t\3\t\3\n\3\n\5\nc\n\n"+
		"\3\n\3\n\5\ng\n\n\3\n\3\n\5\nk\n\n\3\n\3\n\5\no\n\n\3\n\3\n\5\ns\n\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13|\n\13\3\f\3\f\3\f\3\f\7\f\u0082\n"+
		"\f\f\f\16\f\u0085\13\f\3\f\3\f\5\f\u0089\n\f\3\f\5\f\u008c\n\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u009b\n\16\f\16\16"+
		"\16\u009e\13\16\3\17\3\17\3\17\3\17\3\17\5\17\u00a5\n\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u00af\n\17\f\17\16\17\u00b2\13\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00be\n\22\3\22\2\3"+
		"\34\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\5\3\2\24\25\4\2\5\5"+
		"\b\b\3\2\20\21\2\u00c9\2%\3\2\2\2\4-\3\2\2\2\6\61\3\2\2\2\b\66\3\2\2\2"+
		"\n;\3\2\2\2\fO\3\2\2\2\16[\3\2\2\2\20^\3\2\2\2\22`\3\2\2\2\24{\3\2\2\2"+
		"\26}\3\2\2\2\30\u008f\3\2\2\2\32\u0097\3\2\2\2\34\u00a4\3\2\2\2\36\u00b3"+
		"\3\2\2\2 \u00b5\3\2\2\2\"\u00bd\3\2\2\2$&\5\4\3\2%$\3\2\2\2%&\3\2\2\2"+
		"&\'\3\2\2\2\'(\5\b\5\2()\7\2\2\3)\3\3\2\2\2*+\5\6\4\2+,\7\3\2\2,.\3\2"+
		"\2\2-*\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\5\3\2\2\2\61\62\7\23"+
		"\2\2\62\7\3\2\2\2\63\64\5\n\6\2\64\65\7\3\2\2\65\67\3\2\2\2\66\63\3\2"+
		"\2\2\678\3\2\2\28\66\3\2\2\289\3\2\2\29\t\3\2\2\2:<\5\f\7\2;:\3\2\2\2"+
		";<\3\2\2\2<=\3\2\2\2=B\7\4\2\2>?\7\5\2\2?C\5\6\4\2@A\7\6\2\2AC\5\6\4\2"+
		"B>\3\2\2\2B@\3\2\2\2BC\3\2\2\2CD\3\2\2\2DE\5\16\b\2EG\5\32\16\2FH\5\26"+
		"\f\2GF\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IK\5\20\t\2JI\3\2\2\2JK\3\2\2\2KM\3"+
		"\2\2\2LN\5\22\n\2ML\3\2\2\2MN\3\2\2\2N\13\3\2\2\2OT\5\6\4\2PQ\7\7\2\2"+
		"QS\5\6\4\2RP\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\r\3\2\2\2VT\3\2\2"+
		"\2WX\7\b\2\2XZ\5\6\4\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\17\3"+
		"\2\2\2][\3\2\2\2^_\7\22\2\2_\21\3\2\2\2`b\7\t\2\2ac\7\22\2\2ba\3\2\2\2"+
		"bc\3\2\2\2cd\3\2\2\2df\7\7\2\2eg\7\22\2\2fe\3\2\2\2fg\3\2\2\2gh\3\2\2"+
		"\2hj\7\7\2\2ik\5\24\13\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2ln\7\7\2\2mo\7\22"+
		"\2\2nm\3\2\2\2no\3\2\2\2op\3\2\2\2pr\7\7\2\2qs\7\22\2\2rq\3\2\2\2rs\3"+
		"\2\2\2st\3\2\2\2tu\7\n\2\2u\23\3\2\2\2v|\7\22\2\2wx\7\13\2\2x|\7\22\2"+
		"\2yz\7\22\2\2z|\7\13\2\2{v\3\2\2\2{w\3\2\2\2{y\3\2\2\2|\25\3\2\2\2}\u008b"+
		"\7\f\2\2~\u0083\5\30\r\2\177\u0080\7\7\2\2\u0080\u0082\5\30\r\2\u0081"+
		"\177\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2"+
		"\2\u0084\u0088\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\7\2\2\u0087\u0089"+
		"\5\34\17\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008c\3\2\2\2"+
		"\u008a\u008c\5\34\17\2\u008b~\3\2\2\2\u008b\u008a\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\7\r\2\2\u008e\27\3\2\2\2\u008f\u0090\7\22\2\2\u0090"+
		"\u0091\t\2\2\2\u0091\u0092\7\16\2\2\u0092\u0093\t\2\2\2\u0093\u0094\7"+
		"\22\2\2\u0094\u0095\7\17\2\2\u0095\u0096\5\34\17\2\u0096\31\3\2\2\2\u0097"+
		"\u009c\7\23\2\2\u0098\u0099\7\3\2\2\u0099\u009b\7\23\2\2\u009a\u0098\3"+
		"\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\33\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\b\17\1\2\u00a0\u00a1\5\36"+
		"\20\2\u00a1\u00a2\5\"\22\2\u00a2\u00a5\3\2\2\2\u00a3\u00a5\5\"\22\2\u00a4"+
		"\u009f\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00b0\3\2\2\2\u00a6\u00a7\f\5"+
		"\2\2\u00a7\u00a8\5 \21\2\u00a8\u00a9\5\34\17\6\u00a9\u00af\3\2\2\2\u00aa"+
		"\u00ab\f\4\2\2\u00ab\u00ac\5\36\20\2\u00ac\u00ad\5\34\17\5\u00ad\u00af"+
		"\3\2\2\2\u00ae\u00a6\3\2\2\2\u00ae\u00aa\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\35\3\2\2\2\u00b2\u00b0\3\2\2"+
		"\2\u00b3\u00b4\t\3\2\2\u00b4\37\3\2\2\2\u00b5\u00b6\t\4\2\2\u00b6!\3\2"+
		"\2\2\u00b7\u00be\7\16\2\2\u00b8\u00be\7\22\2\2\u00b9\u00ba\7\f\2\2\u00ba"+
		"\u00bb\5\34\17\2\u00bb\u00bc\7\r\2\2\u00bc\u00be\3\2\2\2\u00bd\u00b7\3"+
		"\2\2\2\u00bd\u00b8\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be#\3\2\2\2\32%/8;B"+
		"GJMT[bfjnr{\u0083\u0088\u008b\u009c\u00a4\u00ae\u00b0\u00bd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}