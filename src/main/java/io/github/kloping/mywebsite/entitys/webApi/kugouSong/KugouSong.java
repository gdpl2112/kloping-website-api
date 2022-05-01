package io.github.kloping.mywebsite.entitys.webApi.kugouSong;

/**
 * @author github-kloping
 */
public class KugouSong {
    private Data data;
    private Number err_code;
    private Number status;

    public Data getData() {
        return this.data;
    }

    public KugouSong setData(Data data) {
        this.data = data;
        return this;
    }

    public Number getErr_code() {
        return this.err_code;
    }

    public KugouSong setErr_code(Number err_code) {
        this.err_code = err_code;
        return this;
    }

    public Number getStatus() {
        return this.status;
    }

    public KugouSong setStatus(Number status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "KugouSong{" +
                "data=" + data +
                ", err_code=" + err_code +
                ", status=" + status +
                '}';
    }
}