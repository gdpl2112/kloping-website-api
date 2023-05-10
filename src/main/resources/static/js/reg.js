const paramsStr = window.location.search
const params = new URLSearchParams(paramsStr)
let tips = params.get('tips')
if (tips != null && tips !== "") {
    $("#tips-content").html(tips)
    $('#tipsModal').modal('show')
}

let vm1 = new Vue({
    el: "#reg_form", created: function () {

    }, data: {
        ke: true,
        rq: ""
    }, methods: {
        requestCode() {
            if (!vm1.ke) return
            let eid0 = $("#eid")
            axios.get("/req0?eid=" + eid0.val()).then(function (response) {
                $("#tips-content").html(response.data)
                $('#tipsModal').modal('show')
            }).catch(function (err) {
                alert(err);
            })
            vm1.ke = false
            let sc = $("#send_cap")
            sc.addClass("disabled")
            let time = 60;
            let si = setInterval(function () {
                time--;
                sc.html(time + "秒");
                if (time === 0) {
                    sc.removeClass("disabled")
                    clearInterval(si)
                    vm1.ke = true
                    sc.html("重新发送");
                }
            }, 1000);
        }
        , sub() {
            let eid = $("#eid").val()
            let qid = $("#qid").val()
            let name = $("#name").val()
            let pwd = $("#pwd").val()
            let code = $("#code").val()
            let data = new FormData()
            data.append("eid", eid)
            data.append("qid", qid)
            data.append("pwd", pwd)
            data.append("name", name)
            data.append("code", code)
            axios.post("/reg", data).then(function (response) {
                vm1.rq = response.data
                $("#tips-content").html(response.data)
                $('#tipsModal').modal('show')
            }).catch(function (err) {
                alert(err);
            })
        }
    }
});

$("#tips-but").click(
    function () {
        if (vm1.rq === "注册成功") {
            location.href = "/login.html"
        }
    }
)