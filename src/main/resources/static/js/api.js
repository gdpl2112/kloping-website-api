let vm1 = new Vue({
    el: "#api_list",
    created: function () {
        axios.get("/api/list").then(function (response) {
            vm1.list = response.data;
            load()
        }).catch(function (err) {
            alert(err);
        })
    }, data: {
        list: []
    }, methods: {
        mDetail: function (i) {
            window.open("apid.html?name=" + i)
        }
    }, mounted() {
        load();
    }
    , watch: {
        analysisRank: function () {
            load()
        }
    }
});

let colors = [
    "rgba(239,151,89,0.67)",
    "rgba(99,241,156,0.67)",
    "rgba(229,234,88,0.67)",
    "rgba(253,86,170,0.67)",
    "rgba(85,202,239,0.67)"
];

function load() {
    setTimeout(function () {
        let eee = document.getElementsByClassName("apiE");
        console.log(eee)
        let len = eee.length;
        console.log(len)
        for (let i = 0; i < len; i++) {
            let e = $(eee[i]);
            let n = Math.trunc(Math.random() * colors.length);
            e.css("background", colors[n])
            e.css("color", "#000000");
        }
        let sc1 = document.createElement("script")
        sc1.src = "https://api.vvhan.com/api/yinghua"
        document.getElementsByTagName("body")[0].appendChild(sc1)

    }, 1000)
}