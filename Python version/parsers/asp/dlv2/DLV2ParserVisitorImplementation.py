from .DLV2Lexer import DLV2Lexer
from .DLV2Parser import DLV2Parser 
from .DLV2ParserVisitor import DLV2ParserVisitor
from antlr4 import PredictionMode
from antlr4.CommonTokenStream import CommonTokenStream
from antlr4.error.ErrorListener import ConsoleErrorListener
from antlr4.error.Errors import RecognitionException
from antlr4.error.ErrorStrategy import BailErrorStrategy, DefaultErrorStrategy
from antlr4.InputStream import InputStream

class DLV2ParserVisitorImplementation(DLV2ParserVisitor):
    def __init__(self, answerSets):
        self._answerSets = answerSets
        
    def visitAnswer_set(self, ctx):
        self._answerSets.addAnswerSet()
        
        if ctx.cost() is not None and not ctx.cost().isEmpty():
            firstCost = ctx.cost().COST_LABEL().getText().split(' ')[1].split('@')
            
            self._answerSets.storeCost(firstCost[1], firstCost[0])
        
        return self.visitChildren(ctx)
    
    def visitLevel(self, ctx):
        self._answerSets.storeCost(ctx.INTEGER(1).getText(), ctx.INTEGER(0).getText())
    
    def visitPredicate_atom(self, ctx):
        self._answerSets.storeAtom(ctx.getText())
    
    @staticmethod
    def parse(answerSets, dlv2Output, two_stageParsing):
        tokens = CommonTokenStream(DLV2Lexer(InputStream(dlv2Output)))
        parser = DLV2Parser(tokens)
        visitor = DLV2ParserVisitorImplementation(answerSets)
        
        if not two_stageParsing:
            visitor.visit(parser.output())
            
            return
        
        parser._interp.predictionMode = PredictionMode.SLL
        parser.removeErrorListeners()
        parser._errHandler = BailErrorStrategy()
        
        try:
            visitor.visit(parser.output())
        except RuntimeError as exception:
            if isinstance(exception, RecognitionException):
                tokens.seek(0)
                parser.addErrorListener(ConsoleErrorListener.INSTANCE)
                parser._errHandler = DefaultErrorStrategy()
                parser._interp.predictionMode = PredictionMode.LL
                visitor.visit(parser.output())