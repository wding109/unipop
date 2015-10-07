package org.unipop.controller.elasticsearch.star;

import org.apache.tinkerpop.gremlin.process.traversal.step.util.HasContainer;
import org.apache.tinkerpop.gremlin.structure.*;
import org.unipop.controller.elasticsearch.helpers.*;
import org.unipop.controller.Predicates;
import org.unipop.controller.elasticsearch.vertex.ElasticVertex;
import org.unipop.structure.*;

import java.util.*;

public class ElasticStarVertex extends ElasticVertex {
    private Set<InnerEdge> innerEdges;

    public ElasticStarVertex(final Object id, final String label, Object[] keyValues, UniGraph graph, LazyGetter lazyGetter, ElasticMutations elasticMutations, String indexName) {
        super(id, label, keyValues, graph, lazyGetter, elasticMutations, indexName);
        innerEdges = new HashSet<>();
    }

    @Override
    public Iterator<Edge> cachedEdges(Direction direction, String[] edgeLabels, Predicates predicates) {
        ArrayList<Edge> edges = new ArrayList<>();
        innerEdges.forEach(edge -> {
            EdgeMapping mapping = edge.getMapping();
            if(mapping.getDirection().equals(direction) &&
                    (edgeLabels.length == 0 || StarController.contains(edgeLabels, mapping.getLabel()))) {

                // Test predicates on inner edge
                boolean passed = true;
                for (HasContainer hasContainer : predicates.hasContainers) {
                    if (!hasContainer.test(edge)) {
                        passed = false;
                    }
                }
                if (passed) {
                    edges.add(edge);
                }
            }
        });

        if(edges.size() > 0) return edges.iterator();
        else return null;
    }
}
