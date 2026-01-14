<#import "../layout.ftlh" as home>
<@home.layout>
    <#if vacancies?has_content>

        <div class="row">
            <#list vacancies as v>
                <div class="col-12 mb-4">
                    <div class="card h-100 shadow border bg-body-tertiary">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${v.name}</h5>
                            <p class="card-text">${v.description}</p>
                            <strong>Зарплата:</strong> ${v.salary?string["0"]}

                            <div class="mt-auto text-end">
                                <button type="button" class="btn btn-outline-warning">Подробнее</button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>


    <#else>

    <p class="text-muted text-center fs-5 py-5">Пока нет доступных вакансий</p>

    </#if>
</@home.layout>