package it.unical.mat.embasp.languages.asp.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ASPGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ASPGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ASPGrammarParser#output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(ASPGrammarParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by {@link ASPGrammarParser#predicate_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom(ASPGrammarParser.Predicate_atomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SymbolicTerm}
	 * labeled alternative in {@link ASPGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolicTerm(ASPGrammarParser.SymbolicTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerTerm}
	 * labeled alternative in {@link ASPGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerTerm(ASPGrammarParser.IntegerTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionalTerm}
	 * labeled alternative in {@link ASPGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalTerm(ASPGrammarParser.FunctionalTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringTerm}
	 * labeled alternative in {@link ASPGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringTerm(ASPGrammarParser.StringTermContext ctx);
}