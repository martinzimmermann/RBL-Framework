// Generated from C:\Users\mzimmerm\IdeaProjects\rule-based-compiler\src\main\RuleGrammar.g4 by ANTLR 4.7
package at.tugraz.ist.compiler.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RuleGrammarParser}.
 */
public interface RuleGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(RuleGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(RuleGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#memory}.
	 * @param ctx the parse tree
	 */
	void enterMemory(RuleGrammarParser.MemoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#memory}.
	 * @param ctx the parse tree
	 */
	void exitMemory(RuleGrammarParser.MemoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(RuleGrammarParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(RuleGrammarParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#r_rules}.
	 * @param ctx the parse tree
	 */
	void enterR_rules(RuleGrammarParser.R_rulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#r_rules}.
	 * @param ctx the parse tree
	 */
	void exitR_rules(RuleGrammarParser.R_rulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#r_rule}.
	 * @param ctx the parse tree
	 */
	void enterR_rule(RuleGrammarParser.R_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#r_rule}.
	 * @param ctx the parse tree
	 */
	void exitR_rule(RuleGrammarParser.R_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#preconditions}.
	 * @param ctx the parse tree
	 */
	void enterPreconditions(RuleGrammarParser.PreconditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#preconditions}.
	 * @param ctx the parse tree
	 */
	void exitPreconditions(RuleGrammarParser.PreconditionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#worldDeletions}.
	 * @param ctx the parse tree
	 */
	void enterWorldDeletions(RuleGrammarParser.WorldDeletionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#worldDeletions}.
	 * @param ctx the parse tree
	 */
	void exitWorldDeletions(RuleGrammarParser.WorldDeletionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#rule_goal}.
	 * @param ctx the parse tree
	 */
	void enterRule_goal(RuleGrammarParser.Rule_goalContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#rule_goal}.
	 * @param ctx the parse tree
	 */
	void exitRule_goal(RuleGrammarParser.Rule_goalContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#alist}.
	 * @param ctx the parse tree
	 */
	void enterAlist(RuleGrammarParser.AlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#alist}.
	 * @param ctx the parse tree
	 */
	void exitAlist(RuleGrammarParser.AlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#alistentry}.
	 * @param ctx the parse tree
	 */
	void enterAlistentry(RuleGrammarParser.AlistentryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#alistentry}.
	 * @param ctx the parse tree
	 */
	void exitAlistentry(RuleGrammarParser.AlistentryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(RuleGrammarParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(RuleGrammarParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SignExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSignExpr(RuleGrammarParser.SignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SignExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSignExpr(RuleGrammarParser.SignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValueExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterValueExpr(RuleGrammarParser.ValueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValueExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitValueExpr(RuleGrammarParser.ValueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnarySignExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnarySignExpr(RuleGrammarParser.UnarySignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnarySignExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnarySignExpr(RuleGrammarParser.UnarySignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulopExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulopExpr(RuleGrammarParser.MulopExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulopExpr}
	 * labeled alternative in {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulopExpr(RuleGrammarParser.MulopExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(RuleGrammarParser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(RuleGrammarParser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#mulop}.
	 * @param ctx the parse tree
	 */
	void enterMulop(RuleGrammarParser.MulopContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#mulop}.
	 * @param ctx the parse tree
	 */
	void exitMulop(RuleGrammarParser.MulopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarValue}
	 * labeled alternative in {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterVarValue(RuleGrammarParser.VarValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarValue}
	 * labeled alternative in {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitVarValue(RuleGrammarParser.VarValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumValue}
	 * labeled alternative in {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNumValue(RuleGrammarParser.NumValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumValue}
	 * labeled alternative in {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNumValue(RuleGrammarParser.NumValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BraceValue}
	 * labeled alternative in {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBraceValue(RuleGrammarParser.BraceValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BraceValue}
	 * labeled alternative in {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBraceValue(RuleGrammarParser.BraceValueContext ctx);
}