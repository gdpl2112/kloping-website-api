let u1 = location.href
let i1 = u1.indexOf("=")
if (i1 > 0) {
    let e = u1.substr(i1 + 1);
    let e0 = $("#login-tips")
    switch (e) {
        case "error":
            $("#tips-content").html("登录失败,账号或密码错误,请重试")
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
        gotoReg() {
            location.href = "/register.html"
        },
        gotoGithub() {
            location.href = "https://github.com/login/oauth/authorize?client_id=6316ca5eff5a1c74c2f3&redirect_uri=http://localhost/github/uauth&scope=user&state=1"
        }
    }
});
