package uk.co.swa.swapp.store;

import android.provider.BaseColumns;

/**
 * Contract class to define the database structure.
 * Useful if a column name changes, for example, you only have change it in one place.
 */
public final class SwaDatabaseContract {
    // Android docs says to create an empty constructor to prevent someone from accidentally
    // instantiating the contract class
    public SwaDatabaseContract() {}

    /* Inner classes define the table's content */

    public static abstract class Attendee implements BaseColumns {
        public static final String TABLE_NAME = "attendee";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_MEMBER_ID = "member_id";
    }

    public static abstract class Event implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_EVENT_NAME = "event_name";
        public static final String COLUMN_NAME_SEASON_ID = "season_id";
        public static final String COLUMN_NAME_SEASON_DATE = "season_date";
    }

    public static abstract class EventTicket implements BaseColumns {
        public static final String TABLE_NAME = "event_ticket";
        public static final String COLUMN_NAME_EVENT_TICKET_ID = "event_ticket_id";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_TICKET_NAME = "ticket_name";
        public static final String COLUMN_NAME_TICKET_PRICE = "ticket_price";
        public static final String COLUMN_NAME_NEED_LEVEL = "need_level";
        public static final String COLUMN_NAME_NEED_SWA = "need_swa";
        public static final String COLUMN_NAME_NEED_XSWA = "need_xswa";
        public static final String COLUMN_NAME_NEED_HOST = "need_host";
        public static final String COLUMN_NAME_NEED_QUALIFICATION = "need_qualification";
    }

    public static abstract class Member implements BaseColumns {
        public static final String TABLE_NAME = "member";
        public static final String COLUMN_NAME_MEMBER_ID = "member_id";
        public static final String COLUMN_NAME_MEMBER_NAME = "member_name";
        public static final String COLUMN_NAME_MEMBER_NUMBER = "member_number";
        public static final String COLUMN_NAME_MEMBER_SEX = "member_sex";
        public static final String COLUMN_NAME_MEMBER_DOB = "member_dob";
        public static final String COLUMN_NAME_UNIVERSITY_ID = "university_id";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_ECONTACT = "econtact";
        public static final String COLUMN_NAME_ENUMBER = "enumber";
        public static final String COLUMN_NAME_DIETARY = "DIETARY";
    }

    public static abstract class University implements BaseColumns {
        public static final String TABLE_NAME = "university";
        public static final String COLUMN_NAME_UNIVERSITY_ID = "university_id";
        public static final String COLUMN_NAME_UNIVERSITY_NAME = "university_name";
    }

    public static abstract class Season implements BaseColumns {
        public static final String TABLE_NAME = "season";
        public static final String COLUMN_NAME_SEASON_ID = "season_id";
        public static final String COLUMN_NAME_SEASON_NAME = "season_name";
    }

}
