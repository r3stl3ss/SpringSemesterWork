<#import "spring.ftl" as spring>
<#import "header.ftlh" as template>
<#include "securityCheck.ftlh">

<@template.header>
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
    <#if !isPhoneProved>
        Confirm phone number
        <button><a href="http://localhost/editProfile/confirmPhone">confirm</a></button>
    </#if>
<br>
EDIT IMAGE
    <form method="post" enctype="multipart/form-data" action="/editProfile/image">
        <span class="title">Добавить фото</span>
        <input type="file" name="file">
        <input type="submit" value="ENTER"/>
    </form>
</@template.header>