document.addEventListener("DOMContentLoaded", onLoad);

function onLoad() {

    const applicantRadio = document.getElementById("role-applicant");
    const employerRadio = document.getElementById("role-employer");

    applicantRadio.addEventListener("change", updateForm);
    employerRadio.addEventListener("change", updateForm);

    updateForm();
}

function updateForm() {

    const applicant = document.getElementById("role-applicant").checked;

    const nameLabel = document.getElementById("name-label");
    const ageLabel = document.getElementById("age-label");
    const ageInput = document.getElementById("age");
    const surnameField = document.getElementById("surname-field");

    if (applicant) {

        nameLabel.textContent = "Имя";
        ageLabel.textContent = "Возраст";

        ageInput.min = 18;
        surnameField.style.display = "block";

    } else {

        nameLabel.textContent = "Название компании";
        ageLabel.textContent = "Период существования";

        ageInput.min = 1;
        surnameField.style.display = "none";
    }
}