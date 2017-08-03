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
}