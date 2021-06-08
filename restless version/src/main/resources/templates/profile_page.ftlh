<#import "spring.ftl" as spring>
<#import "header.ftlh" as template>

<@template.header>
YOUR PROFILE
<div class="form-group mt-1">
    HELLO ${username}
</div>
    <div class="form-group mt-1">
        Your email: ${email}
    </div>
    <div class="form-group mt-1">
        Your id in system: ${id}
    </div>
    <div class="form-group mt-1">
        Your name: ${name}
    </div>
<#if imagePath??>
    <div>
        <img height="200px" width="200px"src="/img/${imagePath}">
    </div>
</#if>
<#if phone??>
    <div class="form-group mt-1">
        Your phone: ${phone}
    </div>
</#if>
<br>
<button><a href="/editProfile">Edit Profile</a></button>
</@template.header>