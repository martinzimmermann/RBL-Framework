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
	 * Enter a parse tree produced by {@link RuleGrammarParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterFact(RuleGrammarParser.FactContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitFact(RuleGrammarParser.FactContext ctx);
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
	 * Enter a parse tree produced by {@link RuleGrammarParser#postconditions}.
	 * @param ctx the parse tree
	 */
	void enterPostconditions(RuleGrammarParser.PostconditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#postconditions}.
	 * @param ctx the parse tree
	 */
	void exitPostconditions(RuleGrammarParser.PostconditionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#postcondition}.
	 * @param ctx the parse tree
	 */
	void enterPostcondition(RuleGrammarParser.PostconditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#postcondition}.
	 * @param ctx the parse tree
	 */
	void exitPostcondition(RuleGrammarParser.PostconditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#worldAddition}.
	 * @param ctx the parse tree
	 */
	void enterWorldAddition(RuleGrammarParser.WorldAdditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#worldAddition}.
	 * @param ctx the parse tree
	 */
	void exitWorldAddition(RuleGrammarParser.WorldAdditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#worldDeletion}.
	 * @param ctx the parse tree
	 */
	void enterWorldDeletion(RuleGrammarParser.WorldDeletionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#worldDeletion}.
	 * @param ctx the parse tree
	 */
	void exitWorldDeletion(RuleGrammarParser.WorldDeletionContext ctx);
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
}