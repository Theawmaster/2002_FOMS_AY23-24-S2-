package constants;

/**
 * Enum representing file paths for various data files.
 */
public enum FilePaths {
    /**
     * Path to the branch list CSV file.
     */
    branchListPath("FOMS/src/utilities/data/branch_list.csv"),

    /**
     * Path to the menu list CSV file.
     */
    menuListPath("FOMS/src/utilities/data/menu_list.csv"),

    /**
     * Path to the order menu list CSV file.
     */
    orderprocessListPath("FOMS/src/utilities/data/order_process_list.csv"),

    /**
     * Path to the staff list CSV file.
     */
    staffListPath("FOMS/src/utilities/data/staff_list.csv"),

    /**
     * Path to the staff passwords CSV file.
     */
    staffPasswordsPath("FOMS/src/utilities/data/staff_passwords.csv"),

    /**
     * Path to the payment list CSV file.
     */
    paymentListPath("FOMS/src/entities/data/payment_list.csv"),

    /**
     * Path to the folder where all CSV files are stored
     */
    dataFolderPath("FOMS/src/utilities/data");
    /**
     * The file path associated with the enum constant.
     */
    private final String path;

    /**
     * Constructs a new FilePaths enum constant with the specified file path.
     *
     * @param path The file path associated with the enum constant.
     */
    FilePaths(String path) {
        this.path = path;
    }

    /**
     * Gets the file path associated with this enum constant.
     *
     * @return The file path.
     */
    public String getPath() {
        return this.path;
    }
}
