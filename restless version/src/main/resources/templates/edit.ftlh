
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

EDIT USERNAME
<@spring.bind "nameEditForm"/>
<form method="post" action="/editProfile/username">
    <input type="hidden">
    <@spring.formInput "nameEditForm.username" "class = \"password_input\" name=\"text\"  placeholder = \"name\"" />
    <@spring.formInput "nameEditForm.password" "class = \"password_input\" name=\"password\"  placeholder = \"password\"" />
    <@spring.showErrors "<br>" "error" />
    <input type="submit" value="ENTER"/>
</form>

EDIT PASSWORD
<@spring.bind "passwordEditForm"/>
<form method="post" action="/editProfile/password">
    <input type="hidden">
    <@spring.formInput "passwordEditForm.oldPassword" "class = \"password_input\" name=\"password\"  placeholder = \"old password\"" />
    <@spring.formInput "passwordEditForm.newPassword" "class = \"password_input\" name=\"password\"  placeholder = \"new password\"" />
    <@spring.formInput "passwordEditForm.repeatNewPassword" "class = \"password_input\" name=\"password\"  placeholder = \"confirm new password\"" />
    <@spring.showErrors "<br>" "error" />
    <input type="submit" value="ENTER"/>
</form>
Confirm phone number
<button><a href="http://localhost/editProfile/confirmPhone">confirm</a></button>
<br>
EDIT IMAGE
    <form method="post" enctype="multipart/form-data" action="/editProfile/image">
        <span class="title">Добавить фото</span>
        <input type="file" name="file">
        <input type="submit" value="ENTER"/>
    </form>
</body>
</html>
