package ua.com.makarenko.view;

public enum DescriptionMessage {

    COMMAND_WRONG("This command '%s' wrong, should be: "),

    SETUP_DRIVER("Setup JDBC driver!"),
    NOT_CONNECT("Could not connect to database '%s' "),
    CONNECT_SUCCESSFUL("Database connection '%s' was successful"),
    WRONG_CONNECT(COMMAND_WRONG + "connect|database|user|password"),

    TABLE_CREATED("Table '%s' created successfully"),
    WRONG_CREATE_TABLE(COMMAND_WRONG +
            "create|tableName|primaryKeyName|columnName1|columnValue1|....|columnNameN|columnValueN"),
    DATABASE_NOT_TABLES("This database has no tables"),

    DROP_TABLE("Table '%s' successfully deleted"),
    WRONG_DROP_TABLE(COMMAND_WRONG + "drop|tableName"),
    CONFIRM_DROP_TABLE("You want to delete the table '%s'? Enter the name of the table to confirm"),
    DELETION_CANCELED("deletion canceled"),

    CLEAR_TABLE("Table '%s' successfully cleared"),
    WRONG_CLEAR_TABLE(COMMAND_WRONG + "clear|tableName"),
    CONFIRM_CLEAR_TABLE("You want to clear the table '%s'? Enter the name of the table to confirm"),
    CLEAR_CANCELED("cleaning canceled"),

    WRONG_OPEN_TABLE(COMMAND_WRONG + "open|tableName")
    ;


    private String text;

    DescriptionMessage(String text) {
        this.text = text;
    }

    public String getDescription() {
        return text;
    }
}
