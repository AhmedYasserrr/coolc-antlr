// Generated from D:/ALL/compilers/project/coolc-antlr/Code Generation/grammer/Parser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code start}
	 * labeled alternative in {@link ParserParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ParserParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classdef}
	 * labeled alternative in {@link ParserParser#classDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(ParserParser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(ParserParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(ParserParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(ParserParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#parm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParm(ParserParser.ParmContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(ParserParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parexpr}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParexpr(ParserParser.ParexprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(ParserParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(ParserParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code void}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoid(ParserParser.VoidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invert}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvert(ParserParser.InvertContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(ParserParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(ParserParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(ParserParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lteq}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLteq(ParserParser.LteqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code num}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(ParserParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(ParserParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(ParserParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(ParserParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(ParserParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(ParserParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code block}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ParserParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code let}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(ParserParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newobject}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewobject(ParserParser.NewobjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longcall}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongcall(ParserParser.LongcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(ParserParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code case}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase(ParserParser.CaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(ParserParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shortcall}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortcall(ParserParser.ShortcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code object}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(ParserParser.ObjectContext ctx);
}