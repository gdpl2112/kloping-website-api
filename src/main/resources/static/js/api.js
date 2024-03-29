let vm1 = new Vue({
    el: "#api_list",
    created: function () {
        axios.get("/api/getApiList").then(function (response) {
            vm1.list = response.data;
            load()
        }).catch(function (err) {
            alert(err);
        })
    }, data: {
        list: []
    }, methods: {
        mDetail: function (i) {
            window.open("ApiDetail.html?name=" + i)
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
    "rgba(243,213,188,0.52)",
    "rgba(191,241,211,0.52)",
    "rgba(248,218,211,0.52)",
    "rgba(241,185,185,0.52)",
    "rgba(187,185,245,0.52)"
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