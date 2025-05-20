// Generated from D:/ALL/compilers/project/coolc-antlr/Code Generation/grammer/Parser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ParserParser}.
 */
public interface ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code start}
	 * labeled alternative in {@link ParserParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterStart(ParserParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by the {@code start}
	 * labeled alternative in {@link ParserParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitStart(ParserParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classdef}
	 * labeled alternative in {@link ParserParser#classDefine}.
	 * @param ctx the parse tree
	 */
	void enterClassdef(ParserParser.ClassdefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classdef}
	 * labeled alternative in {@link ParserParser#classDefine}.
	 * @param ctx the parse tree
	 */
	void exitClassdef(ParserParser.ClassdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(ParserParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(ParserParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#attr}.
	 * @param ctx the parse tree
	 */
	void enterAttr(ParserParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#attr}.
	 * @param ctx the parse tree
	 */
	void exitAttr(ParserParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(ParserParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(ParserParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#parm}.
	 * @param ctx the parse tree
	 */
	void enterParm(ParserParser.ParmContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#parm}.
	 * @param ctx the parse tree
	 */
	void exitParm(ParserParser.ParmContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(ParserParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(ParserParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parexpr}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParexpr(ParserParser.ParexprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parexpr}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParexpr(ParserParser.ParexprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code add}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd(ParserParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd(ParserParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sub}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSub(ParserParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSub(ParserParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code void}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVoid(ParserParser.VoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code void}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVoid(ParserParser.VoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invert}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInvert(ParserParser.InvertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invert}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInvert(ParserParser.InvertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(ParserParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(ParserParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBool(ParserParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBool(ParserParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul(ParserParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul(ParserParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lteq}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLteq(ParserParser.LteqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lteq}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLteq(ParserParser.LteqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code num}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNum(ParserParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code num}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNum(ParserParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lt}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLt(ParserParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLt(ParserParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhile(ParserParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhile(ParserParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEq(ParserParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eq}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEq(ParserParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code div}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiv(ParserParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code div}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiv(ParserParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(ParserParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(ParserParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ParserParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ParserParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code let}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLet(ParserParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLet(ParserParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newobject}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewobject(ParserParser.NewobjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newobject}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewobject(ParserParser.NewobjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longcall}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLongcall(ParserParser.LongcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longcall}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLongcall(ParserParser.LongcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf(ParserParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf(ParserParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code case}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCase(ParserParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code case}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCase(ParserParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign(ParserParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign(ParserParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shortcall}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterShortcall(ParserParser.ShortcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shortcall}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitShortcall(ParserParser.ShortcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code object}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterObject(ParserParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code object}
	 * labeled alternative in {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitObject(ParserParser.ObjectContext ctx);
}