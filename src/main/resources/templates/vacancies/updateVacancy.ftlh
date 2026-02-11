<#import "../layout.ftlh" as editV>

<@editV.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width: 580px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-5 fw-bold text-center text-warning"><@spring.message "update_vacancy.title"/></h3>

                    <form id="vacancy-form" action="/vacancies/update/${vacancyId}" method="post">
                        <#if _csrf??>
                            <input
                                    type="hidden"
                                    name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"
                            >
                        </#if>

                        <input type="hidden" name="id" value="${vacancyId}" />

                        <div class="mb-4">
                            <label for="name" class="form-label fw-medium"><@spring.message "update_vacancy.name"/></label>
                            <@spring.formInput "vacancyDto.name" 'id="name" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="categoryId" class="form-label fw-medium"><@spring.message "update_vacancy.category"/></label>
                            <@spring.formSingleSelect "vacancyDto.categoryId" categories 'id="categoryId" class="form-select border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="salary" class="form-label fw-medium"><@spring.message "update_vacancy.salary"/></label>
                            <@spring.formInput "vacancyDto.salary" 'id="salary" type="number" step="1000" min="0" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="description" class="form-label fw-medium"><@spring.message "update_vacancy.description"/></label>
                            <@spring.formTextarea "vacancyDto.description" 'id="description" rows="6" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-medium"><@spring.message "update_vacancy.experience"/></label>
                            <div class="row g-3">
                                <div class="col-6">
                                    <label class="form-label fw-medium"><@spring.message "update_vacancy.experience_from"/></label>

                                    <@spring.formInput "vacancyDto.expFrom" 'id="expFrom" type="number" min="0" class="form-control border-warning"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>
                                <div class="col-6">
                                    <label class="form-label fw-medium"><@spring.message "update_vacancy.experience_to"/></label>
                                    <@spring.formInput "vacancyDto.expTo" 'id="expTo" type="number" min="0" class="form-control border-warning"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>
                            </div>
                        </div>

                        <div class="mb-5">
                            <div class="form-check">
                                <@spring.formCheckbox "vacancyDto.isActive" 'id="isActive" class="form-check-input"' />
                                <label class="form-check-label fw-medium" for="isActive"><@spring.message "update_vacancy.is_active"/></label>
                            </div>
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="text-center mt-5 d-flex justify-content-center gap-4 flex-wrap">
                            <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                                <@spring.message "update_vacancy.publish"/>
                            </button>

                            <a href="/users/profile" class="btn btn-outline-secondary btn-lg px-5 py-3">
                                <@spring.message "update_vacancy.cancel"/>
                            </a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </section>
</@editV.layout>