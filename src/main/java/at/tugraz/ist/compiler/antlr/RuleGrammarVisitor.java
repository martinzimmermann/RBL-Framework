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
	 * Visit a parse tree produced by {@link RuleGrammarParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFact(RuleGrammarParser.FactContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(RuleGrammarParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#r_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_rule(RuleGrammarParser.R_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#preconditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreconditions(RuleGrammarParser.PreconditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#postconditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostconditions(RuleGrammarParser.PostconditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#postcondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostcondition(RuleGrammarParser.PostconditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#worldAddition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWorldAddition(RuleGrammarParser.WorldAdditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#worldDeletion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWorldDeletion(RuleGrammarParser.WorldDeletionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(RuleGrammarParser.ActionContext ctx);
}