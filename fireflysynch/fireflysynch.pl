%====================================================================================
% fireflysynch description   
%====================================================================================
event( changeMode, changeMode(Mode,NewPeriod) ).
event( obstacleClose, obstacleClose(Distance) ).
event( obstacleFar, obstacleFar(Distance) ).
%====================================================================================
context(ctxfirefly, "localhost",  "TCP", "8010").
 qactor( firefly, ctxfirefly, "it.unibo.firefly.Firefly").
 static(firefly).
  qactor( fireflysystem, ctxfirefly, "it.unibo.fireflysystem.Fireflysystem").
 static(fireflysystem).
  qactor( sonarmock, ctxfirefly, "it.unibo.sonarmock.Sonarmock").
 static(sonarmock).
