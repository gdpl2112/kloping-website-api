package io.github.kloping.mywebsite.entitys.medias;

public class PositionM {
    private B result;
    private Number status;

    public B getResult(){
        return this.result;
    }

    public PositionM setResult(B result) {
        this.result = result;
        return this;
    }

    public Number getStatus(){
        return this.status;
    }

    public PositionM setStatus(Number status) {
        this.status = status;
        return this;
    }

    public static class B {
        private String level;
        private Number confidence;
        private C location;
        private Number precise;
        private Number comprehension;

        public String getLevel(){
            return this.level;
        }

        public B setLevel(String level) {
            this.level = level;
            return this;
        }

        public Number getConfidence(){
            return this.confidence;
        }

        public B setConfidence(Number confidence) {
            this.confidence = confidence;
            return this;
        }

        public C getLocation(){
            return this.location;
        }

        public B setLocation(C location) {
            this.location = location;
            return this;
        }

        public Number getPrecise(){
            return this.precise;
        }

        public B setPrecise(Number precise) {
            this.precise = precise;
            return this;
        }

        public Number getComprehension(){
            return this.comprehension;
        }

        public B setComprehension(Number comprehension) {
            this.comprehension = comprehension;
            return this;
        }

        @Override
        public String toString() {
            return "B{" +
                    "level='" + level + '\'' +
                    ", confidence=" + confidence +
                    ", location=" + location +
                    ", precise=" + precise +
                    ", comprehension=" + comprehension +
                    '}';
        }
    }

    public static class C {
        private Number lng;
        private Number lat;

        public Number getLng(){
            return this.lng;
        }

        public C setLng(Number lng) {
            this.lng = lng;
            return this;
        }

        public Number getLat(){
            return this.lat;
        }

        public C setLat(Number lat) {
            this.lat = lat;
            return this;
        }

        @Override
        public String toString() {
            return "C{" +
                    "lng=" + lng +
                    ", lat=" + lat +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PositionM{" +
                "result=" + result +
                ", status=" + status +
                '}';
    }
}