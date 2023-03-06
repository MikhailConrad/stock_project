package com.stock.socks.model.entity;

public enum Operation {
    EQUAL("equal"),
    LESS_THAN("lessThan"),
    MORE_THAN("moreThan");

    public String getOperationName() {
        return operationName;
    }

    private final String operationName;

    Operation(String operationName) {
        this.operationName = operationName;
    }
}
