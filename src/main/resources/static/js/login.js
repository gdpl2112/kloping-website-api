let u1 = location.href
let i1 = u1.indexOf("=")
if (i1 > 0) {
    let e = u1.substr(i1 + 1);
    let e0 = $("#login-tips")
    switch (e) {
        case "error":
            $("#tips-content").html("登录失败,QQ号或验证码错误,请重试")
            $('#tipsModal').modal('show')
            break
    }
}
let vm1 = new Vue({
    el: "#login_form",
    created: function () {

    }, data: {
        k0: true
    }, methods: {
        requestCode() {
            if (!vm1.k0) return
            let qid0 = $("#qid")
            axios.get("/req0?qid=" + qid0.val()).then(function (response) {
                $("#tips-content").html(response.data)
                $('#tipsModal').modal('show')
            }).catch(function (err) {
                alert(err);
            })
            vm1.k0 = false
            let sc = $("#send_cap")
            sc.addClass("disabled")
            let time = 60;
            let si = setInterval(function () {
                time--;
                sc.html(time + "秒");
                if (time === 0) {
                    sc.remove("disabled")
                    clearInterval(si)
                    vm1.k0 = true
                    sc.html("重新发送");
                }
            }, 1000);
        }
    }
});
