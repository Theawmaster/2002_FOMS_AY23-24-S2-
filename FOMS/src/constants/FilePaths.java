package constants;

public enum FilePaths {
    branchListPath("FOMS/src/utilities/data/branch_list.csv"),
    menuListPath("FOMS/src/utilities/data/menu_list.csv"),
    staffListPath("FOMS/src/utilities/data/staff_list.csv"),
    staffPasswordsPath("FOMS/src/utilities/data/staff_passwords.csv"),
    paymentListPath("entities/data/payment_list.csv");


    private final String path;

    FilePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}

