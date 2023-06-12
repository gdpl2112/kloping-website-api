$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

let host0 = "";
axios.get("/getHost?url=" + document.location).then(function (response) {
    host0 = response.data
    $("body").append(`

<div style="margin-right: 10px;margin-left: 10px; margin-top: 35px;background-color: rgba(0,6,33,0.4)"
     role="alert" class="opacity-50 alert alert-dark">
    <center style="opacity: 100%;color: white">
        欢迎您的访问 <br>
        ` + host0 + `版权所有 Power by <br>
        <a style="color: #93c3da" href="//wpa.qq.com/msgrd?v=3&amp;uin=3474006766&amp;site=qq&amp;menu=yes"
           class="alert-link">若生</a>
        <a style="color: #93c3da" href="//github.com/kloping" class="alert-link">kloping</a>
    </center>
    <br>
    <center>
        <h5 style='color: white' data-toggle="tooltip" data-placement="top" title="添加友链,请联系作者">
            友情链接
        </h5>
        <div class="container justify-content-center">
            <div class="row justify-content-center">
                <a style="color: #f2d2f6" href="//kloping.top" class="alert-link col-3">
                    <img class="yl-img" src="/icon.jpg" alt=""/>
                    SLEF
                </a>
                <a style="color: #83b7ff" href="https://rainchan.win/" class="alert-link">
                    <img class="yl-img"
                         src="https://gravatar.loli.net/avatar/f7e8af6d341b76ad3de6757a8f86f2b4?d=mp&v=1.3.10" alt=""/>
                    RainChan的小博客
                </a>
            </div>
        </div>
    </center>
</div>

<style>
    .yl-img {
        max-width: 25px;
        transition: all 0.8s ease !important;
        border-radius: 999px;
        rotate: 0turn;
    }

    .yl-img:hover {
        rotate: 1turn;
        max-width: 30px;
    }
</style>
    `);
}).catch(function (err) {
    alert(err);
})


