<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    Enter your confirmation code:
    <@spring.bind "codeInsertForm"/>
    <form method="post" action="/editProfile/confirmPhone/confirm/code">
        <@spring.formInput "codeInsertForm.code" "placeholder = \"confirmation code\""/>
        <input type="submit" value="ENTER"/>
    </form>
    <#if fail_confirm_message??>
        ${fail_confirm_message}
    </#if>
</body>
</html>