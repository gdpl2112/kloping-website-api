$("body").append(`

<div class="modal fade" id="agreement-tips" tabindex="-1" role="dialog" aria-labelledby="agreement-tips-title"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" id="modal-content-id">
            <div class="modal-body">
                1. 该系统自带了防止负能量信息传出的屏障,但不完全保证无漏洞,在未知情况下使用该系统传播负能量言论/信息/谣言 而造成的 法律责任 该系统概不负责
                <br>
                2. 若该系统侵犯了您的版权/权益 请速度联系作者 将尽快处理
                <br>
                3. 该系统不会通过任何方式主动或强迫对您收费
                <br>
                4. 一切收费都在您的同意之下进行,即当您通过任何方式支付时即视为同意
                <br>
                5. 在您与该系统达成交易之后,所有费用改不退还
                <br>
                6. 在为确认对方身份的情况下造成的钱财损失该系统概不负责
                <br>
                7. 该系统为您提供免费的服务,且不会对您进行任何强制性的收费,若该系统因您而受到了 严重影响/严重损坏 您有责任对其系统负责
                <br>
                8. 由该系统引起的个人或群体纠纷或因其造成的财产损失,法律责任,该系统概不负责
                <br>
                9. 使用该系统默认视为同意该协议
                <br>
                --- 最后修改时间 2022/1/21
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close btn btn-primary">收到</button>
            </div>
        </div>
    </div>
</div>

<nav id="hn0" class="fixed-top navbar navbar-expand-lg navbar-light">
    <div class="navbar-brand navbar-header">
        <a href="/">
            <img style="opacity: 0.86;max-width: 50px; border-radius: 999px"
                 class="navbar-brand" src="/icon.jpg" alt="qq"> kloping's web
        </a>
    </div>
     <button class="navbar-toggler" type="button" data-toggle="collapse" 
     data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
     aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/api">API</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#"
                 id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                    关于
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" target="_blank" href="//wpa.qq.com/msgrd?v=3&amp;uin=3474006766&amp;site=qq&amp;menu=yes">
                        <img style="opacity: 0.86;max-width: 35px"  alt="qq" loading="lazy" src="https://s.nmxc.ltd/sakurairo_vision/@2.6/display_icon/sora/qq.png">
                        QQ
                    </a>
                    <a class="dropdown-item" target="_blank" href="http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=Bk2IrDI969mNd-R5G30Ridw9yeou0Mqd&authKey=TCtcOWnwzFw3NfZn%2F9XAXtRmPquOdFbRQmAAFFnf18WXusQBWqt8TFyoJpVVktj2&noverify=0&group_code=278681553">
                        <img style="opacity: 0.86;max-width: 35px"  alt="qq" loading="lazy" src="https://s.nmxc.ltd/sakurairo_vision/@2.6/display_icon/sora/qq.png">
                        QQ GROUP
                    </a>
                    <a class="dropdown-item" target="_blank" href="https://github.com/kloping">
                        <img style="opacity: 0.86;max-width: 35px"  alt="github" loading="lazy" src="https://s.nmxc.ltd/sakurairo_vision/@2.6/display_icon/sora/github.png">
                        github
                    </a> 
                    <a class="dropdown-item" target="_blank" href="https://github.com/gdpl2112">
                        <img style="opacity: 0.86;max-width: 35px"  alt="github" loading="lazy" src="https://s.nmxc.ltd/sakurairo_vision/@2.6/display_icon/sora/github.png">
                        github organization
                    </a>
                </div>
            </li>
             <li class="nav-item mr-sm-2" style="margin-right: 8px">
                <a href="" id="jump-fl" class="nav-link">友链</a>
            </li>
            <li class="nav-item">
                <a style="color: #91f5c3" type="button" 
                class="nav-link btn btn-primary mr-sm-2" 
                data-toggle="modal" data-target="#agreement-tips">使用协议</a>
            </li>
        </ul>
        <div class="form-inline my-2 my-lg-0">
            <a href="/v0.html" class="mr-sm-2 my-2 my-sm-0" style="margin-right: 8px">
                <button class="btn btn-outline-success" type="button">个人中心</button>
            </a>
            <a href="/upload.html" class=" my-2 my-sm-0">
                <button class="btn btn-outline-success" type="button">发布帖子</button>
            </a>
        </div>
    </div>
</nav>

<script>
    $("#jump-fl").click(function (){
        $("html, body").animate({
            scrollTop: $("#f-link-d0").offset().top }, {duration: 500,easing: "swing"});
            return false;
    })
</script>
<style>
    #hn0{
        transition: all 1s ease !important;
        border: 1.5px solid transparent;
        background-color: rgba(255,255,255,.0);
        border-radius: 7px;
        animation: normal1 .3s linear forwards;
    }
    #hn0:hover{
        animation: hover1 .3s linear forwards;
        background-color: rgba(255,255,255, .9)
    }
    
        
    @keyframes normal1 {
        0% {
            transform: scale(1);
        }
    
        100% {
            transform: scale(0.98);
        }
    }
    @keyframes hover1 {
        0% {
            transform: scale(0.98);
        }
    
        100% {
            transform: scale(1.00);
        }
    }
    
</style>

`
);


