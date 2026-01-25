<#import "../layout.ftlh" as home>
<@home.layout>
    <#if vacancies?has_content>




        <div class="d-flex align-items-center justify-content-between mb-4">


            <div style="width: 280px;"></div>


            <#if totalPages != 1>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg mb-0">


                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold"
                               href="/vacancies?page=${currentPage - 1}&sort=${sort!}">
                                ←
                            </a>
                        </li>


                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link bg-warning text-dark fw-bold"
                                   href="/vacancies?page=${p}&sort=${sort!}">
                                    ${p + 1}
                                </a>
                            </li>
                        </#list>


                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold"
                               href="/vacancies?page=${currentPage + 1}&sort=${sort!}">
                                →
                            </a>
                        </li>

                    </ul>
                </nav>
            </#if>

            <form method="get" action="/vacancies">
                <input type="hidden" name="page" value="0"/>

                <select class="form-select"
                        name="sort"
                        style="width: 280px"
                        onchange="this.form.submit()">

                    <#assign sortValue = sort!"">

                    <option value="" <#if sortValue == "">selected</#if>>
                        Сортировка: по дате обновления
                    </option>

                    <option value="createdDate" <#if sortValue == "createdDate">selected</#if>>
                        По дате публикации
                    </option>

                    <option value="responsesCount" <#if sortValue == "responsesCount">selected</#if>>
                        По количеству откликов
                    </option>

                </select>
            </form>


        </div>


        <div class="row">
            <#list vacancies as v>
                <div class="col-12 mb-4">
                    <div class="card h-100 shadow border bg-body-tertiary">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${v.name}</h5>
                            <p class="card-text">${v.description}</p>
                            <p class="card-text">Дата создания: ${v.createdDate}</p>
                            <p class="card-text">Обновление: ${v.updateDate}</p>
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