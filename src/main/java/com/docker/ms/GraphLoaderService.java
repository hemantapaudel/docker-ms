package com.docker.ms;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

public class GraphLoaderService {

    void main(){
        Graph g = TinkerGraph.open();

        Vertex marko = g.addVertex("name","marko","age",29);
        Vertex lop = g.addVertex("name","lop","lang","java");
        marko.addEdge("created",lop,"weight",0.6d);
        Object ver =  g.traversal().V().has("name","marko").out("created").values("name").next();
    }
}
