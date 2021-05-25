<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>profile</title>
</head>
<body>

YOUR PROFILE
<br>
HELLO ${username}
<br>
Your email: ${email}
<br>
Your id in system: ${id}
<br>
Your name: ${name}
<br>
<#if imagePath??>
    <img height="200px" width="200px"src="/img/${imagePath}">
</#if>

<button><a href="http://localhost/editProfile">Edit Profile</a></button>
</body>
</html>