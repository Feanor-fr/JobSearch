let eduCount = document.querySelectorAll('#education-container > div').length;

window.addEventListener('load', onLoad)

function onLoad(){
    initEvents()
}

function initEvents(){
    const eduBtn = document.getElementById('btn-add-education');

    if (eduBtn) eduBtn.onclick = addEducationBlock;
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








