<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Флудилка</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
            crossorigin="anonymous">
    </script>
    <script>
        let webSocket;
        function connect() {
            webSocket = new WebSocket("ws://localhost/chat");
            webSocket.onmessage = function receiveMessage(response) {
                let json = JSON.parse(response['data']);
                console.log(json);
                $('#messagesList').first().after("<li>" + json['from'] + ": " + json['text'] + "</li>");
            }
        }

        function sendMessage(text, pageId) {
            let message = {
                "text" : text,
                "from" : pageId
            };
            webSocket.send(JSON.stringify(message));
        }

    </script>
</head>
<body onload="connect()">
    <div>
        <label for="message">Сообщение: </label>
        <input name="message" id="message" placeholder="message">
        <button onclick="sendMessage($('#message').val(), '${pageId}')">Отправить</button>
        <h3>
            Список сообщений
        </h3>
        <ul id="messagesList">

        </ul>
    </div>
</body>
</html>