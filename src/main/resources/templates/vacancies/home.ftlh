<#import "../layout.ftlh" as home>
<@home.layout ; spring>
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
                     <@spring.message "home.sort.update_date"/>
                    </option>

                    <option value="createdDate" <#if sortValue == "createdDate">selected</#if>>
                        <@spring.message "home.sort.create_date"/>
                    </option>

                    <option value="responsesCount" <#if sortValue == "responsesCount">selected</#if>>
                        <@spring.message "home.sort.responses_count"/>
                    </option>

                </select>
            </form>


        </div>

        <div class="card p-3 mb-4 shadow-sm">
            <div class="row g-3">

                <div class="col-md-4">
                    <input type="text"
                           id="searchInput"
                           class="form-control"
                           placeholder="Поиск по названию">

                    <ul id="searchResults"
                        class="list-group position-absolute w-100 shadow"
                        style="z-index: 1000"></ul>
                </div>

                <div class="col-md-3">
                    <select id="categoryFilter" class="form-select">
                        <option value="">Все категории</option>

                        <#list categories?values as categoryName>
                            <option value="${categoryName}">
                                ${categoryName}
                            </option>
                        </#list>
                    </select>
                </div>

                <div class="col-md-3">
                    <select id="salarySort" class="form-select">
                        <option value="">Без сортировки</option>
                        <option value="asc">Зарплата ↑</option>
                        <option value="desc">Зарплата ↓</option>
                    </select>
                </div>

                <div class="col-md-2">
                    <button id="applyFilterBtn"
                            class="btn btn-warning w-100">
                        Применить
                    </button>
                </div>

            </div>
        </div>


        <div class="row">
            <#list vacancies as v>
                <div class="col-12 mb-4 vacancy-card"
                     data-name="${v.name?lower_case}"
                     data-category="${v.categoryName! ''}"
                     data-salary="${v.salary}">

                    <div class="card h-100 shadow border bg-body-tertiary">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${v.name}</h5>
                            <p class="card-text">${v.description}</p>
                            <p class="card-text"><@spring.message "home.vacancy.create_date"/> ${v.createdDate?date("yyyy-MM-dd")?string.medium}</p>
                            <p class="card-text"><@spring.message "home.vacancy.update_date"/> ${v.updateDate?date("yyyy-MM-dd")?string.medium}</p>
                            <strong><@spring.message "home.vacancy.salary"/></strong> ${v.salary?string.currency}


                            <div class="mt-auto text-end">
                                <#if isApplicant?? && isApplicant>
                                    <a href="/users/companies/${v.author}" class="btn btn-outline-warning btn-sm">
                                        Перейти к компании
                                    </a>
                                </#if>
                            </div>


                            <div class="mt-auto text-end">
                                <a href="/vacancies/${v.id}"
                                   class="btn btn-outline-warning">
                                    <@spring.message "home.vacancy.more"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>


    <#else>

    <p class="text-muted text-center fs-5 py-5"><@spring.message "home.vacancy.is_empty"/></p>

    </#if>

    <script src="/static/js/homeFilter.js"></script>
</@home.layout>