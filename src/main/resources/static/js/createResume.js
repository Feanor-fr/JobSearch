let eduCount = document.querySelectorAll('#education-container > div').length;
let expCount = document.querySelectorAll('#experience-container > div').length;
let conCount = document.querySelectorAll('#contacts-container > div').length;

window.addEventListener('load', onLoad)

function onLoad(){
    initEvents()
}

function initEvents(){
    const eduBtn = document.getElementById('btn-add-education');
    const expBtn = document.getElementById('btn-add-experience');
    const conBtn = document.getElementById('btn-add-contact');

    if (eduBtn) eduBtn.onclick = addEducationBlock;
    if (expBtn) expBtn.onclick = addExperienceBlock;
    if (conBtn) conBtn.onclick = addContactBlock;
}

function addEducationBlock() {
    const container = document.getElementById('education-container');

    const tInst = container.getAttribute('data-title');
    const tProg = container.getAttribute('data-prog');
    const tDeg = container.getAttribute('data-deg');

    const div = document.createElement('div');
    div.className = 'border border-warning rounded-3 p-3 mb-2 position-relative bg-light';

    div.innerHTML = `
<input type="text" 
    name="educationInfos[${eduCount}].institution" 
    class="form-control mb-2" placeholder="${tInst}">

<input type="text" 
    name="educationInfos[${eduCount}].program" 
    class="form-control mb-2" placeholder="${tProg}">

<div class="row mb-2">
    <div class="col-6">
        <input type="date" 
            name="educationInfos[${eduCount}].startDate" 
            class="form-control">
    </div>
    <div class="col-6">
        <input type="date" 
            name="educationInfos[${eduCount}].endDate" 
            class="form-control" >
    </div>
</div>

<input type="text" 
    name="educationInfos[${eduCount}].degree" 
    class="form-control"  placeholder="${tDeg}">

<button type= "button" class="btn btn-danger btn-sm btn-remove" style="position: absolute; top: -10px; right: -10px;" >X</button>`

    div.querySelector('.btn-remove').onclick = () => div.remove();

    container.append(div);
    eduCount++;
}

function addExperienceBlock() {
    const container = document.getElementById('experience-container');

    const tYears = container.getAttribute('data-years');
    const tCompany = container.getAttribute('data-company');
    const tPosition = container.getAttribute('data-position');
    const tResp = container.getAttribute('data-resp');

    const div = document.createElement('div');
    div.className = 'border border-warning rounded-3 p-3 mb-2 position-relative bg-light';

    div.innerHTML = `
<input type="number"
    name="workExperienceInfos[${expCount}].years"
    class="form-control mb-2"
    placeholder="${tYears}">

<input type="text"
    name="workExperienceInfos[${expCount}].companyName"
    class="form-control mb-2"
    placeholder="${tCompany}">

<input type="text"
    name="workExperienceInfos[${expCount}].position"
    class="form-control mb-2"
    placeholder="${tPosition}">

<input type="text"
    name="workExperienceInfos[${expCount}].responsibilities"
    class="form-control mb-2"
    placeholder="${tResp}"></input>

<button type="button"
    class="btn btn-danger btn-sm btn-remove"
    style="position: absolute; top: -10px; right: -10px;">
    X
</button>
`;

    div.querySelector('.btn-remove').onclick = () => div.remove();

    container.append(div);
    expCount++;
}

function addContactBlock() {
    const container = document.getElementById('contacts-container');
    const placeholder = container.getAttribute('data-placeholder');
    const contactTypes = JSON.parse(container.getAttribute('data-types'));

    const div = document.createElement('div');
    div.className = 'border border-warning rounded-3 p-3 mb-2 position-relative bg-light';

    let options = '';
    for (const id in contactTypes) {
        options += `<option value="${id}">${contactTypes[id]}</option>`;
    }

    div.innerHTML = `
        <select name="contactInfos[${conCount}].typeId" class="form-select mb-2 border-warning">
            ${options}
        </select>
        <input type="text" 
               name="contactInfos[${conCount}].contactValue" 
               class="form-control mb-2 border-warning" 
               placeholder="${placeholder}">
        <button type="button" class="btn btn-danger btn-sm btn-remove" 
                style="position: absolute; top: -10px; right: -10px;">X</button>
    `;

    div.querySelector('.btn-remove').onclick = () => div.remove();
    container.append(div);
    conCount++;
}








