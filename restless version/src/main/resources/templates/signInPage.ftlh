<#import "header.ftlh" as template>
<@template.header>
<h1>SIGN IN</h1>
    <form action="/signIn" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Nickname: </label>
            <div class="col-sm-10">
                <input name="username" type="text" placeholder="INPUT NICKNAME" class="form-control">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-10">
                <input name="password" type="password" placeholder="INPUT PASSWORD" class="form-control">
            </div>
        </div>
        <label>
            <input type="checkbox" name="remember_me">remember me
        </label>
        <input type="submit" value="SIGN IN">
    </form>
</@template.header>