// <audio style="display: none;" autoPlay="autoplay" loop="loop" preload="auto" controls="controls">
//     <source src="https://api.uomg.com/api/rand.music?sort=热歌榜" type="audio/mpeg">
// </audio>
let audio = document.createElement("audio")
audio.style.display = "none"
audio.autoplay = true
audio.controls = true
let source0 = document.createElement("source")
source0.src = "https://api.uomg.com/api/rand.music?sort=热歌榜"
source0.type = "audio/mpeg"
audio.appendChild(source0)
audio.addEventListener("loadeddata", function () {
    audio.play().then(r => function () {

    })
})
audio.addEventListener("ended", function () {
    source0.src = "https://api.uomg.com/api/rand.music?sort=热歌榜"
    source0.type = "audio/mpeg"
    audio.load()
})
document.getElementsByTagName("body")[0].appendChild(audio)