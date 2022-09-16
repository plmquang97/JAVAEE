package com.axonactive.agileterm.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class ErrorMessage {
  static InputStream input = ErrorMessage.class.getClassLoader().getResourceAsStream("message.properties");
    static Properties prop = new Properties();
      static String getData(String var) throws IOException {
        prop.load(input);
        return prop.getProperty(var);
    };




    public static String INVALID_ID;

    static {
        try {
            INVALID_ID = ErrorMessage.getData("exception.input.validation");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static final String TOPIC_NOT_FOUND;

    static {
        try {
            TOPIC_NOT_FOUND = getData("exception.resource-not-found-topic");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String TERM_NOT_FOUND;

    static {
        try {
            TERM_NOT_FOUND = getData("exception.resource-not-found-term");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String TERM_TOPIC_NOT_FOUND;

    static {
        try {
            TERM_TOPIC_NOT_FOUND = getData("exception.resource-not-found-term-topic");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String DESCRIPTION_NOT_FOUND;

    static {
        try {
            DESCRIPTION_NOT_FOUND = getData("exception.resource-not-found-description");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String AUTHOR_NOT_FOUND;

    static {
        try {
            AUTHOR_NOT_FOUND = getData("exception.resource-not-found-author");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String USER_NOT_FOUND;

    static {
        try {
            USER_NOT_FOUND = getData("exception.resource-not-found-user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String VOTE_NOT_FOUND;

    static {
        try {
            VOTE_NOT_FOUND = getData("exception.resource-not-found-vote");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static final String USER_EMAIL_EXISTED;

    static {
        try {
            USER_EMAIL_EXISTED = getData("exception.security.user-email-existed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String USERNAME_EMAIL_EXISTED;

    static {
        try {
            USERNAME_EMAIL_EXISTED = getData("exception.security.username-existed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String CONFIRM_PASSWORD_NOT_MATCH;

    static {
        try {
            CONFIRM_PASSWORD_NOT_MATCH = getData("exception.security.confirm-password-not-match");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String USER_EMAIL_INVALID;

    static {
        try {
            USER_EMAIL_INVALID = getData("exception.security.user-email-invalid");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String PASSWORD_INVALID;

    static {
        try {
            PASSWORD_INVALID = getData("exception.security.password-invalid");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static final String ACCOUNT_ALREADY_EXISTED;

    static {
        try {
            ACCOUNT_ALREADY_EXISTED = getData("exception.system.account-already-activate");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String TERM_ALREADY_EXISTED;

    static {
        try {
            TERM_ALREADY_EXISTED = getData("exception.system.term-already-existed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
