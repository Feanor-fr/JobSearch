<#import "../layout.ftlh" as mess>
<@mess.layout ; spring>

   <div class="text-container">
       <h2>   <@spring.message "mess.title"/></h2>
       <p class="text-warning">${message}</p>
   </div>
</@mess.layout>