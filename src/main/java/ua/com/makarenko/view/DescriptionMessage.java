package ua.com.makarenko.view;

public enum DescriptionMessage {

    SETUP_DRIVER("Setup JDBC driver!"),
    NOT_CONNECT("Could not connect to database '%s' "),
    CONNECT_SUCCESSFUL("Database connection '%s' was successful"),
    WRONG_CONNECT("Command '%s' is wrong, should be: connect|database|user|password"),

    TABLE_CREATED("Table '%s' created successfully"),
    WRONG_CREATE_TABLE("Command '%s' is wrong, should be: " +
            "create|tableName|primaryKeyName|columnName1|columnValue1|....|columnNameN|columnValueN"),
    DATABASE_NOT_TABLES("This database has no tables"),

    DROP_TABLE("Table '%s' successfully deleted"),
    WRONG_DROP_TABLE("Command '%s' is wrong, should be: drop|tableName"),
    CONFIRM_DROP_TABLE("You want to delete the table '%s'? Enter the name of the table to confirm"),
    DELETION_CANCELED("deletion canceled"),

    CLEAR_TABLE("Table '%s' successfully cleared"),
    WRONG_CLEAR_TABLE("Command '%s' is wrong, should be: clear|tableName"),
    CONFIRM_CLEAR_TABLE("You want to clear the table '%s'? Enter the name of the table to confirm"),
    CLEAR_CANCELED("cleaning canceled"),

    WRONG_OPEN_TABLE("Command '%s' is wrong, should be: open|tableName"),

    INSERT_DATA("Records successfully added to the '%s'"),
    WRONG_INSERT_DATA("Command '%s' is wrong, should be: " +
            "insert|tableName|columnName1|columnValue1|...|columnNameN|columnValueN"),

    UPDATE_DATA("All records successfully updated"),
    WRONG_UPDATE_DATA("Command '%s' is wrong, should be: " +
            "update|tableName|primaryColumnName|primaryColumnValue|getColumnName1|" +
            "SetColumnNewValue1|...|getColumnNameN|SetColumnNewValueN"),

    DELETE_DATA("Record successfully deleted"),
    WRONG_DELETE_DATA("Command '%s' is wrong, should be: delete|tableName|columnName|columnValue"),

    IS_CONNECTED("You cannot use '%s' Connect to the database"),

    COMMAND_NOT_EXIST("Nonexistent command: '%s'"),

    FILE_NOT_FIND("File help.txt not find"),

    EXIT_PROGRAM("Goodbye, thank you for using our product"),

    FAIL("Fail! "),

    WELCOME_MESSAGE("Welcome to program 'SQLCMD'\n" +
                         "To view commands enter: help"),

    ENTER_COMMAND("Enter command (or help):");

    private String text;

    DescriptionMessage(String text) {
        this.text = text;
    }

    public String getDescription() {
        return text;
    }
}
