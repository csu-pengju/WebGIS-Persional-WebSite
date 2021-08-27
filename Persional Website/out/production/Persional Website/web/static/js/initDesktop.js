var mapControl = null;

$(document).ready(function () {
    initDesktop();
});

function initDesktop() {
    mapControl = new MapControl({
        div:"map",
        width:800,
        height:600
    });


}