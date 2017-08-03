// Generated from C:\Users\mzimmerm\IdeaProjects\rule-based-compiler\src\main\RuleGrammar.g4 by ANTLR 4.7
package at.tugraz.ist.compiler.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RuleGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RuleGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(RuleGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#memory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemory(RuleGrammarParser.MemoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(RuleGrammarParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(RuleGrammarParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#predicates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicates(RuleGrammarParser.PredicatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(RuleGrammarParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#alist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlist(RuleGrammarParser.AlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#alistentry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlistentry(RuleGrammarParser.AlistentryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(RuleGrammarParser.ActionContext ctx);
}