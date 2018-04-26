Intercooler.ready(function () {
    $("[autofocus]:last").focus();
});

$(document).ready(function() {
    var interval = 500;
    function doAjax() {
        $.ajax({
            type: 'GET',
            url: '/refreshVotes',
            success: function (votesHtml) {
                $('#votes').html(votesHtml);
            },
            complete: function (data) {
                // Schedule the next
                setTimeout(doAjax, interval);
            }
        });
    }

    setTimeout(doAjax, interval);
});