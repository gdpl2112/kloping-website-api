package io.github.kloping.mywebsite.entitys.ks;

public class KsSearchEn {
    private Variables variables;
    private String query;
    private String operationName;

    public Variables getVariables() {
        return this.variables;
    }

    public KsSearchEn setVariables(Variables variables) {
        this.variables = variables;
        return this;
    }

    public String getQuery() {
        return this.query;
    }

    public KsSearchEn setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getOperationName() {
        return this.operationName;
    }

    public KsSearchEn setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }
}