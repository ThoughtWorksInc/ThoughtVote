$(document).ready(function() {
    var interval = 1000;
    function doAjax() {
        $.ajax({
            type: 'GET',
            url: '/refreshVotes',
            success: function (count) {
                $('#answer-a-counter').text(JSON.parse(count).ofVoteA);
                $('#answer-b-counter').text(JSON.parse(count).ofVoteB);
            },
            complete: function () {
                setTimeout(doAjax, interval);
            }
        });
    }

    setTimeout(doAjax, interval);
});