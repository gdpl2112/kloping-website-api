$("body").append(`
<nav style='opacity: 0.82;border: 1px solid #04e7ff;' class="fixed-top navbar navbar-expand-lg navbar-light bg-light">
    <div class=" navbar-brand navbar-header">
        <img style="max-width: 50px" class="navbar-brand" src="//q1.qlogo.cn/g?b=qq&amp;nk=3474006766&amp;s=640" alt="qq">
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand text-success" href="/">首页</a>
    <div class="collapse navbar-collapse" id="navbarContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/api">API</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                    关于我们
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" target="_blank" href="https://jq.qq.com/?_wv=1027&amp;k=clP6D5ih">加入群聊</a>
                    <a class="dropdown-item" target="_blank" href="//wpa.qq.com/msgrd?v=3&amp;uin=3474006766&amp;site=qq&amp;menu=yes">联系作者</a>
                    <a class="dropdown-item" target="_blank" href="//github.com/kloping">github主页</a>
                </div>
            </li>
            <li class="nav-item">
                <a type="button" class="nav-link btn btn-info" data-toggle="modal" data-target="#agreement-tips">使用协议</a>
            </li>
        </ul>
        <div class="form-inline my-2 my-lg-0">
            <a href="/upload.html"> 
            <button class="btn btn-outline-success my-2 my-sm-0" type="button">发布帖子</button>
            </a>       
        </div>
    </div>
</nav>
`
);


