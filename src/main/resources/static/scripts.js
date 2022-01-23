var stompClient = null;
var notificationCount = 0;

$(document).ready(function() {
    let phone = prompt('Please enter your phone number!');
    connect(phone);

   let mResponse = getMissedCalls(phone);

    $("#send").click(function() {
        sendMessage();
    });

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

    $("#notifications").click(function() {
        resetNotificationCount();
    });
});

function getMissedCalls(phone) {
    return '';
}

function connect(phone) {
    var socket = new SockJS('/our-websocket?rootNumber='+ phone);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        updateNotificationDisplay();
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/user/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/user/topic/private-messages', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({'content': $("#message").val()}));
}

function sendPrivateMessage() {
    console.log("sending private message");
    stompClient.send("/ws/message", {}, JSON.stringify({'content': $("#private-message").val()}));
}

function updateNotificationDisplay() {
    if (notificationCount == 0) {
        $('#notifications').hide();
    } else {
        $('#notifications').show();
        $('#notifications').text(notificationCount);
    }
}

function resetNotificationCount() {
    notificationCount = 0;
    updateNotificationDisplay();
}