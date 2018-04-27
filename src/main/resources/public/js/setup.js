$(document).ready(function() {
    var interval = 1000;
    function doAjax() {
        $.ajax({
            type: 'GET',
            url: '/setupRefresh',
            success: function (setupHtml) {
                $('#voteapp').html(setupHtml);
            }
        });
    }

    var endSetupButton = $("#endSetup");
    endSetupButton.click(function () {
        $.ajax({
            type: 'POST',
            url: '/endSetup',
            success: function (data) {
                console.log("Ended setup = " + data);
                endSetupButton.hide();
                clearTimeout(timeout);
            }
        });
    });

    var timeout = setTimeout(doAjax, interval);
});