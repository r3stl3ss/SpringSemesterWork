<#include "securityCheck.ftlh">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">The Temporary Nameless App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <#if userExists>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/reviews">Review list</a>
                    </li>
                </#if>
                <#if userExists>
                    <li class="nav-item">
                        <a class="nav-link active" href="/reviews/${currentUserId}">My reviews</a>
                    </li>
                </#if>
                <#if !isPhoneProved>
                    <#if userExists>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/editProfile/confirmPhone">Confirm phone</a>
                        </li>
                    </#if>
                </#if>
                <#if !userExists>
                    <li class="nav-item">
                        <a class="nav-link active" href="/signUp">Join us</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/signIn">Sign in</a>
                    </li>
                </#if>
            </ul>
        </div>
        <div class="navbar-text">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li>
                    <a class="nav-link active" aria-current="page" href="/profile">${username}</a>
                </li>
                <#if userExists>
                    <form action="/logout" method="get">
                        <button class="btn-danger" type="submit">Logout</button>
                    </form>
                </#if>
            </ul>
        </div>

    </div>
</nav>