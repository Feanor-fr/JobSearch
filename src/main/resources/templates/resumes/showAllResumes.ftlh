<#import "../layout.ftlh" as resumePage>
<@resumePage.layout>
    <#if resumes?has_content>

        <div class="row">
            <#list resumes as r>
                <div class="col-12 mb-4">
                    <div class="card h-100 shadow border bg-body-tertiary">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${r.name}</h5>
                            <p class="card-text">${r.applicant}</p>
                            <strong>Зарплата:</strong> ${r.salary?string["0"]}

                            <div class="mt-auto text-end">
                                <button type="button" class="btn btn-outline-warning">Подробнее</button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>


    <#else>

        <p class="text-muted text-center fs-5 py-5">Пока нет доступных резюме!</p>

    </#if>
</@resumePage.layout>