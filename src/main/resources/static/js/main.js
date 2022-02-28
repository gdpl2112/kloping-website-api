let maxSize = 200;
let time = 150;
let flake = $('<div></div>').css('position', 'absolute').html("*");
$(function () {
    setInterval(function () {
            let body = $("body");
            let width = $(document).width();
            let height = $(document).height();
            let flakeSize = maxSize * Math.random();
            let startLeft = Math.random() * width - flakeSize;
            let endLeft = Math.random() * width - flakeSize;
            let flakeOpacity = 0.3 + Math.random() * 0.3;
            let endFlakeOpacity = 0.5 + Math.random() * 0.3;
            let durationTime = 2400;
            let t1 = height - flakeSize * 2;
            flake.clone().appendTo(body).css({
                'left': startLeft,
                'font-size': flakeSize,
                'opacity': flakeOpacity,
                'color': "rgba(255,167,243,0.6)",
                'top': "-50px"
            }).animate({
                    'left': endLeft,
                    'opacity': endFlakeOpacity,
                    'top': t1
                },
                durationTime,
                function () {
                    $(this).remove();
                });
        },
        time);
});