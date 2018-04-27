$(document).ready(function() {
    var interval = 1000;
    function doAjax() {
        $.ajax({
            type: 'GET',
            url: '/refreshVotes',
            success: function (count) {
                $('#answer-a-counter').text(count);
            },
            complete: function (data) {
                setTimeout(doAjax, interval);
            }
        });
    }

    setTimeout(doAjax, interval);
});