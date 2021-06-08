<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
    <style>
        ul.horizontal_list{
            margin: 3px;
            padding: 3px;
        }
        ul.horizontal_list li{
            display: inline;
            margin-right: 3px;
            padding: 5px;
        }
    </style>
    <style>
        .error {
            color: maroon;
        }
    </style>
</head>
<body>
<ul class="horizontal_list">
    <li><a href="?lang=ru"><img src="rus.jpg" width="37" height="20"></a></li>
    <li><a href="?lang=en"><img src="uk.jpg" width="37" height="20"></a></li>
    <li><a href="?lang=es"><img src="espana.jpg" width="37" height="20"></a></li>
</ul>
<h1><@spring.message 'signUpPage.registration.title'/></h1>
<@spring.bind "signUpForm"/>
    <form action="/signUp" method="post">
        </label><label for="name">
            <@spring.message 'signUpPage.registration.name'/>
        </label>
        <input name="name" type="text">
        <label for="email">
            <@spring.message 'signUpPage.registration.email' />
        </label>
        <@spring.formInput "signUpForm.email" "" "email"/>
        <@spring.showErrors "<br>" "error"/>
        <label for="password">
            <@spring.message 'signUpPage.registration.password'/>
        </label>
        <@spring.formInput "signUpForm.password" "" "password" />
        <@spring.showErrors "<br>" "error"/>
        <label for="username">
            <@spring.message 'signUpPage.registration.username'/>
        </label>
        <input name="username" type="text">
        <input type="submit" value=<@spring.message 'signUpPage.registration.submit.button'/>>
    </form>
</body>
</html>