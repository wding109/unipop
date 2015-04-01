package com.tinkerpop.gremlin.elastic.elasticservice;

import com.tinkerpop.gremlin.structure.Element;
import org.apache.commons.configuration.Configuration;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilder;

import java.io.IOException;


public interface SchemaProvider {
    void init(Client client, Configuration configuration) throws IOException;
    void close();

    AddElementResult addElement(String label, Object idValue, ElasticService.Type type, Object[] keyValues);
    String getIndex(Element element);
    String getIndex(String label, Object id);
    SearchResult search(FilterBuilder filter, ElasticService.Type type, String[] labels);

    public interface AddElementResult{
        public String getIndex() ;
        public Object[] getKeyValues();
        String getId();
    }

    public interface SearchResult{
        public String[] getIndices();
        public FilterBuilder getFilter();
    }
}
