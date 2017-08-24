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
	 * Visit a parse tree produced by {@link RuleGrammarParser#r_rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_rules(RuleGrammarParser.R_rulesContext ctx);
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
	 * Visit a parse tree produced by {@link RuleGrammarParser#worldDeletions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWorldDeletions(RuleGrammarParser.WorldDeletionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#rule_goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule_goal(RuleGrammarParser.Rule_goalContext ctx);
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
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RuleGrammarParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(RuleGrammarParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#mulop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulop(RuleGrammarParser.MulopContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuleGrammarParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(RuleGrammarParser.ValueContext ctx);
}