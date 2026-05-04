%====================================================================================
% firefly description   
%====================================================================================
event( flash, flash(Time) ).
%====================================================================================
context(ctxlucciolelocale, "localhost",  "TCP", "8010").
 qactor( separatore, ctxlucciolelocale, "it.unibo.separatore.Separatore").
 static(separatore).
  qactor( lucciola0, ctxlucciolelocale, "it.unibo.lucciola0.Lucciola0").
 static(lucciola0).
  qactor( lucciola1, ctxlucciolelocale, "it.unibo.lucciola1.Lucciola1").
 static(lucciola1).
  qactor( lucciola2, ctxlucciolelocale, "it.unibo.lucciola2.Lucciola2").
 static(lucciola2).
