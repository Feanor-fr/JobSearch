<#import "../layout.ftlh" as main>
<@main.layout>

    <section class="py-5 ">
        <div class="container">

            <#assign isApplicant = (user.role?? && user.role == 1) />
            <#assign isEmployer   = (user.role?? && user.role == 2) />

            <h2 class="text-center fw-bold mb-5">
                Личный кабинет
                <#if isApplicant>Соискателя<#elseif isEmployer>Работодателя<#else>Пользователя</#if>
            </h2>

            <div class=" rounded-4  p-4 p-md-5">

                <div class="row mb-5 g-4 align-items-start">

                    <div class="col-lg-8">
                        <div class="d-flex flex-column flex-md-row align-items-start gap-4 gap-md-5">

                            <div class="text-center text-md-start">

                                <#if user.avatar?? && user.avatar?has_content>
                                    <img src="/images/${user.avatar}"
                                         alt="Аватар"
                                         class="rounded-3 shadow border border-warning border-4"
                                         style="width: 140px; height: 140px; object-fit: cover;">
                                <#else>
                                    <img src="/static/images/avatar.jpg"
                                         alt="Аватар по умолчанию"
                                         class="rounded-3 shadow border border-warning border-4"
                                         style="width: 140px; height: 140px; object-fit: cover;">
                                </#if>

                                <form action="/users/avatar" method="post" enctype="multipart/form-data" class="mt-3">
                                    <label for="avatarInput"
                                           class="btn btn-warning btn-sm fw-bold px-4 py-2 d-block mx-auto mx-md-0 shadow-sm"
                                           style="cursor: pointer; border-radius: 50px;">
                                        <i class="bi bi-camera-fill me-1"></i>Изменить фото
                                    </label>

                                    <input type="file"
                                           id="avatarInput"
                                           name="file"
                                           accept="image/*"
                                           class="d-none"
                                           onchange="this.form.submit();">
                                </form>
                            </div>


                            <div>
                                <h4 class="fw-bold mb-2">${user.name} ${user.surname!}</h4>
                                <#if user.age??>
                                    <p class="text-muted mb-3 fs-5">
                                        Возраст: ${user.age}
                                    </p>
                                </#if>
                                <a href="/users/update" class="btn btn-outline-secondary btn-sm rounded-pill px-4 py-2">
                                    Редактировать
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 d-flex align-items-stretch">
                        <div class="w-100 d-flex align-items-center justify-content-center">
                            <#if isApplicant>
                                <a href="/resumes/create" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column me-3" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    + Создать<br>резюме
                                </a>

                                <a href="#" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    Отклики
                                </a>

                            <#elseif isEmployer>
                                <a href="/vacancies/create" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column me-3" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    + Создать<br>вакансию
                                </a>

                                <a href="/resumes/showAll" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    Просмотреть<br>все резюме
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>

                <#if isEmployer>
                    <h4 class="fw-bold mb-4 text-center text-md-start">Вакансии</h4>

                    <#if vacancies?? && vacancies?size gt 0>
                        <div class="row g-4">
                            <#list vacancies as vacancy>
                                <div class="col-md-4">
                                    <div class="card h-100 shadow-sm border-2 rounded-3">
                                        <div class="card-body p-4">
                                            <h5 class="fw-medium mb-3">${vacancy.name!'Название вакансии'}</h5>
                                            <p class="text-muted small mb-4">
                                                Дата обновления: ${vacancy.updateDate}
                                            </p>
                                            <div class="d-flex gap-2">
                                                <a href="#" class="btn btn-outline-warning btn-sm flex-fill rounded-pill">Обновить</a>
                                                <a href="/vacancies/update/${vacancy.id}" class="btn btn-outline-secondary btn-sm flex-fill rounded-pill">Ред</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <div class="p-5 text-center  rounded-3">
                            <p class="text-muted fs-5 mb-0">У вас пока нет вакансий</p>
                        </div>
                    </#if>

                <#elseif isApplicant>
                    <h4 class="fw-bold mb-4 text-center text-md-start">Резюме</h4>

                    <#if resumes?? && resumes?size gt 0>
                        <div class="row g-4">
                            <#list resumes as resume>
                                <div class="col-md-4">
                                    <div class="card h-100 shadow-sm border-2 rounded-3">
                                        <div class="card-body p-4">
                                            <h5 class="fw-medium mb-3">${resume.name!'Название вакансии'}</h5>
                                            <p class="text-muted small mb-4">
                                                Дата обновления: ${resume.updateDate}
                                            </p>
                                            <div class="d-flex gap-2">
                                                <a href="#" class="btn btn-outline-warning btn-sm flex-fill rounded-pill">Обновить</a>
                                                <a href="/resumes/update/${resume.id}" class="btn btn-outline-secondary btn-sm flex-fill rounded-pill">Ред</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <div class="p-5 text-center  rounded-3">
                            <p class="text-muted fs-5 mb-0">У вас пока нет резюме</p>
                        </div>
                    </#if>

                <#else>
                    <div class="alert alert-warning text-center rounded-3">
                        Роль не определена
                    </div>
                </#if>

            </div>

        </div>
    </section>

</@main.layout>