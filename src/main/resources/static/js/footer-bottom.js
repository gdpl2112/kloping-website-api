$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

let host0 = "";
axios.get("/getHost?url=" + document.location).then(function (response) {
    host0 = response.data
    $("body").append(`
<div style="margin-right: 10px;margin-left: 10px; margin-top: 35px;background-color: rgba(0,6,33,0.4)" role="alert" class="opacity-50 alert alert-dark">
    <center style="opacity: 100%;color: white">
        该网页由作者单人编写 <br>
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
        <a style="color: #f2d2f6" href="//kloping.top" class="alert-link">若生er,WebSite</a>&nbsp;&nbsp;
    </center>
</div>
    `);
}).catch(function (err) {
    alert(err);
})


