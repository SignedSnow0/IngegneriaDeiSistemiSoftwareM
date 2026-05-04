### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('fireflyArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxlucciolelocale', graph_attr=nodeattr):
          separatore=Custom('separatore','./qakicons/symActorWithobjSmall.png')
          lucciola0=Custom('lucciola0','./qakicons/symActorWithobjSmall.png')
          lucciola1=Custom('lucciola1','./qakicons/symActorWithobjSmall.png')
          lucciola2=Custom('lucciola2','./qakicons/symActorWithobjSmall.png')
     lucciola0 >> Edge( label='flash', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     lucciola1 >> Edge( label='flash', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     lucciola2 >> Edge( label='flash', **eventedgeattr, decorate='true', fontcolor='red') >> sys
diag
