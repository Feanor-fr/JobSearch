<#import "../layout.ftlh" as reg>
<@reg.layout ; spring>

    <section class="py-5">
        <div class="card mx-auto shadow-lg border border-secondary-subtle rounded-4 bg-white" style="max-width: 580px;">
            <div class="card-body py-5 px-4 px-md-5">

                <h3 class="mb-5 fw-bold text-center"><@spring.message "register.title"/></h3>

                <form id="register-form" action="/auth/register" method="post">
                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"
                        >
                    </#if>

                    <div class="mb-4">
                        <label for="name" class="form-label fw-medium"><@spring.message "register.name"/></label>
                        <@spring.formInput "userDto.name" 'id= "name" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>


                    <div class="mb-4">
                        <label for="surname" class="form-label fw-medium"><@spring.message "register.surname"/></label>
                        <@spring.formInput "userDto.surname" 'id= "surname" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="mb-4">
                        <label for="age" class="form-label fw-medium"><@spring.message "register.age"/></label>
                        <@spring.formInput "userDto.age" 'id= "age" type="number" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>


                    <div class="mb-4">
                        <label for="email" class="form-label fw-medium"><@spring.message "register.email"/></label>
                        <@spring.formInput "userDto.email" 'id= "email" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium"><@spring.message "register.password"/></label>
                        <@spring.formPasswordInput "userDto.password" 'id= "password" type="password" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>


                    <div class="mb-5">
                        <label for="phoneNumber" class="form-label fw-medium"><@spring.message "register.phone_number"/></label>
                        <@spring.formInput "userDto.phoneNumber" 'id= "phoneNumber" type="tel" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="mb-5">
                        <label class="form-label fw-bold text-center d-block mb-3"><@spring.message "register.role_type"/></label>

                        <div class="d-flex flex-column flex-md-row gap-3">

                            <div class="form-check form-check-inline flex-fill m-0">

                                <input class="form-check-input d-none" type="radio" name="role" id="role-applicant" value="1"/>
                                <label class="form-check-label btn btn-outline-warning w-100 py-3 fw-medium" for="role-applicant">
                                    <@spring.message "register.role_applicant"/>
                                </label>

                            </div>

                            <div class="form-check form-check-inline flex-fill m-0">
                                <input class="form-check-input d-none" type="radio" name="role" id="role-employer" value="2"/>

                                <label class="form-check-label btn btn-outline-warning w-100 py-3 fw-medium" for="role-employer">
                                    <@spring.message "register.role_employer"/>
                                </label>
                            </div>

                        </div>
<#--                        <@spring.formInput "userDto.role" 'type="radio" name="role" id="role-applicant" value="1" class="d-none"' />-->
<#--                        <@spring.formInput "userDto.role" 'type="radio" name="role" id="role-employer" value="2" class="d-none"' />-->
<#--                        <@spring.showErrors "<br>" "error text-danger" />-->
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                            <@spring.message "register.save"/>
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </section>
</@reg.layout>