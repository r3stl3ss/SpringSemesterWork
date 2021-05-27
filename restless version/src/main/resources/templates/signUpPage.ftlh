<#import "header.ftlh" as template>
<#import "spring.ftl" as spring>
<@template.header />
<ul class="horizontal_list">
    <li><a href="?lang=ru"><img src="rus.jpg" width="37" height="20"></a></li>
    <li><a href="?lang=en"><img src="uk.jpg" width="37" height="20"></a></li>
    <li><a href="?lang=es"><img src="espana.jpg" width="37" height="20"></a></li>
</ul>
<h1><@spring.message 'signUpPage.registration.title'/></h1>
<@spring.bind "signUpForm"/>
    <form action="/signUp" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <@spring.message 'signUpPage.registration.name'/>
            </label>
            <div class="col-sm-2">
                <input name="name" type="text" class="form-control">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <@spring.message 'signUpPage.registration.email'/>
            </label>
            <div class="col-sm-10">
                <@spring.formInput "signUpForm.email" "" "email"/>
                <@spring.showErrors "<br>" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <label for="password">
                    <@spring.message 'signUpPage.registration.password'/>
                </label>
            </label>
            <div class="col-sm-10">
                <@spring.formInput "signUpForm.password" "" "password"/>
                <@spring.showErrors "<br>" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <label for="username">
                    <@spring.message 'signUpPage.registration.username'/>
                </label>
            </label>
            <div class="col-sm-2">
                <input name="username" type="text">
            </div>
        </div>
        <input type="submit" value=<@spring.message 'signUpPage.registration.submit.button'/>>
    </form>