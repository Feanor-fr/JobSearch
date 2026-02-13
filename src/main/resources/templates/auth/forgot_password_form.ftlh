<#import "../layout.ftlh" as forgot>
<@forgot.layout ; spring>

    <h2>Forgot Password</h2>
    <#if error??>
        <b class="text-danger text-center">
            <@spring.message code=error />
        </b>
    </#if>

    <#if message??>
        <b class="text-warning text-center">
            <@spring.message code=message />
        </b>
    </#if>

    <div class="py-5 h-100">
        <div class="row d-flex align-items-center justify-content-center h-100">
            <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                <form action="/auth/forgot_password" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="form-outline  border border-secondary rounded p-3">
                        <div>
                            <label for="form2Example1" class="form-label fw-medium"><@spring.message "forgot_password.title"/></label>

                        </div>
                        <div class="text-center">
                                <label for="form2Example1" class="form-label fw-medium"><@spring.message "forgot_password.email"/></label>
                                <input type="email" name="email" class="form-control" required autofocus/>

                            <button type="submit" class="btn btn-warning mt-3">
                                <@spring.message "forgot_password.send"/>
                            </button>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@forgot.layout>
