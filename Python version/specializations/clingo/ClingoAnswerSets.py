from languages.asp.AnswerSets import AnswerSets
from parsers.asp.ASPSolversParser import ASPSolversParser

class ClingoAnswerSets(AnswerSets):
    """Represents Clingo's answersets"""
    
    def __init__(self, out, err=None):
        super(ClingoAnswerSets, self).__init__(out, err)
        
    def _parse(self):
        ASPSolversParser.parseClingo(self, self._output, True)