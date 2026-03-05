<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="container mt-5">

        <h3 class="mb-4">Чат</h3>

        <div id="chat-box"
             data-user="${currentUserEmail}"
             style="height:400px; overflow-y:scroll; border:1px solid #ccc; padding:10px;">

            <#list messages as message>
                <div>
                    <b>${message.senderEmail}</b> :
                    ${message.content}
                </div>
            </#list>

        </div>

        <div class="mt-3">

            <input
                    type="text"
                    id="messageInput"
                    class="form-control"
                    placeholder="Введите сообщение..."
            >

            <button
                    class="btn btn-warning mt-2"
                    onclick="sendMessage()"
            >
                Отправить
            </button>

        </div>

    </div>

    <script>
        const responseId = ${responseId};
    </script>

    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
    <script src="/static/js/chat.js"></script>

</@main.layout>