<#import "header.ftlh" as template>
<#include "securityCheck.ftlh">
<#import "pager.ftlh" as p>
<@template.header>
    <#--<div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/reviews/filter" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}">
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
        </div>
    </div>-->
    <@p.pager url page />
    <#if isPhoneProved>
        <a class="btn btn-outline-dark" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Залить отзыв
        </a>
    </#if>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-1">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-1">
                    <input type="text" class="form-control" name="text" placeholder="Текст отзыва">
                </div>
                <div class="form-group mt-1">
                    <input type="text" class="form-control" name="header" placeholder="Заголовок">
                </div>
                <div class="custom-file mt-1">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите файл</label>
                </div>
                <div class="form-group mt-1">
                    <input type="number" class="form-control" name="rate" list="rateList" min="0" max="10" step="1" placeholder="Оценка">
                </div>
                <div class="form-group mt-1">
                    <button type="submit" class="btn btn-dark ml-2">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <br>
    Список отзывов:
    <#list page.content as review>
        <div class="card mb-3">
            <b>#${review.id}</b>
            <h5 class="card-title">${review.header}</h5>
            <p class="card-text">${review.text}</p>
            <h5 class="card-title">${review.rating}/10</h5>
            <div>
                <#if review.pathToPicture??>
                    <img width="640px" height="360px" src="/img/${review.pathToPicture}">
                </#if>
            </div>
            <div class="card-footer text-muted">
                by <a href="/reviews/${review.author.id}">${review.author.username}</a>
            </div>
            <div class="card-footer text-muted">
                ${review.createdAt}
            </div>
        </div>
    </#list>
<datalist id="rateList">
    <option value="0" />
    <option value="1" />
    <option value="2" />
    <option value="3" />
    <option value="4" />
    <option value="5" />
    <option value="6" />
    <option value="7" />
    <option value="8" />
    <option value="9" />
    <option value="10" />
</datalist>
</@template.header>