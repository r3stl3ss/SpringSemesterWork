<#import "spring.ftl" as spring>
<#import "header.ftlh" as template>

<@template.header>
    Enter your phone number here:
    <@spring.bind "phoneConfirmForm"/>
    <form method="post" action="/editProfile/confirmPhone/confirm">
        <@spring.formInput "phoneConfirmForm.phone" "placeholder = \"phone number\""/>
        <input type="submit" value="ENTER"/>
    </form>
</@template.header>