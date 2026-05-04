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
with Diagram('fireflysynchArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxfirefly', graph_attr=nodeattr):
          firefly=Custom('firefly','./qakicons/symActorWithobjSmall.png')
          fireflysystem=Custom('fireflysystem','./qakicons/symActorWithobjSmall.png')
          sonarmock=Custom('sonarmock','./qakicons/symActorWithobjSmall.png')
     sys >> Edge( label='changeMode', **evattr, decorate='true', fontcolor='darkgreen') >> firefly
     sys >> Edge( label='obstacleClose', **evattr, decorate='true', fontcolor='darkgreen') >> fireflysystem
     sys >> Edge( label='obstacleFar', **evattr, decorate='true', fontcolor='darkgreen') >> fireflysystem
     fireflysystem >> Edge( label='changeMode', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarmock >> Edge( label='obstacleClose', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarmock >> Edge( label='obstacleFar', **eventedgeattr, decorate='true', fontcolor='red') >> sys
diag
