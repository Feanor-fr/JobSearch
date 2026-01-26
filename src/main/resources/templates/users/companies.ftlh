<#import "../layout.ftlh" as home>
<@home.layout>

    <h2 class="fw-bold mb-4">Компании</h2>

    <nav aria-label="Page navigation">
        <ul class="pagination pagination-lg justify-content-center mb-4">

            <li class="page-item <#if currentPage == 0>disabled</#if>">
                <a class="page-link bg-warning text-dark fw-bold"
                   href="/users/companies?page=${currentPage - 1}">
                    ←
                </a>
            </li>

            <#list 0..totalPages-1 as p>
                <li class="page-item <#if p == currentPage>active</#if>">
                    <a class="page-link bg-warning text-dark fw-bold"
                       href="/users/companies?page=${p}">
                        ${p + 1}
                    </a>
                </li>
            </#list>

            <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                <a class="page-link bg-warning text-dark fw-bold"
                   href="/users/companies?page=${currentPage + 1}">
                    →
                </a>
            </li>

        </ul>
    </nav>

    <div class="row">
        <#if companies?has_content>
            <#list companies as c>
                <div class="col-12 mb-4">
                    <div class="card h-100 shadow border bg-body-tertiary">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title"><strong>Компания:</strong> ${c.name} ${c.surname}</h5>
                            <p class="card-text"><strong>Email:</strong> ${c.email}</p>
                            <div class="mt-auto text-end">
                                <a href="/users/${c.email}" class="btn btn-outline-warning">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted text-center fs-5 py-5">Пока нет доступных компаний</p>
        </#if>
    </div>
</@home.layout>
