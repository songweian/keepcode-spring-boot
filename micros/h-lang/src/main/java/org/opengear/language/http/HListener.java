// Generated from H.g4 by ANTLR 4.9.2
package org.opengear.language.http;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HParser}.
 */
public interface HListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(HParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(HParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(HParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(HParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HParser#containsCheck}.
	 * @param ctx the parse tree
	 */
	void enterContainsCheck(HParser.ContainsCheckContext ctx);
	/**
	 * Exit a parse tree produced by {@link HParser#containsCheck}.
	 * @param ctx the parse tree
	 */
	void exitContainsCheck(HParser.ContainsCheckContext ctx);
}