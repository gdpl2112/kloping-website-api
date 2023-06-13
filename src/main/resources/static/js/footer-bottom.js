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
        <div id="f-link-d0" class="container justify-content-center">
            <div class="row justify-content-center">
                <a v-for="e in arr" :style="'color:'+e.color" :href="e.url" class="alert-link col-3">
                    <img class="yl-img" :src="e.icon" alt=""/>
                    {{e.name}}
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

    let vm1 = new Vue({
        el: "#f-link-d0",
        created: function () {
            axios.get("/flinks").then(function (response) {
                vm1.arr = response.data
            }).catch(function (err) {
                alert(err);
            })
        }, data: {
            arr: []
        }, methods: {}
    });

}).catch(function (err) {
    alert(err);
})
