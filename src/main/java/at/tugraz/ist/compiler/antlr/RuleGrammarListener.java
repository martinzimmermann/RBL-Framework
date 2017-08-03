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
	 * Enter a parse tree produced by {@link RuleGrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(RuleGrammarParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(RuleGrammarParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#predicates}.
	 * @param ctx the parse tree
	 */
	void enterPredicates(RuleGrammarParser.PredicatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#predicates}.
	 * @param ctx the parse tree
	 */
	void exitPredicates(RuleGrammarParser.PredicatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleGrammarParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(RuleGrammarParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleGrammarParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(RuleGrammarParser.GoalContext ctx);
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
}