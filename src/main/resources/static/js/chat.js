let stompClient = null;

const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

function connect() {

    const socket = new SockJS('/ws-chat');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {

        stompClient.subscribe('/topic/chat/' + responseId, function (message) {

            showMessage(JSON.parse(message.body));

        });

    });

}

function sendMessage() {

    const input = document.getElementById("messageInput");

    if(input.value.trim() === "") return;

    const message = {

        responseId: responseId,
        content: input.value

    };

    stompClient.send("/app/sendMessage", {}, JSON.stringify(message));

    input.value = "";

}

function showMessage(message){
    const chat = document.getElementById("chat-box");
    const div = document.createElement("div");
    const currentUser = document.getElementById("chat-box").dataset.user;

    if(message.senderEmail === currentUser){
        div.style.textAlign = "right";
        div.innerHTML =
            "<div style='background:#d1e7dd; padding:8px; margin:5px; display:inline-block; border-radius:8px;'>"
            + message.content +
            "</div>";
    } else {
        div.style.textAlign = "left";
        div.innerHTML =
            "<div style='background:#f8d7da; padding:8px; margin:5px; display:inline-block; border-radius:8px;'>"
            + message.content +
            "</div>";
    }

    chat.appendChild(div);
    chat.scrollTop = chat.scrollHeight;
}

connect();