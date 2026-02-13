<#import "../layout.ftlh" as mess>
<@mess.layout ; spring>

   <div class="text-container">
       <h2>   <@spring.message "mess.title"/></h2>
       <#if message??>
           <@spring.message code=message />
       </#if>
   </div>
</@mess.layout>