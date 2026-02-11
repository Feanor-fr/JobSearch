<#import "../layout.ftlh" as editR>

<@editR.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width: 580px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-5 fw-bold text-center text-warning"><@spring.message "update_resume.title"/></h3>

                    <form id="resume-form" action="/resumes/update/${resumeId}" method="post">
                        <#if _csrf??>
                            <input
                                    type="hidden"
                                    name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"
                            >
                        </#if>

                        <input type="hidden" name="id" value="${resumeId}" />

                        <div class="mb-4">
                            <label for="name" class="form-label fw-medium"><@spring.message "update_resume.name"/></label>
                            <@spring.formInput "resumeDto.name"
                            'id="name" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="categoryId" class="form-label fw-medium"><@spring.message "update_resume.category"/></label>
                            <@spring.formSingleSelect
                            "resumeDto.categoryId"
                            categories
                            'id="categoryId" class="form-select border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="salary" class="form-label fw-medium"><@spring.message "update_resume.salary"/></label>
                            <@spring.formInput "resumeDto.salary"
                            'id="salary" type="number" step="1000" min="0" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-5">
                            <div class="form-check">
                                <@spring.formCheckbox "resumeDto.isActive"
                                'id="isActive" class="form-check-input"' />
                                <label class="form-check-label fw-medium" for="isActive">
                                    <@spring.message "update_resume.is_active"/>
                                </label>
                            </div>
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="text-center mt-5 d-flex justify-content-center gap-4 flex-wrap">
                            <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                                <@spring.message "update_resume.publish"/>
                            </button>

                            <a href="/users/profile" class="btn btn-outline-secondary btn-lg px-5 py-3">
                                <@spring.message "update_resume.cancel"/>
                            </a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </section>
</@editR.layout>