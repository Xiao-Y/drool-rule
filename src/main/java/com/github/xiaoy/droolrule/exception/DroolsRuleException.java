package com.github.xiaoy.droolrule.exception;

public class DroolsRuleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DroolsRuleException() {
        super();
    }

    public DroolsRuleException(String errorMsg) {
        super(errorMsg);
    }

    public static DroolsRuleException error(String errorMsg) {
        return new DroolsRuleException(errorMsg);
    }

}
