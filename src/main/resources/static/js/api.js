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
            window.open("ApiDetail.html?id=" + i)
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

function load() {
    setTimeout(function () {
        let colors = ["#D2343499", "#34D2D299", "#D95AC799", "#5AD97399"];
        let eee = document.getElementsByClassName("apiE");
        console.log(eee)
        let len = eee.length;
        console.log(len)
        for (let i = 0; i < len; i++) {
            let e = $(eee[i]);
            let n = Math.trunc(Math.random() * colors.length);
            e.css("background-color", colors[n])
            e.css("color", "#000000");
        }
        let sc1 = document.createElement("script")
        sc1.src = "/js/lib/dzzui.js"
        document.getElementsByTagName("body")[0].appendChild(sc1)
    }, 1000)
}