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

                        <#if resumeDto.workExperienceInfos?has_content>
                            <h5 class="mt-4">Опыт работы</h5>

                            <#list resumeDto.workExperienceInfos as work>
                                <div class="border rounded p-3 mb-3">

                                    <input type="hidden"
                                           name="workExperienceInfos[${work_index}].id"
                                           value="${work.id}" />

                                    <div class="mb-2">
                                        <label>Годы</label>
                                        <input type="number"
                                               name="workExperienceInfos[${work_index}].years"
                                               value="${work.years!}"
                                               class="form-control"/>
                                    </div>

                                    <div class="mb-2">
                                        <label>Компания</label>
                                        <input type="text"
                                               name="workExperienceInfos[${work_index}].companyName"
                                               value="${work.companyName!}"
                                               class="form-control"/>
                                    </div>

                                    <div class="mb-2">
                                        <label>Должность</label>
                                        <input type="text"
                                               name="workExperienceInfos[${work_index}].position"
                                               value="${work.position!}"
                                               class="form-control"/>
                                    </div>

                                    <div class="mb-2">
                                        <label>Обязанности</label>
                                        <textarea
                                                name="workExperienceInfos[${work_index}].responsibilities"
                                                class="form-control">${work.responsibilities!}</textarea>
                                    </div>

                                </div>
                            </#list>
                        </#if>

                        <#if resumeDto.educationInfos?has_content>
                            <h5 class="mt-4">Образование</h5>

                            <#list resumeDto.educationInfos as edu>
                                <div class="border rounded p-3 mb-3">

                                    <input type="hidden"
                                           name="educationInfos[${edu_index}].id"
                                           value="${edu.id}" />

                                    <input type="text"
                                           name="educationInfos[${edu_index}].institution"
                                           value="${edu.institution!}"
                                           class="form-control mb-2"
                                           placeholder="Учебное заведение"/>

                                    <input type="text"
                                           name="educationInfos[${edu_index}].program"
                                           value="${edu.program!}"
                                           class="form-control mb-2"
                                           placeholder="Программа"/>

                                    <input type="date"
                                           name="educationInfos[${edu_index}].startDate"
                                           value="${edu.startDate!}"
                                           class="form-control mb-2"/>

                                    <input type="date"
                                           name="educationInfos[${edu_index}].endDate"
                                           value="${edu.endDate!}"
                                           class="form-control mb-2"/>

                                    <input type="text"
                                           name="educationInfos[${edu_index}].degree"
                                           value="${edu.degree!}"
                                           class="form-control"
                                           placeholder="Степень"/>

                                </div>
                            </#list>
                        </#if>

                        <#if resumeDto.contactInfos?has_content>
                            <h5 class="mt-4">Контакты</h5>

                            <#list resumeDto.contactInfos as contact>
                                <div class="mb-3 border rounded p-3">

                                    <input type="hidden"
                                           name="contactInfos[${contact_index}].id"
                                           value="${contact.id}" />

                                    <select name="contactInfos[${contact_index}].typeId"
                                            class="form-select border-warning mb-2">

                                        <#list contactTypes?keys as key>
                                            <option value="${key}"
                                                    <#if key == contact.typeId?string>selected</#if>>
                                                ${contactTypes[key]}
                                            </option>
                                        </#list>

                                    </select>

                                    <input type="text"
                                           name="contactInfos[${contact_index}].contactValue"
                                           value="${contact.contactValue!}"
                                           class="form-control border-warning"
                                           placeholder="Введите контакт"/>

                                </div>
                            </#list>
                        </#if>


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