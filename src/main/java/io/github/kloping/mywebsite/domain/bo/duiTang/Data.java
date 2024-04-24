package io.github.kloping.mywebsite.domain.bo.duiTang;

public class Data {
    private Number total;
    private String next_start;
    private Object_list[] object_list;
    private Number more;
    private Number limit;
    private String after;

    public Number getTotal() {
        return this.total;
    }

    public Data setTotal(Number total) {
        this.total = total;
        return this;
    }

    public String getNext_start() {
        return this.next_start;
    }

    public Data setNext_start(String next_start) {
        this.next_start = next_start;
        return this;
    }

    public Object_list[] getObject_list() {
        return this.object_list;
    }

    public Data setObject_list(Object_list[] object_list) {
        this.object_list = object_list;
        return this;
    }

    public Number getMore() {
        return this.more;
    }

    public Data setMore(Number more) {
        this.more = more;
        return this;
    }

    public Number getLimit() {
        return this.limit;
    }

    public Data setLimit(Number limit) {
        this.limit = limit;
        return this;
    }

    public String getAfter() {
        return this.after;
    }

    public Data setAfter(String after) {
        this.after = after;
        return this;
    }
}