<#import "../layout.ftlh" as resumePage>
<@resumePage.layout>
    <#if resumes?has_content>

        <div class="d-flex align-items-center justify-content-between mb-4">


            <div style="width: 280px;"></div>


            <#if totalPages != 1>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg mb-0">


                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold"
                               href="/resumes/showAll?page=${currentPage - 1}">
                                ←
                            </a>
                        </li>


                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link bg-warning text-dark fw-bold"
                                   href="/resumes/showAll?page=${p}">
                                    ${p + 1}
                                </a>
                            </li>
                        </#list>


                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold"
                               href="/resumes/showAll?page=${currentPage + 1}">
                                →
                            </a>
                        </li>

                    </ul>
                </nav>
            </#if>

        </div>

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