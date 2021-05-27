<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
            total = page.getTotalPages()
            curPageNumber = page.getNumber() + 1
            head = (curPageNumber > 4)?then([1, -1], [1, 2, 3])
            tail = (curPageNumber > total - 3)?then([-1, total], [total - 2, total - 1, total])
            bodyBefore = (curPageNumber > 4 && curPageNumber < total - 1)?then([curPageNumber - 2, curPageNumber - 1], [])
            bodyAfter = (curPageNumber > 2 && curPageNumber < total - 3)?then([curPageNumber + 1, curPageNumber + 2], [])>
            body = head + bodyBefore + (curPageNumber > 3 && curPageNumber < total - 2)?then([pageNumber], []) + bodyAfter + tail
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>
    <div class="mt-3">
        <ul class="pagination">
            <#list body as p>
                <#if (p-1) == page.getNumber()>
                    <li class="page-item active"><a class="page-link" href="#" tabindex="-1">${p}</a></li>
                <#elseif p == -1>
                    <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">...</a></li>
                <#else>
                    <li class="page-item"><a class="page-link" href="${url}?page=${p - 1}&size=${page.getSize()}" tabindex="-1">${p}</a></li>
                </#if>
            </#list>
        </ul>

        <ul class="pagination justify-content-end">
            Элементов на странице:
            <#list [5, 10, 20, 50, 100] as e>
                <#if e == page.getSize()>
                    <li class="page-item active"><a class="page-link" href="#" tabindex="-1">${e}</a></li>
                <#else>
                    <li class="page-item"><a class="page-link" href="${url}?page=${page.getNumber()}&size=${e}" tabindex="-1">${e}</a></li>
                </#if>
            </#list>
        </ul>
    </div>
</#macro>