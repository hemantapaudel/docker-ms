package com.docker.ms;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONIo;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONMapper;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONVersion;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.shaded.jackson.core.JsonProcessingException;
import org.apache.tinkerpop.shaded.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Docker Containerization";
    }

    @GetMapping("/google")
    public String external() {
        return new RestTemplate().getForObject("https://www.google.com/",String.class);
    }

    @GetMapping("/test1")
    public Object test1(){
        Graph g = TinkerGraph.open();

        Vertex marko = g.addVertex("name","marko","age",29);
        Vertex lop = g.addVertex("name","lop","lang","java");
        marko.addEdge("created",lop,"weight",0.6d);

        Object ver =  g.traversal().V().has("name","marko").out("created").values("name");
        return ver;
    }

    @GetMapping("/test2")
    public String graphIndex() throws JsonProcessingException {
        TinkerGraph g = TinkerGraph.open();
        g.createIndex("userId", Vertex.class);
        GraphTraversalSource tg = g.traversal();
        Vertex v1 = getOrCreate(tg,"1", "Ramesh");
        Vertex v2 = getOrCreate(tg,"2", "Ram");

        v1.addEdge("reportsTo", v2);

        ObjectMapper objectMapper =
                GraphSONMapper.build().version(GraphSONVersion.V3_0).create().createMapper();
        List<Vertex> results = tg.V().has("userId", "1").out("reportsTo").toList();

        List<Object> resList = new ArrayList<>();
        results.stream().forEach(list -> resList.add(list));
        String data = objectMapper.writeValueAsString(resList);
        return data;
    }

    private Vertex getOrCreate(GraphTraversalSource tg, String userId, String name){
        GraphTraversal<Vertex, Vertex> t = tg.V().has("user", "userId", userId);
        return t.hasNext() ? t.next() : tg.addV("user")
                .property("userId", userId)
                .property("name",name)
                .next();
    }
}