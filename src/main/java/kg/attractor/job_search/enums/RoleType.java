package kg.attractor.job_search.enums;

public enum RoleType {
    APPLICANT(1),
    EMPLOYER(2);

    private final Integer id;

    RoleType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
