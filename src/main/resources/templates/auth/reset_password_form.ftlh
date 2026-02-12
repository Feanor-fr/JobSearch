<#import "../layout.ftlh" as reset>
<@reset.layout ; spring>

    <div class="text-center">
        <h2> <@spring.message "reset_password.title"/></h2>
        <#if error??>
            <b class="text-danger">${error}</b>
        </#if>
    </div>

    <div class="py-5 h-100">
        <div class="row d-flex align-items-center justify-content-center h-100">
            <div class="col-md-7 col-lg-5 col-xl-5">
                <form action="/auth/reset_password/${token}" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
<#--                    <input type="hidden" name="token" value="${token!}">-->
                    <input type="password" name="password" class="form-control"
                            />

                    <#if passwordError??>
                        <div class="text-danger mt-1">
                            ${passwordError}
                        </div>
                    </#if>

                    <button type="submit" class="btn btn-warning mt-3">
                        <@spring.message "reset_password.change"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</@reset.layout>